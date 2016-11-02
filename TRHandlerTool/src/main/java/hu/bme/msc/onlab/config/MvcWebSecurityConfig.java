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
	private static final String JSESSIONID = "JSESSIONID";
	private static final String DASHBOARD = "/dashboard";
	private static final String APPLICATION_REALM = "/dashboard/*";
	private static final String WELCOME = "/welcome";
	private static final String SIGNUP = "/signup";
	private static final String LOGIN = "/login";

//	----------------------------------------------
//	----------------------------------------------
//	||		Configure HTTP auth.				||
//	----------------------------------------------		
//	----------------------------------------------
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.mvcMatchers(WELCOME).permitAll()
				.mvcMatchers(SIGNUP).permitAll()
				.mvcMatchers(APPLICATION_REALM).authenticated()
				.anyRequest().permitAll()
			.and()
				.formLogin()
					.loginPage(LOGIN)
					.defaultSuccessUrl(DASHBOARD)
					.failureUrl(WELCOME + "?error=true")
			.and()
				.logout()
					.deleteCookies(JSESSIONID)
					.logoutSuccessUrl(WELCOME)
					.invalidateHttpSession(true);
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
