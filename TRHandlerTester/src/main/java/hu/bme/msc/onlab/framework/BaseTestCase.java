package hu.bme.msc.onlab.framework;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.Assertion;

import hu.bme.msc.onlab.framework.entity.SUT;
import hu.bme.msc.onlab.framework.util.SUTHolder;

public abstract class BaseTestCase {

	// ~ Fields
	// ================================================================================================
	protected final Logger testLogger = LoggerFactory.getLogger(getClass());

	private static final String FAILURE_REASON_IS_NOT_SPECIFIED = "Failure reason is not specified!";

	protected static final String EXCEPTION_HAPPENED = "Exception happened ";

	private static final Assertion HARD = new Assertion();

	// private static final SoftAssert SOFT = new SoftAssert();

	protected final SUT STANDALONE = SUTHolder.getIstance().getSUT();

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String LINE_SEPARATOR = "<br/>";

	// ~ Methods
	// ================================================================================================
	@BeforeMethod(alwaysRun = true)
	protected void setup(final Method testMethod) {
		setTestInfo("<--------------- START TEST ---------------->");
		setTestInfo("TEST NAME:            " + testMethod.getName());
		setTestInfo("<------------------------------------------->");
	}

	@AfterMethod(alwaysRun = true)
	protected void teardown(final ITestResult testResult) {
		setTestInfo("<--------------- TEST RESULT --------------->");
		setTestInfo("TEST FUNCTIONAL AREAS: " + Arrays.asList(testResult.getMethod().getGroups()));
		setTestInfo("TEST RESULT:           " + testResult.isSuccess());
		setTestInfo("<------------------------------------------->");
	}

	// ~ Asserts
	// ================================================================================================
	protected static void fail(String message) {
		Optional<String> opt = Optional.ofNullable(message);
		if (opt.isPresent()) {
			HARD.fail(message);
		} else {
			HARD.fail(FAILURE_REASON_IS_NOT_SPECIFIED);
		}
	}

	protected static void assertTrue(String message, boolean condition) {
		Optional<String> opt = Optional.ofNullable(message);
		if (opt.isPresent()) {
			HARD.assertTrue(condition, message);
		} else {
			HARD.assertTrue(condition, FAILURE_REASON_IS_NOT_SPECIFIED);
		}
	}

	// ~ Logging
	// ================================================================================================
	protected void setTestInfo(String msg) {
		Optional.ofNullable(msg).ifPresent(m -> {
			testLogger.info(msg);
			logToReport(msg, LogLevel.INFO);
		});
	}

	protected void setTestWarning(String msg) {
		Optional.ofNullable(msg).ifPresent(m -> {
			testLogger.warn(msg);
			logToReport(msg, LogLevel.WARNING);
		});
	}

	private void logToReport(String msg, LogLevel logLevel) {
		Reporter.log(FORMAT.format(new Date()) + " " + String.valueOf(logLevel) + " " + msg + LINE_SEPARATOR);
	}
}
