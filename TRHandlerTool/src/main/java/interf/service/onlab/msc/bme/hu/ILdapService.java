package interf.service.onlab.msc.bme.hu;

import ldap.model.onlab.msc.bme.hu.LdapUserEntry;
import sql.model.onlab.msc.bme.hu.User;

public interface ILdapService {
	public LdapUserEntry registerLdapEntry(User user);
}
