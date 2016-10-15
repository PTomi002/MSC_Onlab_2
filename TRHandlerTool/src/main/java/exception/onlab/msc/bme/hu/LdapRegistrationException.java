package exception.onlab.msc.bme.hu;

import ldap.model.onlab.msc.bme.hu.LdapUserEntry;
import util.onlab.msc.bme.hu.ResponseDto;

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
