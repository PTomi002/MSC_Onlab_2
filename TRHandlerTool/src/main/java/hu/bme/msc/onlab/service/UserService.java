package hu.bme.msc.onlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.bme.msc.onlab.dao.repository.IUserRepository;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.IUserService;
import hu.bme.msc.onlab.util.ResponseDto;

@Service
@Transactional
public class UserService extends BaseService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public ResponseDto<User> register(User user) throws RegistrationException {
		userRepository.save(user);
		return null;
	}

}
