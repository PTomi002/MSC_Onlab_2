package hu.bme.msc.onlab.trhandlertool.test.availability;

import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

import hu.bme.msc.onlab.framework.configuration.TestConfiguration;
import hu.bme.msc.onlab.trhandlertool.driver.DriverFactory;
import hu.bme.msc.onlab.trhandlertool.driver.PingDriver;
import hu.bme.msc.onlab.trhandlertool.test.AbstractTestCase;
import hu.bme.msc.onlab.trhandlertool.util.PropertyKey;
import hu.bme.msc.onlab.trhandlertool.util.ResponseDto;

public class AvailabilityTest extends AbstractTestCase {
	
	@BeforeMethod(alwaysRun = true)
	protected void abstractSetUp() {
//		setTestInfo("AbstractSetUp");
	}
	
	@AfterMethod(alwaysRun = true)
	protected void abstractTearDown() {
//		setTestInfo("AbstractTearDown");
	}
	
	@Test(
		groups = { "AVAILABILITY", "SERVICE_AVAILABILITY", "SERVER_AVAILABILITY" }, 
		description = "Test Case: AVAILABILITY:SERVICE_AVAILABILITY(SERVER_AVAILABILITY) 01")
	public void TC_AV_SERV_01() {
		setTestInfo("Ping web service");
		final InetAddress host = STANDALONE.getHost();
		final int port = STANDALONE.getPort();

		setTestInfo("Host to ping: " + host.getHostAddress());
		setTestInfo("Port to ping: " + port);
		PingDriver ping = DriverFactory.getPingDriver(STANDALONE);

		setTestInfo("Start pinging server...");
		assertTrue("Ping failed, service is unavailable!", ping.ping());
		setTestInfo("Ping successful, server is available");
	}
	
	@Test(
		groups = { "AVAILABILITY", "SERVICE_AVAILABILITY", "MYSQL_AVAILABILITY" }, 
		description = "Test Case: AVAILABILITY:SERVICE_AVAILABILITY(MYSQL_AVAILABILITY) 02")
	public void TC_AV_SERV_02() {
		setTestInfo("Getting mysql server properties");
		final String host = TestConfiguration.getInstance().getProperty(PropertyKey.MYSQL_HOST);
		final Integer port = TestConfiguration.getInstance().getIntegerProperty(PropertyKey.MYSQL_PORT);
		assertNotNull("MySQL host property is null!", host);
		assertNotNull("MySQL port property is null!", port);
		
		setTestInfo("Ping MySql service, if it is running or not");
		try {
			PingDriver pingDriver = DriverFactory.getPingDriver(InetAddress.getByName(host), port);
			setTestInfo("Start pinging MySql server...");
			assertTrue("Could not ping MySql service", pingDriver.ping());
		} catch (Exception e) {
			fail("Could not ping MySql service!", e);
		}
		setTestInfo("Ping successful, MySql service is available");
	}

	@Test(
		groups = { "AVAILABILITY", "SERVICE_AVAILABILITY", "LDAP_AVAILABILITY" }, 
		description = "Test Case: AVAILABILITY:SERVICE_AVAILABILITY(LDAP_AVAILABILITY) 01")
	public void TC_AV_SERV_03() {
		setTestInfo("Getting LDAP service properties");
		final String host = TestConfiguration.getInstance().getProperty(PropertyKey.LDAP_HOST);
//		if it is a primitive type, then during wrapping a Nullpointer is raised, if the property can not be found
		final Integer port = TestConfiguration.getInstance().getIntegerProperty(PropertyKey.LDAP_PORT);
		assertNotNull("LDAP host property is null!", host);
		assertNotNull("LDAP port property is null!", port);
		
		setTestInfo("Ping LDAP service, if it is running or not");
		try {
			PingDriver pingDriver = DriverFactory.getPingDriver(InetAddress.getByName(host), port);
			setTestInfo("Start pinging LDAP service...");
			assertTrue("Could not ping LDAP service", pingDriver.ping());
		} catch (Exception e) {
			fail("Could not ping MySql service!", e);
		}
		setTestInfo("Ping successful, LDAP service is available");
	}
	
	@Test(
		groups = { "AVAILABILITY", "PAGE_AVAILABILITY", "PAGE_WELCOME_AVAILABILITY"},
		description = "Test Case: AVAILABILITY:PAGE_AVAILABILITY(PAGE_WELCOME_AVAILABILITY) 01",
		dependsOnGroups = {"SERVER_AVAILABILITY" })
	public void TC_AV_PGAV_01() {
		final String welcomePage = STANDALONE.getSutUrl(PropertyKey.URL_WELCOME);

		setTestInfo("Creating web client");
		try (final WebClient client = new WebClient()) {
			setTestInfo("Disabling JavaScript engine");
			client.getOptions().setJavaScriptEnabled(false);

			setTestInfo("Assemble URL for requesting: " + welcomePage + " page");
			final ResponseDto<URL> welcomeResponse = getTargetedURL(STANDALONE, welcomePage);
			assertTrue("Could not assemble URL for page: " + welcomePage, welcomeResponse);

			final URL welcomeUrl = welcomeResponse.getValue();
			setTestInfo("Requesting page: " + welcomeUrl.toString());
			HtmlPage page = client.getPage(welcomeUrl);
			
			setTestInfo("Check page title");
			final String title = "Welcome | TR Handler Tool";
			saveAssertTrue("Page title mismatch, current: " + page.getTitleText() + " ,but expected: " + title, checkPageTitle(page, title));
			
			setTestInfo("Getting side menu");
			List<?> result = getPageElementsByXPath(page, "//ul[@class='sidebar-nav']");
			
			setTestInfo("Checking side menu");
			saveAssertTrue("There should be only one unorddered list <ul> element with class 'side-navbar'", result.size() == 1);
			saveAssertNotNull("Unordered list (<ul>) element is null", result.get(0));
			HtmlUnorderedList ul = (HtmlUnorderedList) result.get(0);
			saveAssertEquals("Unordered list (<ul>) element should contain 3 list elements (<li>)", ul.getChildElementCount(), 3);
			
//			setTestInfo("Checking side menu elements: Sign up/Sign in/Search");
//			List<String> listElements = Arrays.asList("Sign up", "Sign in", "Search");
//			StreamSupport.stream(ul.getChildren().spliterator(), false)
//				.
//			
//			setTestInfo("Checking side menu element: Sign in");
//			
//			
//			setTestInfo("Checking side menu element: Search");
			
		} catch (Exception e) {
			fail("Exception happened during TC execution!", e);
		}
	}
}