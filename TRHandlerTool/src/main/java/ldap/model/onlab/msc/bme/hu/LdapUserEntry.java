package ldap.model.onlab.msc.bme.hu;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top" }, base = "ou=user")
public class LdapUserEntry implements Serializable {
	private static final long serialVersionUID = -717903404461870212L;

	@Id
	private Name dn;

	@Attribute(name = "uid")
	private String uid;

	@Attribute(name = "gn")
	private String gn;

	@Attribute(name = "sn")
	private String sn;

	@Attribute(name = "cn")
	private String cn;

	@Attribute(name = "password")
	private String password;

	private LdapUserEntry(List<Rdn> rnds) {
		this.dn = new LdapName(rnds);
	}
	
	public static LdapUserEntry of(List<Rdn> rdns) {
		return new LdapUserEntry(rdns);
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGn() {
		return gn;
	}

	public void setGn(String gn) {
		this.gn = gn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LdapUserEntry [dn=" + dn + ", uid=" + uid + ", gn=" + gn + ", sn=" + sn + ", cn=" + cn + "]";
	}
}
