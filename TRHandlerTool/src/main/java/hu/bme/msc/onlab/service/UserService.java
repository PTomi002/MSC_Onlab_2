package hu.bme.msc.onlab.service;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.msc.onlab.dao.repository.IUserRepository;
import hu.bme.msc.onlab.dto.Event.EventBuilder;
import hu.bme.msc.onlab.exception.MySqlRegisrationException;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Service
public class UserService extends BaseService implements IUserService {

	private static final String USERNAME = "Username";
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseDto<User> register(final User user) throws RegistrationException {
		LOGGER.info("Setting registration date of user: " + LOGGER_UTIL.getValue(user));
		notificationService.send(EventBuilder.of().setMessage("RDBMS Registration: Starting")
				.addAdditional(() -> USERNAME, () -> user.getUsernameId()).build());
		user.setRegistratinDate(new Date());

		LOGGER.info("Checking if User exists in MySQL database: " + LOGGER_UTIL.getValue(user));
		ResponseDto<Boolean> existResponse = exists(user);
		if (!existResponse.isSuccess()) {
			// exception happened
			MySqlRegisrationException exc = new MySqlRegisrationException(existResponse, user);
			notificationService.send(EventBuilder.of().setMessage("RDBMS Registration: Operation unsuccessful")
					.addAdditional(() -> USERNAME, () -> user.getUsernameId())
					.addAdditional(() -> "Trace", () -> ExceptionUtils.getStackTrace(exc))
					.build());
			throw exc;
		} else if (existResponse.isSuccess() && Boolean.TRUE.equals(existResponse.getValue())) {
			MySqlRegisrationException exc = new MySqlRegisrationException("User exists in MySQL database: " + LOGGER_UTIL.getValue(user), user);
			notificationService.send(EventBuilder.of().setMessage("RDBMS Registration: Operation unsuccessful")
					.addAdditional(() -> USERNAME, () -> user.getUsernameId())
					.addAdditional(() -> "Trace", () -> ExceptionUtils.getStackTrace(exc))
					.build());
			throw exc;
		}
		LOGGER.info("User does not exists in MySQL database: " + LOGGER_UTIL.getValue(user));

		LOGGER.info("Registering user to MySQL server: " + LOGGER_UTIL.getValue(user));
		ResponseDto<Void> createResponse = create(user);
		if (!createResponse.isSuccess()) {
			throw new MySqlRegisrationException(createResponse, user);
		}

		LOGGER.info("MySQL registration has been finished successfullly");
		notificationService.send(EventBuilder.of().setMessage("RDBMS Registration: Ending")
				.addAdditional(() -> USERNAME, () -> user.getUsernameId()).build());
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
		return executeOperation(() -> ResponseDto.ok(userRepository.exists(user.getUsernameId())));
	}

	@Override
	@Transactional
	public ResponseDto<User> get(String usernameId) {
		LOGGER.info("Get user by its username: " + usernameId);
		return executeOperation(() -> Optional.ofNullable(userRepository.findByUsernameId(usernameId))
				.map(ResponseDto::ok).orElse(ResponseDto.fail("Could not find user by id: " + usernameId)));
	}

}
