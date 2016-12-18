package hu.bme.msc.onlab.model.ldap;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "organizationalUnit", "top" }, base = "ou=MHO")
public class MHOGroup implements Serializable {

	private static final long serialVersionUID = 6331317543478443690L;

	@Id
	private Name dn;
	
	@Attribute(name = "ou")
	private String ou;
	
	public MHOGroup() {
		// Needed for LdapTemplate instantiation.
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	@Override
	public String toString() {
		return "MHO [dn=" + dn + ", ou=" + ou + "]";
	}
}
