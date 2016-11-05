package hu.bme.msc.onlab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MvcWebSecurityConfig extends WebSecurityConfigurerAdapter{
	private static final String J_PASSWORD = "j_password";
	private static final String J_USERNAME = "j_username";
	private static final String J_SPRING_SECURITY_CHECK = "/j_spring_security_check";
	private static final String JSESSIONID = "JSESSIONID";
	private static final String DASHBOARD = "/dashboard";
	private static final String APPLICATION_REALM = DASHBOARD + "/**";
	private static final String WELCOME = "/welcome";
	private static final String SIGNIN = "/signin";
	private static final String ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
	private static final String ROLE_USER = "hasRole('ROLE_USER')";
//	----------------------------------------------
//	----------------------------------------------
//	||		Configure HTTP auth.				||
//	----------------------------------------------		
//	----------------------------------------------
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers(DASHBOARD, APPLICATION_REALM).access(ROLE_USER)
				.mvcMatchers("/admin").access(ROLE_ADMIN)
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage(SIGNIN)
					.loginProcessingUrl(J_SPRING_SECURITY_CHECK)
					.defaultSuccessUrl(DASHBOARD)
					.failureUrl(SIGNIN + "?invalid=true")
					.usernameParameter(J_USERNAME)
					.passwordParameter(J_PASSWORD)
			.and()
				.logout()
					.deleteCookies(JSESSIONID)
					.logoutSuccessUrl(WELCOME)
					.invalidateHttpSession(true)
			.and()
				.exceptionHandling()
					.accessDeniedPage("");
//			.and()
//				.csrf();
	}
	
//	----------------------------------------------
//	----------------------------------------------
//	||		Configure auth possibilities.		||
//	----------------------------------------------		
//	----------------------------------------------
	@Autowired
	private LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		LDAP authentication
		ldapAuthenticationProvider.configure(auth);
	}
}
