package hu.bme.msc.onlab.service;

import java.util.Arrays;
import java.util.List;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.Rdn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.ldap.InvalidNameException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import hu.bme.msc.onlab.dao.ldap.interf.ILdapDatabaseManager;
import hu.bme.msc.onlab.exception.LdapEntryExistsException;
import hu.bme.msc.onlab.exception.LdapUnknownRegistrationException;
import hu.bme.msc.onlab.exception.RegistrationException;
import hu.bme.msc.onlab.model.ldap.LdapUserEntry;
import hu.bme.msc.onlab.model.ldap.LdapUsersGroupEntry;
import hu.bme.msc.onlab.model.sql.User;
import hu.bme.msc.onlab.service.interf.ILdapService;
import hu.bme.msc.onlab.util.LdapUtil;
import hu.bme.msc.onlab.util.ResponseDto;

@Service
public class LdapService extends BaseService implements ILdapService {

	public static final String UID = "uid";

	public static final String UNIQUE_MEMBER = "uniqueMember";

	@Autowired
	private ILdapDatabaseManager ldapDatabaseManager;

	@Override
	public ResponseDto<LdapUserEntry> register(final User user) throws RegistrationException {
		LOGGER.info("Registering user to LDAP server: " + LOGGER_UTIL.getValue(user));
		LdapUserEntry ldapUserEntry = null;

		LOGGER.info("Getting and parsing base DN");
		ResponseDto<List<Rdn>> rdnsResponse = parseDnToRnds(LdapUtil.getRelativeDnToTheBaseDn(user.getUsername()));
		checkOperationSuccess(rdnsResponse);

		LOGGER.info("Creating LdapUserEntry object");
		final ResponseDto<LdapUserEntry> ldapUserEntryResponse = createLdapUserEntry(user, rdnsResponse.getValue());
		checkOperationSuccess(ldapUserEntryResponse);
		ldapUserEntry = ldapUserEntryResponse.getValue();

		LOGGER.info("Checking if LdapUserEntry exists");
		final ResponseDto<Void> checkResponse = checkIfExists(ldapUserEntry);
		if (!checkResponse.isSuccess()) {
			throw new LdapEntryExistsException(checkResponse).setUser(user);
		}
			
		LOGGER.info("Adding LdapUserEntry to LDAP database");
		final ResponseDto<Void> createResponse = create(ldapUserEntry);
		checkOperationSuccess(createResponse, user);

		LOGGER.info("Adding LdapUserEntry to the default groups");
		final LdapUsersGroupEntry defaultUserGroup = LdapUsersGroupEntry.of();
		final List<ModificationItem> modificationItems = Arrays.asList(LdapUtil.getModificationItem(
				DirContext.ADD_ATTRIBUTE, new BasicAttribute(UNIQUE_MEMBER, ldapUserEntry.getFullDn())));
		ResponseDto<Void> modifyResponse = modify(defaultUserGroup, modificationItems);
		checkOperationSuccess(modifyResponse, user);

		LOGGER.info("LDAP registration has been finished successfullly");
		return ResponseDto.ok(ldapUserEntry);
	}

	@Override
	public ResponseDto<Void> unRegister(final User user) {
		LOGGER.info("Unregistering user from LDAP server: " + LOGGER_UTIL.getValue(user));
		LOGGER.info("Getting and parsing base DN");
		final ResponseDto<List<Rdn>> rdnsResponse = parseDnToRnds(
				LdapUtil.getRelativeDnToTheBaseDn(user.getUsername()));
		LOGGER_UTIL.errorIfNotOk(rdnsResponse);
		
		LOGGER.info("Creating LdapUserEntry object");
		final ResponseDto<LdapUserEntry> ldapUserEntryResponse = createLdapUserEntry(user, rdnsResponse.getValue());
		final LdapUserEntry ldapUserEntry = ldapUserEntryResponse.getValue();
		LOGGER_UTIL.errorIfNotOk(ldapUserEntryResponse);

		LOGGER.info("Deleting LdapUserEntry from the default groups");
		final LdapUsersGroupEntry defaultUserGroup = LdapUsersGroupEntry.of();
		final List<ModificationItem> modificationItems = Arrays.asList(LdapUtil.getModificationItem(
				DirContext.REMOVE_ATTRIBUTE, new BasicAttribute(UNIQUE_MEMBER, ldapUserEntry.getFullDn())));
		LOGGER_UTIL.errorIfNotOk(modify(defaultUserGroup, modificationItems));
		
		LOGGER.info("Deleting LdapUserEntry from LDAP database");
		LOGGER_UTIL.errorIfNotOk(delete(ldapUserEntry));

		LOGGER.info("LDAP unregistration has been finished successfullly");
		return ResponseDto.ok();
	}

	@Override
	public ResponseDto<Void> modify(LdapUsersGroupEntry group, List<ModificationItem> modifications) {
		LOGGER.info("Modifying LdapUserGroupEntry: " + LOGGER_UTIL.getValue(group) + " with modifications: "
				+ LOGGER_UTIL.getValue(modifications));
		return executeOperation(() -> {
			try {
				modifications.forEach((modificationItem) -> {
					ldapDatabaseManager.modify(group.getDn(), modificationItem);
				});
				return ResponseDto.ok();
			} catch (NamingException e) {
				return ResponseDto.fail("Could not modify ldap group: " + group.toString(), e);
			}
		});
	}

	@Override
	public ResponseDto<Void> checkIfExists(LdapUserEntry ldapUserEntry) {
		LOGGER.info("Checking if LdapUserEntry exists: " + LOGGER_UTIL.getValue(ldapUserEntry.getDn()));
		return executeOperation(() -> {
			try {
				ldapDatabaseManager.findOne(LdapQueryBuilder.query().where(UID).is(ldapUserEntry.getUid()));
				LOGGER.error("LdapUserEntry exists in LDAP database: " + ldapUserEntry.toString());
				return ResponseDto.fail("LdapUserEntry exists in LDAP database: " + ldapUserEntry.toString());
			} catch (IncorrectResultSizeDataAccessException e) {
				LOGGER.info("LdapUserEntry does not exists in LDAP database: " + ldapUserEntry.toString());
				return ResponseDto.ok();
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
				return ResponseDto.fail("Could not create LdapUserEntry: " + LOGGER_UTIL.getValue(ldapUserEntry), e);
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
						.setUid(user.getUsername()).setGn(user.getFirstname())
						.setPassword(LdapUtil.getEncodedPassword(user.getPassword()));
				LOGGER.info("LdapUserEnrty has been created successfuly");
				return ResponseDto.ok(ldapUserEntry);
			} catch (IllegalArgumentException e) {
				return ResponseDto.fail("Could not create LdapUserEntry with baseDn: " + dn.toString(), e);
			}
		});
	}

	private void checkOperationSuccess(ResponseDto<?> response) throws RegistrationException {
		checkOperationSuccess(response, null);
	}

	private void checkOperationSuccess(ResponseDto<?> response, User user) throws RegistrationException {
		if (!response.isSuccess()) {
			LdapUnknownRegistrationException exc = new LdapUnknownRegistrationException(response);
			exc.setUser(user);
			throw exc;
		}
	}

	@Override
	public ResponseDto<Void> delete(LdapUserEntry ldapUserEntry) {
		LOGGER.info("Deleting LdapUserEntry: " + LOGGER_UTIL.getValue(ldapUserEntry));
		return executeOperation(() -> {
			try {
				ldapDatabaseManager.delete(ldapUserEntry);
				return ResponseDto.ok();
			} catch (Exception e) {
				return ResponseDto.fail("Could not delete entry: " + LOGGER_UTIL.getValue(ldapUserEntry), e);
			}
		});
	}

}
