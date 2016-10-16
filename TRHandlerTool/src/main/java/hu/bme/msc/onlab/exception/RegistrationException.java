package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.util.ResponseDto;

public class RegistrationException extends Exception {

	private static final long serialVersionUID = -7017191303205901069L;

	public RegistrationException(String message) {
		super(message);
	}

	public RegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrationException(ResponseDto<?> cause) {
		super(cause.getReason(), cause.getException());
	}
}
