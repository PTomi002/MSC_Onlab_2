package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public class LdapEntryExistsException extends RegistrationException {
	private static final long serialVersionUID = 3424331862892056235L;

	public LdapEntryExistsException(String message) {
		super(message, null);
	}
	
	public LdapEntryExistsException(String message, User user) {
		super(message, user);
	}

	public LdapEntryExistsException(String message, Throwable cause) {
		super(message, null, cause);
	}
	
	public LdapEntryExistsException(String message, User user, Throwable cause) {
		super(message, user, cause);
	}

	public LdapEntryExistsException(ResponseDto<?> cause) {
		super(cause.getReason(), null, cause.getException());
	}
	
	public LdapEntryExistsException(ResponseDto<?> cause, User user) {
		super(cause.getReason(), user, cause.getException());
	}
}
