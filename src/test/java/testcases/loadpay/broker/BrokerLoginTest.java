package testcases.loadpay.broker;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;

public class BrokerLoginTest extends TestBase {
	BrokerLoginPage loginPage;

	public BrokerLoginTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		loginPage = new BrokerLoginPage();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		loginPage.Brokerlogin(user, pass);

		wait.until(ExpectedConditions.elementToBeClickable(tempElement));
		loginPage.BrokerLogout();
	}

}