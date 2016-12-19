package hu.bme.msc.onlab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"hu.bme.msc.onlab.listener"})
public class MvcWebSecurityConfig extends WebSecurityConfigurerAdapter{
	// ----------------------------------------------
	// Static attributes
	
	private static final String PATH_LOGOUT = "/j_spring_security_logout";
	private static final String J_P = "j_password";
	private static final String J_USERNAME = "j_username";
	private static final String J_SPRING_SECURITY_CHECK = "/j_spring_security_check";
	private static final String JSESSIONID = "JSESSIONID";
	private static final String PATH_DASHBOARD = "/dashboard";
	private static final String PATH_APPLICATION_REALM = PATH_DASHBOARD + "/**";
	private static final String PATH_WELCOME = "/welcome";
	private static final String PATH_SIGNIN = "/signin";
	private static final String PATH_ADMIN = PATH_DASHBOARD + "/admin"; 
	private static final String PATH_FAILURE_LOGIN = PATH_SIGNIN + "?invalid=true";
	
	// ----------------------------------------------
	// Autowired attributes
	
	@Autowired
	private LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProvider;
	
	// ----------------------------------------------
	// Application default roles
	
	private static final String ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
	private static final String ROLE_USER = "hasRole('ROLE_USER')";
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// * matches for zero or more character
				// ** matches for zero or more character including /
				.mvcMatchers(PATH_DASHBOARD, PATH_APPLICATION_REALM).access(ROLE_USER)
				.mvcMatchers(PATH_ADMIN).access(ROLE_ADMIN)
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage(PATH_SIGNIN)
					.loginProcessingUrl(J_SPRING_SECURITY_CHECK)
					.defaultSuccessUrl(PATH_WELCOME)
					.failureUrl(PATH_FAILURE_LOGIN)
					.usernameParameter(J_USERNAME)
					.passwordParameter(J_P)
			.and()
				.logout()
					.deleteCookies(JSESSIONID)
					.logoutSuccessUrl(PATH_WELCOME)
					.invalidateHttpSession(true)
					.logoutUrl(PATH_LOGOUT)
			.and()
				.headers()
					.frameOptions()
							.sameOrigin();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// LDAP authentication
		ldapAuthenticationProvider.configure(auth);
	}
}
