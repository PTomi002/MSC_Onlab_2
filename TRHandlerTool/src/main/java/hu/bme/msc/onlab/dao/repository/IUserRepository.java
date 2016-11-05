package hu.bme.msc.onlab.dao.repository;

import org.springframework.data.repository.CrudRepository;

import hu.bme.msc.onlab.model.sql.User;

public interface IUserRepository extends CrudRepository<User, String> {
	
}
