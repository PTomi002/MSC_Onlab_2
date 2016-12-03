package hu.bme.msc.onlab.trhandlertool.test.authentication;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;

import hu.bme.msc.onlab.trhandlertool.test.AbstractTestCase;

public class InteractiveLoginTest extends AbstractTestCase {
	@Test(
		groups = { "AUTHENTICATION", "LOGIN", "INTERACTIVE_LOGIN" },
		description = "Test Case: AUTHENTICATION(AUTH):LOGIN(LOGIN):INTERACTIVE_LOGIN(INTERA) 01",
		dependsOnGroups = {"LOGIN_PRECONDITION" })
	public void TC_AUTH_LOGIN_INTERA_01() {
		try (final WebClient client = new WebClient()) {
//			client.getPage(new URL(STANDALONE.get));
		}
	}
}
