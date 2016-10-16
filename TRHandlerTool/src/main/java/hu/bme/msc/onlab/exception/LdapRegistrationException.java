package hu.bme.msc.onlab.exception;

import hu.bme.msc.onlab.model.ldap.LdapUserEntry;
import hu.bme.msc.onlab.util.ResponseDto;

public class LdapRegistrationException extends RegistrationException {

	private static final long serialVersionUID = 5717629400984798987L;

	private final LdapUserEntry ldapUserEntry;
	
	public LdapRegistrationException(ResponseDto<?> cause, LdapUserEntry ldapUserEntry) {
		super(cause.getReason(), cause.getException());
		this.ldapUserEntry = ldapUserEntry;
	}

	public LdapUserEntry getLdapUserEntry() {
		return ldapUserEntry;
	}
}
