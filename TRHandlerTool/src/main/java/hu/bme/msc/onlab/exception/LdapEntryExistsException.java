package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.util.ResponseDto;

public class LdapEntryExistsException extends RegistrationException {
	private static final long serialVersionUID = 3424331862892056235L;

	public LdapEntryExistsException(String message) {
		super(message);
	}

	public LdapEntryExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public LdapEntryExistsException(ResponseDto<?> cause) {
		super(cause.getReason(), cause.getException());
	}
}
