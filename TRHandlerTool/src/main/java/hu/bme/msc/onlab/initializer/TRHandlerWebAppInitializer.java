package hu.bme.msc.onlab.initializer;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.common.collect.Lists;

import hu.bme.msc.onlab.config.LdapContextConfig;
import hu.bme.msc.onlab.config.MessageResolverContext;
import hu.bme.msc.onlab.config.MvcWebAppConfiguration;
import hu.bme.msc.onlab.config.MvcWebSecurityConfig;
import hu.bme.msc.onlab.config.ViewResolverContextConfig;

//Replaces web.xml when using XML-based config
public class TRHandlerWebAppInitializer implements WebApplicationInitializer {
	private final static List<Class<?>> configurationClasses = Lists.newArrayList(MvcWebAppConfiguration.class,
			MvcWebSecurityConfig.class, LdapContextConfig.class, ViewResolverContextConfig.class,
			MessageResolverContext.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// Create the default WebApp context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(configurationClasses.toArray(new Class<?>[configurationClasses.size()]));
		ctx.setServletContext(container);

		// Register DispatcherServlet with name: "root"
		ServletRegistration.Dynamic dispatcher = container.addServlet("root", new DispatcherServlet(ctx));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}
