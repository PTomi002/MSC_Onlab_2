package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.util.ResponseDto;

public class MySqlRegisrationException extends RegistrationException {

	private static final long serialVersionUID = 7995725927305904624L;

	public MySqlRegisrationException(String message) {
		super(message);
	}

	public MySqlRegisrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySqlRegisrationException(ResponseDto<?> cause) {
		super(cause.getReason(), cause.getException());
	}
}
