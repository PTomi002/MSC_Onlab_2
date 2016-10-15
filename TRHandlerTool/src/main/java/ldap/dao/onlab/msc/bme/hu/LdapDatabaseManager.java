package ldap.dao.onlab.msc.bme.hu;

import javax.naming.Name;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import interf.ldap.dao.onlab.msc.bme.hu.ILdapDatabaseManager;
import ldap.model.onlab.msc.bme.hu.LdapUserEntry;

@Repository
public class LdapDatabaseManager implements ILdapDatabaseManager {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public LdapUserEntry create(LdapUserEntry user) {
		ldapTemplate.create(user);
		return user;
	}

	@Override
	public void delete(LdapUserEntry user) {
		ldapTemplate.delete(user);
	}

	@Override
	public LdapUserEntry findOne(LdapQuery query) {
		return ldapTemplate.findOne(query, LdapUserEntry.class);
	}

	@Override
	public Object lookup(Name dn) {
		return ldapTemplate.lookup(dn);
	}

	@Override
	public void modify(Name dn, ModificationItem modificationItem) {
		ModificationItem modifications[] = new ModificationItem[1];
		modifications[0] = modificationItem;
		ldapTemplate.modifyAttributes(dn, modifications);
	}
}
