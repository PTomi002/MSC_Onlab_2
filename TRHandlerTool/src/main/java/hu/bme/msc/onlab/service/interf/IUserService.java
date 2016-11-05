package hu.bme.msc.onlab.service.interf;

import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public interface IUserService {
	public ResponseDto<User> register(User user) throws RegistrationException;
	
	public ResponseDto<Void> create(User user);
	
	public ResponseDto<Boolean> exists(User user);
}
