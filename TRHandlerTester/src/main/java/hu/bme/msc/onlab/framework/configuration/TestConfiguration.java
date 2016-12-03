package hu.bme.msc.onlab.framework.configuration;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConfiguration extends Properties {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TestConfiguration.class);

	private static final long serialVersionUID = 8643649087294809034L;

	// Initialization-on-demand holder idiom - thread safe
	private static class TestConfigurationHolder {
		static final TestConfiguration INSTANCE = new TestConfiguration();
	}

	public static TestConfiguration getInstance() {
		return TestConfigurationHolder.INSTANCE;
	}

	private TestConfiguration() {
	}
	
	public Integer getIntegerProperty(String key) {
		final String numberString = getProperty(key);
		Integer number = null;

		if (numberString != null) {
			try {
				number = Integer.parseInt(numberString);
			} catch (NumberFormatException e) {
				LOGGER.warn("Could not parse string (" + numberString + ") to integer!", e);
			}
		}

		return number;
	}
}
