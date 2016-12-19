package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public class RegistrationException extends Exception {

	private static final long serialVersionUID = -7017191303205901069L;

	private final User user;
	
	public RegistrationException(String message, User user) {
		super(message);
		this.user = user;
	}

	public RegistrationException(String message, User user, Throwable cause) {
		super(message, cause);
		this.user = user;
	}

	public RegistrationException(ResponseDto<?> cause, User user) {
		super(cause.getReason(), cause.getException());
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
