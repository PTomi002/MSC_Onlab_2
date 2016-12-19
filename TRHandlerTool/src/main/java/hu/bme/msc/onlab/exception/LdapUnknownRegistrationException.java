package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public class LdapUnknownRegistrationException extends RegistrationException {
	private static final long serialVersionUID = -2619738990557928610L;

	public LdapUnknownRegistrationException(String message) {
		super(message, null);
	}
	
	public LdapUnknownRegistrationException(String message, User user) {
		super(message, user);
	}

	public LdapUnknownRegistrationException(String message, Throwable cause) {
		super(message, null, cause);
	}
	
	public LdapUnknownRegistrationException(String message, User user, Throwable cause) {
		super(message, user, cause);
	}

	public LdapUnknownRegistrationException(ResponseDto<?> cause) {
		super(cause.getReason(), null, cause.getException());
	}
	
	public LdapUnknownRegistrationException(ResponseDto<?> cause, User user) {
		super(cause.getReason(), user, cause.getException());
	}
}
