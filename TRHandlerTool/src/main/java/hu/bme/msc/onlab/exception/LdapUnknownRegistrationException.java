package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.util.ResponseDto;

public class LdapUnknownRegistrationException extends RegistrationException {
	private static final long serialVersionUID = -2619738990557928610L;

	public LdapUnknownRegistrationException(String message) {
		super(message);
	}

	public LdapUnknownRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public LdapUnknownRegistrationException(ResponseDto<?> cause) {
		super(cause.getReason(), cause.getException());
	}
}
