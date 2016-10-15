package interf.ldap.dao.onlab.msc.bme.hu;

import javax.naming.Name;
import javax.naming.directory.ModificationItem;

import org.springframework.ldap.query.LdapQuery;

import ldap.model.onlab.msc.bme.hu.LdapUserEntry;

public interface ILdapDatabaseManager {
	public LdapUserEntry create(LdapUserEntry user);

	public void delete(LdapUserEntry user);

	public LdapUserEntry findOne(LdapQuery query);

	public Object lookupLdapUserEntry(Name dn);
	
	public void modify(Name dn, ModificationItem modificationItem);
}
