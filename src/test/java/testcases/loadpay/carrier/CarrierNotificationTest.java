package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNotification;
import util.TestUtil;

public class CarrierNotificationTest extends TestBase {

	CarrierNotification cn;
	CarrierLoginPage cl;
	String payment_status = "Verified";
	String invoice;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/

	public CarrierNotificationTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		cn = new CarrierNotification();
		cl = new CarrierLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getCarrierLoginData")
	public void loginCarrier(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = un;
			carrierPassword = pwd;
		}

		cl = new CarrierLoginPage();
		cl.Carrierlogin(carrierUsername, carrierPassword);

	}

	@Test(dependsOnMethods = "loginCarrier")
	public void carrierNotification() throws InterruptedException {
		cn = new CarrierNotification();
		cn.clickNotifications();
		cn.carrierInvoice();
		cl.CarrierLogout();

	}

}
