package hu.bme.msc.onlab.model.ldap;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LdapUsersGroupEntry {
	private static final Logger LOGGER = LoggerFactory.getLogger(LdapUsersGroupEntry.class);

	private static final String DN = "cn=Users,ou=Groups";

	private Name dn;

	public LdapUsersGroupEntry() {
		try {
			this.dn = new LdapName(DN);
		} catch (InvalidNameException e) {
			LOGGER.error("Invalid javax Ldap Name!", e);
		}
	}

	public static LdapUsersGroupEntry of() {
		return new LdapUsersGroupEntry();
	}

	public Name getDn() {
		return dn;
	}

	@Override
	public String toString() {
		return "LdapUsersGroupEntry [dn=" + dn + "]";
	}
}
