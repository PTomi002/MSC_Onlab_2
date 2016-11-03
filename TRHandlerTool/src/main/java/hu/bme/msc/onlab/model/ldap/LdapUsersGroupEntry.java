package hu.bme.msc.onlab.model.ldap;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;

public final class LdapUsersGroupEntry {
	private static final String DN = "cn=Users,ou=Groups";

	private Name dn;

	public LdapUsersGroupEntry() {
		try {
			this.dn = new LdapName(DN);
		} catch (InvalidNameException e) {
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
