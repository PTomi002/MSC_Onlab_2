package hu.bme.msc.onlab.framework;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.IResultListener2;

public class FailureListener implements IResultListener2 {
	private static final Logger LOGGER = LoggerFactory.getLogger("SystemErr");
	
	private static final String LINE_SEPARATOR = "<br/>";

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final void log(String message) {
		LOGGER.error(message);
		Reporter.log(FORMAT.format(new Date()) + " " + String.valueOf(LogLevel.ERROR) + " " + message + LINE_SEPARATOR);
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (!result.isSuccess()) {
			String stack = Optional.ofNullable(result.getThrowable()).map(exc -> {
				return ExceptionUtils.getStackTrace(exc);
			}).orElse("No trace was found!");
			log(stack);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
	}

	@Override
	public void beforeConfiguration(ITestResult tr) {
	}
}
