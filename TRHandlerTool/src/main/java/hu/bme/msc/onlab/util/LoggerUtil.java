package hu.bme.msc.onlab.util;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.google.common.base.Joiner;

public final class LoggerUtil {
	private final Logger logger;

	private static final String DELIMETER = " ";

	private LoggerUtil(Logger logger) {
		this.logger = logger;
	}

	public static LoggerUtil getLoggerUtil(Logger logger) {
		return new LoggerUtil(logger);
	}

	public String getValue(Object object) {
		return Optional.ofNullable(object).map(Object::toString).orElse("[value is null]");
	}

	public void error(ResponseDto<?> responseDto) {
		error(StringUtils.EMPTY, responseDto);
	}

	public void errorIfNotOk(ResponseDto<?> responseDto) {
		if (!responseDto.isSuccess()) {
			error(StringUtils.EMPTY, responseDto);
		}
	}

	public void error(String message, ResponseDto<?> responseDto) {
		String errorMessage = Joiner.on(DELIMETER).join(message, "[" + responseDto.getReason() + "]");
		if (responseDto.getException() == null) {
			logger.error(errorMessage);
		} else {
			logger.error(errorMessage, responseDto.getException());
		}
	}
}
