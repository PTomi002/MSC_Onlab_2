package ldap.model.onlab.msc.bme.hu;

import java.util.List;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

public class LdapUserGroupEntry {
	private Name dn;
	
	private LdapUserGroupEntry(List<Rdn> rnds) {
		this.dn = new LdapName(rnds);
	}
	
	public static LdapUserGroupEntry of(List<Rdn> rdns) {
		return new LdapUserGroupEntry(rdns);
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	@Override
	public String toString() {
		return "LdapUserGroupEntry [dn=" + dn + "]";
	}
}
