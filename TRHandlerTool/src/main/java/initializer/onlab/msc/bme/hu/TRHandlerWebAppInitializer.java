package initializer.onlab.msc.bme.hu;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import config.onlab.msc.bme.hu.LdapContextConfig;
import config.onlab.msc.bme.hu.MvcWebAppConfiguration;
import config.onlab.msc.bme.hu.MvcWebSecurityConfig;
import config.onlab.msc.bme.hu.ViewResolverContextConfig;

//Replaces web.xml when using XML-based config
public class TRHandlerWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
//		Create the default WebApp context
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(MvcWebAppConfiguration.class, MvcWebSecurityConfig.class, LdapContextConfig.class, ViewResolverContextConfig.class);
		ctx.setServletContext(container);

//		Register DispatcherServlet with name: "root"
		ServletRegistration.Dynamic dispatcher = container.addServlet("root", new DispatcherServlet(ctx));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

}
