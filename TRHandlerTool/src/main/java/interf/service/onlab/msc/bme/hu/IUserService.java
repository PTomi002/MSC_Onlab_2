package interf.service.onlab.msc.bme.hu;

import exception.onlab.msc.bme.hu.RegistrationException;
import sql.model.onlab.msc.bme.hu.User;
import util.onlab.msc.bme.hu.ResponseDto;

public interface IUserService {
	public ResponseDto<User> register(User user) throws RegistrationException;
}
