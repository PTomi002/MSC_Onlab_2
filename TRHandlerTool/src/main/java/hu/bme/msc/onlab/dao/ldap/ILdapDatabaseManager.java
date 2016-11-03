package hu.bme.msc.onlab.dao.ldap;

import javax.naming.Name;
import javax.naming.directory.ModificationItem;

import org.springframework.ldap.query.LdapQuery;

import hu.bme.msc.onlab.model.ldap.LdapUserEntry;

public interface ILdapDatabaseManager {
	public LdapUserEntry create(LdapUserEntry user);

	public void delete(LdapUserEntry user);

	public LdapUserEntry findOne(LdapQuery query);

	public Object lookup(Name dn);
	
	public void modify(Name dn, ModificationItem modificationItem);
}
