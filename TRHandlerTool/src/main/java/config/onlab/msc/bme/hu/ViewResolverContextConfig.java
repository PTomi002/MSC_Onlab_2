package config.onlab.msc.bme.hu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ViewResolverContextConfig {
//	-----------------------------------------------------------------
//	-----------------------------------------------------------------
//	||		Setting default VievResolver backend to: Thymeleaf		||
//	-----------------------------------------------------------------
//	-----------------------------------------------------------------
	@Bean(name = "thymeleafViewResolver")
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver bean = new ThymeleafViewResolver();
		bean.setTemplateEngine(springTemplateEngine());
		return bean;
	}
	
	@Bean(name = "springTemplateEngine")
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
	
	@Bean(name = "servletContextTemplateResolver")
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
