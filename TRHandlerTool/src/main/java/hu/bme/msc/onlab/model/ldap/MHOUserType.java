package hu.bme.msc.onlab.model.ldap;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "groupOfUniqueNames", "top" })
public class MHOUserType implements Serializable {
	
	private static final long serialVersionUID = 6616442795099719516L;
	
	@Id
	private Name dn;
	
	@Attribute(name = "cn")
	private String cn;
	
	@Attribute(name = "uniqueMember")
	private String uniqueMember;
	
	@Attribute(name = "businessCategory")
	private String businessCategory;
	
	public MHOUserType() {
	}

	public Name getDn() {
		return dn;
	}

	public void setDn(Name dn) {
		this.dn = dn;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getUniqueMember() {
		return uniqueMember;
	}

	public void setUniqueMember(String uniqueMember) {
		this.uniqueMember = uniqueMember;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}
}
