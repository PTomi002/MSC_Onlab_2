package hu.bme.msc.onlab.framework;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TESTING {

	public static void main(String[] args) throws Exception {
		Connection connection = null;
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:6000");
		connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		try {
			Topic queue = session.createTopic("notification_service.topic");

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			System.out.println("Waiting for messages");
			Message msg = consumer.receive();
			System.out.println(msg.toString());
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

}
