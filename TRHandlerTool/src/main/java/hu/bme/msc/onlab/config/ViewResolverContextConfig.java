package hu.bme.msc.onlab.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

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
		bean.setTemplateResolver(springResourceTemplateResolver());
//		Add extra dialects
		Set<IDialect> additionalDialects = new HashSet<>();
		additionalDialects.add(new LayoutDialect());
		additionalDialects.add(new SpringSecurityDialect());
		bean.setAdditionalDialects(additionalDialects);
		return bean;
	}
	
	@Bean(name = "springResourceTemplateResolver")
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		SpringResourceTemplateResolver bean = new SpringResourceTemplateResolver();
		bean.setSuffix(".html");
		bean.setPrefix("/WEB-INF/page/");
		bean.setTemplateMode(TemplateMode.HTML);
//		TODO !!! Only for development !!!
		bean.setCacheable(false);
		return bean;
	}
}
