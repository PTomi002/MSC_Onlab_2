package service.onlab.msc.bme.hu;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import interf.ldap.dao.onlab.msc.bme.hu.ILdapDatabaseManager;
import interf.service.onlab.msc.bme.hu.ILdapService;
import ldap.model.onlab.msc.bme.hu.LdapUserEntry;
import sql.model.onlab.msc.bme.hu.User;
import util.onlab.msc.bme.hu.LdapUtil;
import util.onlab.msc.bme.hu.ResponseDto;

@Service
public class LdapService extends BaseService implements ILdapService{

	private static final String UNIQUE_MEMBER = "uniqueMember";
	
	private static final String BASE_DN_STRING = "dc=onlab,dc=msc,dc=bme,dc=hu"; 
	
	private static final Name BASE_DN = LdapUtils.newLdapName(BASE_DN_STRING);
	
	@Autowired
	private ILdapDatabaseManager ldapDatabaseManager;
	
	@Override
	public LdapUserEntry registerLdapEntry(User user) {
		LOGGER.info("Adding user to LDAP server: " + user.toString());
		LdapUserEntry ldapUserEntry = createLdapUserEntryFromUser(user);
	}
	
	private LdapUserEntry createLdapUserEntryFromUser(User user){
		LOGGER.info("Creating LdapUserEntry from user: " + user.toString());
		LdapUserEntry ldapUserEntry = null;
		
		ResponseDto<LdapUserEntry> result = executeOperation(() -> {
			return LdapUtil.parseStringToRdnList("") 
		}, );
	}
}
