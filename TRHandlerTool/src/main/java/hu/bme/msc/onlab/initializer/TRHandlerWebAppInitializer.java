package hu.bme.msc.onlab.initializer;

import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.common.collect.Lists;

import hu.bme.msc.onlab.config.JmsConfiguration;
import hu.bme.msc.onlab.config.JpaContextConfiguration;
import hu.bme.msc.onlab.config.LdapContextConfig;
import hu.bme.msc.onlab.config.MessageResolverContext;
import hu.bme.msc.onlab.config.MvcWebAppConfiguration;
import hu.bme.msc.onlab.config.MvcWebSecurityConfig;
import hu.bme.msc.onlab.config.ViewResolverContextConfig;
import hu.bme.msc.onlab.filter.UserNameFilter;

//Replaces web.xml when using XML-based config
public class TRHandlerWebAppInitializer implements WebApplicationInitializer {
	private static final String FILTER_CHAIN_PATTERN = "/*";

	private static final String DEFAULT_MAPPING = "/";

	private static final String SERVLET_NAME = "root";

	private final static List<Class<?>> CONFIGURATION_CLASSES = Lists.newArrayList(MvcWebAppConfiguration.class,
			MvcWebSecurityConfig.class, LdapContextConfig.class, ViewResolverContextConfig.class,
			MessageResolverContext.class, JpaContextConfiguration.class, JmsConfiguration.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(CONFIGURATION_CLASSES.toArray(new Class<?>[CONFIGURATION_CLASSES.size()]));
		ctx.setServletContext(container);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(ctx));

		// Needed for LoggedUserListener
		container.addListener(new RequestContextListener());

		// Create dispatcher servlet
		DispatcherServlet disp = new DispatcherServlet(ctx);
		// Throw exception if page was not found, replaces the error part in
		// web.xml
		disp.setThrowExceptionIfNoHandlerFound(true);

		// Register DispatcherServlet with name: "root"
		ServletRegistration.Dynamic dispatcher = container.addServlet(SERVLET_NAME, disp);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(DEFAULT_MAPPING);

		// Adding springSecurityFilter to the Filter Chain
		FilterRegistration.Dynamic securityFilter = container
				.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class);
		securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, FILTER_CHAIN_PATTERN);

		// Adding userNameFilter to the Filter Chain
		FilterRegistration.Dynamic userNameFilter = container.addFilter(UserNameFilter.DEFAULT_FILTER_NAME,
				UserNameFilter.class);
		userNameFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, FILTER_CHAIN_PATTERN);
	}

}
