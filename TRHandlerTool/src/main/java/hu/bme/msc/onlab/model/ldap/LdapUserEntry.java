package hu.bme.msc.onlab.model.ldap;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.google.common.base.Joiner;

@Entry(objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top", "uidObject" }, base = "ou=People")
public final class LdapUserEntry implements Serializable {
	private static final long serialVersionUID = -717903404461870212L;

	@Id
	private Name dn;

	@Attribute(name = "uid")
	private String uid;

	@Attribute(name = "givenName")
	private String gn;

	@Attribute(name = "sn")
	private String sn;

	@Attribute(name = "cn")
	private String cn;

	@Attribute(name = "userPassword")
	private String password;

	public LdapUserEntry() {
	}
	
	private LdapUserEntry(List<Rdn> rnds) {
		this.dn = new LdapName(rnds);
	}
	
	public static LdapUserEntry of(List<Rdn> rdns) {
		return new LdapUserEntry(rdns);
	}

	public Name getDn() {
		return dn;
	}

	public LdapUserEntry setDn(Name dn) {
		this.dn = dn;
		return this;
	}

	public String getUid() {
		return uid;
	}

	public LdapUserEntry setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public String getGn() {
		return gn;
	}

	public LdapUserEntry setGn(String gn) {
		this.gn = gn;
		return this;
	}

	public String getSn() {
		return sn;
	}

	public LdapUserEntry setSn(String sn) {
		this.sn = sn;
		return this;
	}

	public String getCn() {
		return cn;
	}

	public LdapUserEntry setCn(String cn) {
		this.cn = cn;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public LdapUserEntry setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getFullDn() {
		return Joiner.on(",").join("uid=" + getUid(), "ou=People,dc=onlab,dc=msc,dc=bme,dc=hu");
	}
	
	@Override
	public String toString() {
		return "LdapUserEntry [dn=" + dn + ", uid=" + uid + ", gn=" + gn + ", sn=" + sn + ", cn=" + cn + "]";
	}
}
