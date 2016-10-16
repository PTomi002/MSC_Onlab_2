package hu.bme.msc.onlab.config;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResolverContext {
	private static final Charset ENCODING = Charset.forName("UTF-8");
	private static final String BASENAME = "Messages";

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename(BASENAME);
		resourceBundleMessageSource.setDefaultEncoding(ENCODING.name());
		return resourceBundleMessageSource;
	}
}
