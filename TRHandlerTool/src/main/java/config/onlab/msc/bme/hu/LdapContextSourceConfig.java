package config.onlab.msc.bme.hu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapContextSourceConfig {
	@Autowired
	private Environment env;

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(env.getRequiredProperty("ldap.server.url"));
		contextSource.setBase(env.getRequiredProperty("ldap.server.base"));
		contextSource.setUserDn(env.getRequiredProperty("ldap.server.user"));
		contextSource.setPassword(env.getRequiredProperty("ldap.server.password"));
		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
}
