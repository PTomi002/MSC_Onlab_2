package hu.bme.msc.onlab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.support.JmsGatewaySupport;

import hu.bme.msc.onlab.dto.SystemNotification;

public class NotificationJmsService extends JmsGatewaySupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationJmsService.class);
	
	public void send(SystemNotification systemNotification) {
		LOGGER.info("Sending SystemNotification: " + systemNotification.toString());
		try {
			getJmsTemplate().convertAndSend(systemNotification);
		} catch (Exception e) {
			LOGGER.error("Could not send JMS message!", e);
		}
	}
	
}
