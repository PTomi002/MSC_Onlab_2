package hu.bme.msc.onlab.service.interf;

import java.util.List;

import javax.naming.directory.ModificationItem;

import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.ldap.LdapUserEntry;
import hu.bme.msc.onlab.model.ldap.LdapUserGroupEntry;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.util.ResponseDto;

public interface ILdapService {
	public ResponseDto<LdapUserEntry> register(User user) throws RegistrationException;
	
	public ResponseDto<Void> checkIfExists(LdapUserEntry ldapUserEntry);
	
	public ResponseDto<Void> create(LdapUserEntry ldapUserEntry);
	
	public ResponseDto<Void> modify(LdapUserGroupEntry ldapUserGroupEntry, List<ModificationItem> modifications);
}
