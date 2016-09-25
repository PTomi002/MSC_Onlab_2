package initializer.onlab.msc.bme.hu;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import config.onlab.msc.bme.hu.AppConfiguration;

public class TRHandlerWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfiguration.class);
		ctx.setServletContext(container);

		// Register DispatcherServlet with name: "root"
		ServletRegistration.Dynamic dispatcher = container.addServlet("root", new DispatcherServlet(ctx));
		dispatcher.addMapping("/");
	}

}
