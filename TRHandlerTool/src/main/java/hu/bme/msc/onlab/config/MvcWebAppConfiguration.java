package hu.bme.msc.onlab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "controller.onlab.msc.bme.hu" , "service.onlab.msc.bme.hu", "dao.onlab.msc.bme.hu"})
//Replaces root-servlet.xml when using XML-based config
public class MvcWebAppConfiguration extends WebMvcConfigurerAdapter {
//	----------------------------------------------
//	----------------------------------------------
//	||		Setting default config				||
//	----------------------------------------------		
//	----------------------------------------------
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/**").addResourceLocations("/public/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		Set default servlet name to "root"
		configurer.enable("root");
	}
}
