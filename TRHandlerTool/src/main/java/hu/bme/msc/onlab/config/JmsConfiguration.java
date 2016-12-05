package hu.bme.msc.onlab.config;

import java.time.Duration;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.activemq.xbean.BrokerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import hu.bme.msc.onlab.listener.JmsExceptionListener;
import hu.bme.msc.onlab.service.NotificationJmsService;

//ONLY FOR Operations & Maintenance USAGE
//TODO ONLY FOR DEVEOPMENT!!

@Configuration
public class JmsConfiguration {
	@Bean(name = "connectionFactory")
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://localhost:6000");
		activeMQConnectionFactory.setExceptionListener(new JmsExceptionListener());
		return activeMQConnectionFactory;
	}

	@Bean(name = "activeMQTopic")
	public ActiveMQTopic activeMQTopic() {
		ActiveMQTopic activeMQTopic = new ActiveMQTopic();
		activeMQTopic.setPhysicalName("notification_service.topic");
		return activeMQTopic;
	}

	@Bean(name = "jmsTemplate")
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setDefaultDestination(activeMQTopic());
		jmsTemplate.setMessageConverter(getDefaultConverter());
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setTimeToLive(Duration.ofSeconds(5).toMillis());
		// Publishing to a topic
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	
	private MarshallingMessageConverter getDefaultConverter() {
		MarshallingMessageConverter converter = new MarshallingMessageConverter();
		converter.setMarshaller(getDefaultMarshaller());
		return converter;
	}

	private Marshaller getDefaultMarshaller() {
		Jaxb2Marshaller jaxbContext = new Jaxb2Marshaller();
		jaxbContext.setPackagesToScan("hu.bme.msc.onlab.dto");
		return jaxbContext;
	}
	
	@Bean(name = "notificationJmsService")
	// Defining this bean here instead of using @Component annotation
	public NotificationJmsService notificationJmsService() {
		NotificationJmsService notificationJmsService = new NotificationJmsService();
		notificationJmsService.setJmsTemplate(jmsTemplate());
		return notificationJmsService;
	}
	
	@Bean
	public BrokerFactoryBean brokerFactoryBean(){
		BrokerFactoryBean brokerFactoryBean = new BrokerFactoryBean();
		brokerFactoryBean.setStart(true);
		brokerFactoryBean.setConfig(new ClassPathResource("activemq_broker/activemq.xml"));
		return brokerFactoryBean;
	}
}
