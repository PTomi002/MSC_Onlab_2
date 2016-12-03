package hu.bme.msc.onlab.test.authentication;

import org.testng.annotations.Test;

import hu.bme.msc.onlab.test.BaseTestCase;

public class InteractiveLoginTest extends BaseTestCase {
	@Test(groups = {
			"AUTHENTICATION, LOGIN, INTERACTIVE_LOGIN" }, description = "Test Case: AUTHENTICATION(AUTH):LOGIN(LOGIN):INTERACTIVE(INTERA) 01", dependsOnGroups = {
					"AVAILABILITY_PRECONDITION" })
	public void TC_AUTH_LOGIN_INTERA_01() {
		setTestInfo("Run");
	}
}
