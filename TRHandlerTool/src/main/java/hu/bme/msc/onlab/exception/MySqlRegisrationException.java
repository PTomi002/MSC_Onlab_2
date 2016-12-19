package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public class MySqlRegisrationException extends RegistrationException {

	private static final long serialVersionUID = 7995725927305904624L;

	public MySqlRegisrationException(String message) {
		super(message, null);
	}
	
	public MySqlRegisrationException(String message, User user) {
		super(message, user);
	}

	public MySqlRegisrationException(String message, Throwable cause) {
		super(message, null, cause);
	}
	
	public MySqlRegisrationException(String message, User user, Throwable cause) {
		super(message, user, cause);
	}

	public MySqlRegisrationException(ResponseDto<?> cause) {
		super(cause.getReason(), null, cause.getException());
	}
	
	public MySqlRegisrationException(ResponseDto<?> cause, User user) {
		super(cause.getReason(), user, cause.getException());
	}
}
