package interf.service.onlab.msc.bme.hu;

import java.util.List;

import javax.naming.directory.ModificationItem;

import exception.onlab.msc.bme.hu.RegistrationException;
import ldap.model.onlab.msc.bme.hu.LdapUserEntry;
import ldap.model.onlab.msc.bme.hu.LdapUserGroupEntry;
import sql.model.onlab.msc.bme.hu.User;
import util.onlab.msc.bme.hu.ResponseDto;

public interface ILdapService {
	public ResponseDto<LdapUserEntry> register(User user) throws RegistrationException;
	
	public ResponseDto<Void> checkIfExists(LdapUserEntry ldapUserEntry);
	
	public ResponseDto<Void> create(LdapUserEntry ldapUserEntry);
	
	public ResponseDto<Void> modify(LdapUserGroupEntry ldapUserGroupEntry, List<ModificationItem> modifications);
}
