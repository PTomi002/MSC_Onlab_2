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
	private static final String DASHBOARD_PATH = "/dashboard";
	private static final String INDEX_PAGE_PATH = "/";

//	----------------------------------------------
//	----------------------------------------------
//	||		Configure HTTP auth.				||
//	----------------------------------------------		
//	----------------------------------------------
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(INDEX_PAGE_PATH).permitAll()
				.antMatchers(DASHBOARD_PATH).authenticated()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage(INDEX_PAGE_PATH)
					.defaultSuccessUrl(DASHBOARD_PATH)
					.failureUrl(INDEX_PAGE_PATH + "error=true")
			.and()
				.logout()
					.deleteCookies(JSESSIONID)
					.logoutSuccessUrl(INDEX_PAGE_PATH + "logout")
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
