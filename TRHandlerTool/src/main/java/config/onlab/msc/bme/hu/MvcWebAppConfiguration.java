package config.onlab.msc.bme.hu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "controller.onlab.msc.bme.hu" })
//Replaces root-servlet.xml when using XML-based config
public class MvcWebAppConfiguration extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/**").addResourceLocations("/public/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		Set default servlet name to "root"
		configurer.enable("root");
	}

//	-----Setting default VievResolver------
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver bean = new ThymeleafViewResolver();
		bean.setTemplateEngine(springTemplateEngine());
		return bean;
	}
	
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine bean = new SpringTemplateEngine();
		bean.setTemplateResolver(servletContextTemplateResolver());
//		Add extra dialects
		Set<IDialect> additionalDialects = new HashSet<>();
		additionalDialects.add(new LayoutDialect());
		additionalDialects.add(new SpringSecurityDialect());
		bean.setAdditionalDialects(additionalDialects);
		return bean;
	}
	
	@Bean
	public ServletContextTemplateResolver servletContextTemplateResolver() {
		ServletContextTemplateResolver bean = new ServletContextTemplateResolver();
		bean.setSuffix(".html");
		bean.setPrefix("/WEB-INF/page/");
		bean.setTemplateMode("HTML5");
//		TODO !!! Only for development !!!
		bean.setCacheable(false);
		return bean;
	}
}
