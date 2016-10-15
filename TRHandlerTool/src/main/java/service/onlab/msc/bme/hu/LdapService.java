package service.onlab.msc.bme.hu;

import java.util.Arrays;
import java.util.List;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.Rdn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import exception.onlab.msc.bme.hu.LdapRegistrationException;
import exception.onlab.msc.bme.hu.RegistrationException;
import interf.ldap.dao.onlab.msc.bme.hu.ILdapDatabaseManager;
import interf.service.onlab.msc.bme.hu.ILdapService;
import ldap.model.onlab.msc.bme.hu.LdapUserEntry;
import ldap.model.onlab.msc.bme.hu.LdapUserGroupEntry;
import sql.model.onlab.msc.bme.hu.User;
import util.onlab.msc.bme.hu.LdapUtil;
import util.onlab.msc.bme.hu.ResponseDto;

@Service
@PropertySource("classpath:ldap.properties")
public class LdapService extends BaseService implements ILdapService {

	private static final String UID = "uid";

	private static final String UNIQUE_MEMBER = "uniqueMember";

	private static final String BASE_DN_KEY = "ldap.server.base";

	private static final String GROUP_DN_KEY = "ldap.server.default.user.group";

	@Autowired
	private ILdapDatabaseManager ldapDatabaseManager;

	@Autowired
	private Environment env;

	@Override
	public ResponseDto<LdapUserEntry> register(final User user) throws RegistrationException {
		LOGGER.info("Registering user to LDAP server: " + LOGGER_UTIL.getValue(user));
		LdapUserEntry ldapUserEntry = null;

		LOGGER.info("Getting and parsing base DN");
		ResponseDto<List<Rdn>> rdnsResponse = parseDnToRnds(env.getProperty(BASE_DN_KEY));
		checkOperationSuccess(rdnsResponse);

		LOGGER.info("Creating LdapUserEntry object");
		final ResponseDto<LdapUserEntry> ldapUserEntryResponse = createLdapUserEntry(user, rdnsResponse.getValue());
		checkOperationSuccess(ldapUserEntryResponse);
		ldapUserEntry = ldapUserEntryResponse.getValue();

		LOGGER.info("Checking if LdapUserEntry exists");
		final ResponseDto<Void> checkResponse = checkIfExists(ldapUserEntry);
		checkOperationSuccess(checkResponse);

		LOGGER.info("Adding LdapUserEntry to LDAP database");
		final ResponseDto<Void> createResponse = create(ldapUserEntry);
		checkOperationSuccess(createResponse, ldapUserEntry);

		LOGGER.info("Adding LdapUserEntry to the default groups");
		rdnsResponse = parseDnToRnds(env.getProperty(GROUP_DN_KEY));
		checkOperationSuccess(rdnsResponse, ldapUserEntry);

		final LdapUserGroupEntry defaultUserGroup = LdapUserGroupEntry.of(rdnsResponse.getValue());
		final List<ModificationItem> modificationItems = Arrays
				.asList(LdapUtil.getModificationItem(defaultUserGroup.getDn(), DirContext.ADD_ATTRIBUTE,
						new BasicAttribute(UNIQUE_MEMBER, ldapUserEntry.getUid())));
		ResponseDto<Void> modifyResponse = modify(defaultUserGroup, modificationItems);
		checkOperationSuccess(modifyResponse, ldapUserEntry);

		LOGGER.info("LDAP registration has been finished successfullly");
		return ResponseDto.ok(ldapUserEntry);
	}

	@Override
	public ResponseDto<Void> modify(LdapUserGroupEntry ldapUserGroupEntry, List<ModificationItem> modifications) {
		LOGGER.info("Modifying LdapUserGroupEntry: " + LOGGER_UTIL.getValue(ldapUserGroupEntry)
				+ " with modifications: " + LOGGER_UTIL.getValue(modifications));
		return executeOperation(() -> {
			try {
				modifications.forEach((modificationItem) -> {
					ldapDatabaseManager.modify(ldapUserGroupEntry.getDn(), modificationItem);
				});
				return ResponseDto.ok();
			} catch (NamingException e) {
				return ResponseDto.fail("Could not modify LdapUserEntry: " + ldapUserGroupEntry.toString(), e);
			}
		});
	}

	@Override
	public ResponseDto<Void> checkIfExists(LdapUserEntry ldapUserEntry) {
		LOGGER.info("Checking if LdapUserEntry exists: " + LOGGER_UTIL.getValue(ldapUserEntry.getDn()));
		return executeOperation(() -> {
			try {
				ldapDatabaseManager.findOne(LdapQueryBuilder.query().where(UID).is(ldapUserEntry.getUid()));
				LOGGER.info("LdapUserEntry is not exists in LDAP database: " + ldapUserEntry.toString());
				return ResponseDto.ok();
			} catch (IncorrectResultSizeDataAccessException e) {
				return ResponseDto.fail("LdapUserEntry already exists: " + ldapUserEntry.toString(), e);
			}
		});
	}

	@Override
	public ResponseDto<Void> create(LdapUserEntry ldapUserEntry) {
		LOGGER.info("Creating LdapUserEntry in LDAP database: " + LOGGER_UTIL.getValue(ldapUserEntry));
		return executeOperation(() -> {
			try {
				ldapDatabaseManager.create(ldapUserEntry);
				LOGGER.info("LdapUserEntry has been created in LDAP database");
				return ResponseDto.ok();
			} catch (NamingException | IllegalArgumentException e) {
				return ResponseDto.fail("Could not create LdapUserEntry: " + ldapUserEntry.toString(), e);
			}
		});
	}

	private ResponseDto<List<Rdn>> parseDnToRnds(String dn) {
		LOGGER.info("Parsing dn to rnds: " + LOGGER_UTIL.getValue(dn));
		return executeOperation(() -> {
			try {
				List<Rdn> rdns = LdapUtils.newLdapName(dn).getRdns();
				LOGGER.info("Parsing rdns has finished successfully");
				return ResponseDto.ok(rdns);
			} catch (IllegalArgumentException | InvalidNameException e) {
				return ResponseDto.fail("Could not parse DN: " + dn, e);
			}
		});
	}

	private ResponseDto<LdapUserEntry> createLdapUserEntry(User user, List<Rdn> dn) {
		LOGGER.info("Creating LdapUserEntry from User: " + LOGGER_UTIL.getValue(user) + ", and from RDNs: "
				+ LOGGER_UTIL.getValue(dn));
		return executeOperation(() -> {
			try {
				LdapUserEntry ldapUserEntry = LdapUserEntry.of(dn)
						.setCn(LdapUtil.getCn(user.getFirstname(), user.getLastname())).setSn(user.getLastname())
						.setGn(user.getFirstname()).setPassword(LdapUtil.getEncodedPassword(user.getPassword()));
				LOGGER.info("LdapUserEnrty has been created successfuly");
				return ResponseDto.ok(ldapUserEntry);
			} catch (IllegalArgumentException e) {
				return ResponseDto.fail("Could not create LdapUserEntry with baseDn: " + dn.toString(), e);
			}
		});
	}

	private void checkOperationSuccess(ResponseDto<?> response) throws LdapRegistrationException {
		checkOperationSuccess(response, null);
	}
	
	private void checkOperationSuccess(ResponseDto<?> response, LdapUserEntry ldapUserEntry) throws LdapRegistrationException {
		if (!response.isSuccess()) {
			throw new LdapRegistrationException(response, ldapUserEntry);
		}
	}
}
