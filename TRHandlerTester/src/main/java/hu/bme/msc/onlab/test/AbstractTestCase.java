package hu.bme.msc.onlab.test;

import java.net.InetAddress;

import org.testng.annotations.Test;

import hu.bme.msc.onlab.driver.DriverFactory;
import hu.bme.msc.onlab.driver.PingDriver;
import hu.bme.msc.onlab.framework.BaseTestCase;

public abstract class AbstractTestCase extends BaseTestCase {
	@Test(
		groups = {"AUTHENTICATION", "LOGIN", "LOGIN_PRECONDITION" },
		description = "Test Case: AUTHENTICATION(AUTH):LOGIN(LOGIN):PRECONDITION(PRE) 01")
	public void TC_AUTH_LOGIN_PRE_01() {
		setTestInfo("Ping web service");
		final InetAddress host = STANDALONE.getHost();
		final int port = STANDALONE.getPort();

		setTestInfo("Host to ping: " + host.getHostAddress());
		setTestInfo("Port to ping: " + port);
		PingDriver ping = DriverFactory.getPingDriver(STANDALONE);

		setTestInfo("Starting ping service");
		assertTrue("Ping failed, service is unavailable!", ping.ping());
		setTestInfo("Ping successful, service is available");
	}
}
