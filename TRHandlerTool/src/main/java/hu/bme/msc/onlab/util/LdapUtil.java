package hu.bme.msc.onlab.util;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.ModificationItem;

import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;

public final class LdapUtil {
	private static final String CN_DELIMETER = " ";

	private LdapUtil() {
	}

	public static final ModificationItem getModificationItem(int operation, BasicAttribute basicAttribute)
			throws IllegalArgumentException {
		Assert.notNull(basicAttribute, "BasicAttribute must be not null");
		return new ModificationItem(operation, basicAttribute);
	}

	public static final String getCn(String firstName, String lastName) throws IllegalArgumentException {
		Assert.notNull(firstName, "FirstName must be not null");
		Assert.notNull(lastName, "LastName must be not null");
		return Joiner.on(CN_DELIMETER).join(firstName, lastName);
	}

	public static final String getEncodedPassword(String rawPassword) throws IllegalArgumentException {
		Assert.notNull(rawPassword, "Password must be not null");
		return new LdapShaPasswordEncoder().encodePassword(rawPassword, null);
	}
	
	public static final String getRelativeDnToTheBaseDn(String uid) {
		return Joiner.on(",").join("uid=" + uid, "ou=People");
	}
}
