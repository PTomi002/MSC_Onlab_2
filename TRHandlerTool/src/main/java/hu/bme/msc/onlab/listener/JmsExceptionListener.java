package hu.bme.msc.onlab.listener;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

public class JmsExceptionListener extends BaseListener implements ExceptionListener{

	private static final String EXC_MESSAGE = "JMS exception happened!";
	
	@Override
	public void onException(JMSException exception) {
		LOGGER.error(EXC_MESSAGE, exception);
	}

}
