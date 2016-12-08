package hu.bme.msc.onlab.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.msc.onlab.dao.repository.IUserRepository;
import hu.bme.msc.onlab.dto.SystemNotification;
import hu.bme.msc.onlab.enums.SystemNotificationType;
import hu.bme.msc.onlab.exception.MySqlRegisrationException;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Service
public class UserService extends BaseService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<User> register(User user) throws RegistrationException {
		LOGGER.info("Setting registration date of user: " + LOGGER_UTIL.getValue(user));
		notificationService.send(SystemNotification.of(SystemNotificationType.RMDBS_REGISTRAION_STARTED));
		user.setRegistration_date(new Date());

		LOGGER.info("Checking if User exists in MySQL database: " + LOGGER_UTIL.getValue(user));
		ResponseDto<Boolean> existResponse = exists(user);
		if (!existResponse.isSuccess()) {
			// exception happened
			throw new MySqlRegisrationException(existResponse).setUser(user);
		} else if (existResponse.isSuccess() && Boolean.TRUE.equals(existResponse.getValue())) {
			throw new MySqlRegisrationException("User exists in MySQL database: " + LOGGER_UTIL.getValue(user))
					.setUser(user);
		}
		LOGGER.info("User does not exists in MySQL database: " + LOGGER_UTIL.getValue(user));

		LOGGER.info("Registering user to MySQL server: " + LOGGER_UTIL.getValue(user));
		ResponseDto<Void> createResponse = create(user);
		if (!createResponse.isSuccess()) {
			throw new MySqlRegisrationException(createResponse).setUser(user);
		}

		LOGGER.info("MySQL registration has been finished successfullly");
		notificationService.send(SystemNotification.of(SystemNotificationType.RMDBS_REGISTRATION_FINISHED));
		return ResponseDto.ok(user);
	}

	@Override
	public ResponseDto<Void> create(User user) {
		LOGGER.info("Creating User in MySQL database: " + LOGGER_UTIL.getValue(user));
		return executeOperation(() -> {
			// save or update
			userRepository.save(user);
			LOGGER.info("User has been created in MySQL database");
			return ResponseDto.ok();
		});
	}

	@Override
	public ResponseDto<Boolean> exists(User user) {
		LOGGER.info("Searching User in MySQL database: " + LOGGER_UTIL.getValue(user));
		return executeOperation(() -> {
			return ResponseDto.ok(userRepository.exists(user.getUsernameId()));
		});
	}

	@Override
	@Transactional
	public ResponseDto<User> get(String usernameId) {
		LOGGER.info("Get user by its username: " + usernameId);
		return executeOperation(() -> {
			return Optional.ofNullable(userRepository.findByUsernameId(usernameId)).map(user -> {
				return ResponseDto.ok(user);
			}).orElse(ResponseDto.fail("Could not find user by id: " + usernameId));
		});
	}

}
