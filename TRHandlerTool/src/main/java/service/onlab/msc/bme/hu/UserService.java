package service.onlab.msc.bme.hu;

import java.util.Date;

import org.springframework.stereotype.Service;

import interf.service.onlab.msc.bme.hu.IUserService;
import sql.model.onlab.msc.bme.hu.User;

@Service
public class UserService extends BaseService implements IUserService{

	@Override
	public User createRegistrationDate(User user) {
		LOGGER.info("Creating reistration date to user: " + user.toString());
		user.setRegistration_date(new Date());
		return user;
	}

}
