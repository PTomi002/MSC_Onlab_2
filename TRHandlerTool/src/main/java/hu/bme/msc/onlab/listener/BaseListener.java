package hu.bme.msc.onlab.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.bme.msc.onlab.util.LoggerUtil;

public abstract class BaseListener {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	protected final LoggerUtil LOGGER_UTIL = LoggerUtil.getLoggerUtil(LOGGER);
}
