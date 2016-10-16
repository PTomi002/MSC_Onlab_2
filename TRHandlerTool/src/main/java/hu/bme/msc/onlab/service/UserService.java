package hu.bme.msc.onlab.service;

import org.springframework.stereotype.Service;

import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Service
public class UserService extends BaseService implements IUserService{

//	@Override
//	public ResponseDto<User> createRegistrationDate(User user) {
//		LOGGER.info("Creating reistration date to user: " + user.toString());
//		user.setRegistration_date(new Date());
//		return ResponseDto.ok(user);
//	}

	@Override
	public ResponseDto<User> register(User user) throws RegistrationException {
		// TODO Auto-generated method stub
		return null;
	}

}
