package hu.bme.msc.onlab.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.TestNGException;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import com.beust.jcommander.internal.Lists;

import hu.bme.msc.onlab.configuration.PropertyKey;
import hu.bme.msc.onlab.configuration.TestConfiguration;
import hu.bme.msc.onlab.entity.SUT;
import hu.bme.msc.onlab.util.Closer;
import hu.bme.msc.onlab.util.SUTHolder;

public class Main {
	private static final int INDEX_2 = 2;

	private static final int INDEX_1 = 1;

	private static final int INDEX_0 = 0;

	private static final int ARGS_NUMBER_EXPECTED = 3;

	private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOGGER.info("Checking arguments");
		checkArgs(args, ARGS_NUMBER_EXPECTED);

		LOGGER.info("Reading arguments");
		final String testxmlFilePath = parseConfiguratonFile(args, INDEX_0);
		final String testConfigFilePath = parseConfiguratonFile(args, INDEX_1);

		LOGGER.info("Get test configuration files");
		InputStream testXmlFile = fetchTestConfigurationFile(testxmlFilePath);
		InputStream testConfigFile = fetchTestConfigurationFile(testConfigFilePath);

		LOGGER.info("Initialize test configuration");
		initTestConfiguration(testConfigFile);

		LOGGER.info("Initialize System Under Test");
		final TestConfiguration config = TestConfiguration.getInstance();
		SUT standalone = initSystemUnderTest(config);

		LOGGER.info("Storing SUT");
		SUTHolder.getInstance(standalone);

		LOGGER.info("Parsing Test XML");
		List<XmlSuite> tests = parseTestXml(testXmlFile);
		Closer.close(Arrays.asList(testXmlFile, testConfigFile));

		LOGGER.info("Creating test output dir, if not exists");
		final String outputDir = parseTestOutputDir(args, INDEX_2);

		LOGGER.info("Configuring TestNG framework");
		TestNG testng = new TestNG();
		testng.setXmlSuites(tests);
		testng.setOutputDirectory(outputDir);
		testng.addListener(new FailureListener());

		LOGGER.info("Redirecting System out/err to the Loggers");
		redirectSystemOutput();

		LOGGER.info("<====================================>");
		LOGGER.info("        Running Test Framework");
		LOGGER.info("<====================================>");
		try {
			testng.run();
		} catch (TestNGException e) {
			LOGGER.error("TestNG exception happened!", e);
		}
		LOGGER.info("<====================================>");
		LOGGER.info("             Shutting down");
		LOGGER.info("<====================================>");
	}

	private static void redirectSystemOutput() {
		// Redirecting
		// org.testng.Reporter.log(String, boolean)
		System.setOut(new SystemOutputPrintStream(System.out));
		System.setErr(new SystemErrorPrintStream(System.err));
	}

	private static String parseTestOutputDir(String[] args, int index) {
		String path = StringUtils.EMPTY;
		try {
			LOGGER.info("Command line argument(" + index + "): " + args[index]);
			File outputDir = new File(args[index]);
			if (!outputDir.exists() && !outputDir.mkdirs()) {
				LOGGER.error("Could not create test resuts output dir!");
				System.exit(1);
			} else {
				LOGGER.info("Output dir exists, using it");
			}
			path = outputDir.getAbsolutePath();
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("Did not found command line argument at position: " + index, e);
			System.exit(1);
		}
		return path;
	}

	private static String parseConfiguratonFile(String[] args, int index) {
		String path = StringUtils.EMPTY;
		try {
			LOGGER.info("Command line argument(" + index + "): " + args[index]);
			File file = new File(args[index]);
			if (!file.exists()) {
				LOGGER.error("Test configuration file does not exist: " + args[index]);
				System.exit(1);
			}
			path = file.getAbsolutePath();
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("Did not found command line argument at position: " + index, e);
			System.exit(1);
		}
		return path;
	}

	private static void checkArgs(String[] args, int expectedArgsNumber) {
		if (args.length != expectedArgsNumber) {
			LOGGER.error("Command line aruments are not matching, needed: " + expectedArgsNumber + " ,but found: "
					+ args.length);
			System.exit(1);
		}

		Arrays.asList(args).stream().forEach((arg) -> {
			LOGGER.info("Command line argument: " + arg);
			if (StringUtils.isEmpty(arg)) {
				LOGGER.error("Found a null or empty argument!");
				System.exit(1);
			}
		});
	}

	private static List<XmlSuite> parseTestXml(InputStream testXmlFile) {
		List<XmlSuite> result = Lists.newArrayList();
		try {
			result.addAll(new Parser(testXmlFile).parseToList());
		} catch (Exception e) {
			LOGGER.error("Could not parse Test.xml file!", e);
			System.exit(1);
		}
		return result;
	}

	private static InputStream fetchTestConfigurationFile(String filePath) {
		InputStream file = null;
		try {
			LOGGER.info("Get an input stream for file: " + filePath);
			file = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			LOGGER.error("Could not find file on path: " + filePath, e);
			System.exit(1);
		}
		return file;
	}

	private static SUT initSystemUnderTest(final TestConfiguration config) {
		SUT sut = null;
		try {
			final String host = config.getProperty(PropertyKey.HOST_DOMAIN);
			final int port = config.getIntegerProperty(PropertyKey.HOST_PORT);

			LOGGER.info("Checking host: " + host);
			InetAddress inetAddress = InetAddress.getByName(host);

			LOGGER.info("Checking port: " + port);
			new InetSocketAddress(inetAddress, port);

			LOGGER.info("Creating SUT...");
			sut = new SUT(inetAddress).setPort(port);

			LOGGER.info("Adding service URLs");
			addSutServiceUrl(PropertyKey.URL_WELCOME, config, sut);
			addSutServiceUrl(PropertyKey.URL_WELCOME_EMPTY, config, sut);
		} catch (UnknownHostException | IllegalArgumentException e) {
			LOGGER.error("Could not initialize System Under Test!", e);
			System.exit(1);
		}
		return sut;
	}

	private static void addSutServiceUrl(String property, TestConfiguration config, SUT sut) {
		String url = config.getProperty(property);
		LOGGER.info("Adding SUT service URL (key: " + property + "): " + url);
		sut.addSutUrl(property, url);
	}

	private static void initTestConfiguration(InputStream testConfigFile) {
		try {
			LOGGER.info("Loading test configuration");
			TestConfiguration.getInstance().load(testConfigFile);
		} catch (IOException | IllegalArgumentException e) {
			LOGGER.error("Could not get properties, bailing out...", e);
			System.exit(1);
		}
	}
}
