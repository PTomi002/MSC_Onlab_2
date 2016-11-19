package hu.bme.msc.onlab.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@PropertySource("classpath:pages/url.properties")
@ComponentScan(basePackages = { "hu.bme.msc.onlab.controller" , "hu.bme.msc.onlab.service", "hu.bme.msc.onlab.dao", "hu.b.e.msc.onlab.controller.advice"})
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
	
//	@Bean(name = "propertyPlaceholderConfigurer")
//	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//		propertyPlaceholderConfigurer.setLocations(
//				new ClassPathResource("pages/url.properties"));
//		
//		return propertyPlaceholderConfigurer;
//	}
}
