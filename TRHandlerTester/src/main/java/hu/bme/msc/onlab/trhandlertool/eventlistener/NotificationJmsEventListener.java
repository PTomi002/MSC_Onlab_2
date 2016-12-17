package hu.bme.msc.onlab.trhandlertool.eventlistener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.testng.SkipException;

import com.google.common.collect.Lists;

import hu.bme.msc.onlab.framework.eventhandling.EventListener;

public class NotificationJmsEventListener extends BaseEventListener implements EventListener {

	private AtomicReference<List<Message>> notifications = new AtomicReference<List<Message>>(
			Collections.synchronizedList(Lists.newArrayList()));

	private AtomicBoolean run = new AtomicBoolean(true);

	private final String openwireUrl;
	
	public NotificationJmsEventListener(String name, String url) {
		super(name);
		openwireUrl = url;
	}

	@Override
	public void start() throws SkipException {
		LOGGER.info("Starting " + name + " notification event listener");
	}

	@Override
	public void stop() {

	}

	private class JmsThread implements Runnable {

		@Override
		public void run() {
			
//			Connection connection = null;
//			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:6000");
//			connection = connectionFactory.createConnection();
//			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//			try {
//				Topic queue = session.createTopic("notification_service.topic");
//
//				// Consumer
//				MessageConsumer consumer = session.createConsumer(queue);
//				connection.start();
//				System.out.println("Waiting for messages");
//				Message msg = consumer.receive();
//				System.out.println(msg.toString());
//			} finally {
//				if (session != null) {
//					session.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			}

			while (run.get()) {
				
			}
		}

	}
}
