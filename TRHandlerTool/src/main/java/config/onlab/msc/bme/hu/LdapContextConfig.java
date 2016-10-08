package config.onlab.msc.bme.hu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;

@Configuration
@PropertySource("classpath:ldap.properties")
public class LdapContextConfig {
//	----------------------------------------------
//	----------------------------------------------
//	||		Setting ldap server					||
//	----------------------------------------------		
//	----------------------------------------------
	@Autowired
	private Environment env;

	@Bean(name = "ldapContextSource")
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(env.getRequiredProperty("ldap.server.url"));
		contextSource.setBase(env.getRequiredProperty("ldap.server.base"));
		contextSource.setUserDn(env.getRequiredProperty("ldap.server.user"));
		contextSource.setPassword(env.getRequiredProperty("ldap.server.password"));
		return contextSource;
	}

	@Bean(name = "ldapTemplate")
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
	
//	-------------------------------------------------
//	-------------------------------------------------
//	||		Setting ldap auth provider configurer	||
//	-------------------------------------------------	
//	-------------------------------------------------
	@Bean(name = "ldapAuthenticationProvider")
	public LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProvider(){
		LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> config = new LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder>();
		return config.contextSource(contextSource());
	}
}
