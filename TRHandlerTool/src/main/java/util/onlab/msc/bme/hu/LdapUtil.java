package util.onlab.msc.bme.hu;

import java.util.List;

import javax.naming.ldap.Rdn;

import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.support.LdapUtils;

public final class LdapUtil {
	public static ResponseDto<List<Rdn>> parseStringToRdnList(String dn) {
		try {
			return ResponseDto.ok(LdapUtils.newLdapName(dn).getRdns());
		} catch (InvalidNameException e) {
			return ResponseDto.fail("Could not parse DN: " + dn, e);
		}
	}
}
