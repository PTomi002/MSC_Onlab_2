package service.onlab.msc.bme.hu;

import org.springframework.stereotype.Service;

import exception.onlab.msc.bme.hu.RegistrationException;
import interf.service.onlab.msc.bme.hu.IUserService;
import sql.model.onlab.msc.bme.hu.User;
import util.onlab.msc.bme.hu.ResponseDto;

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
