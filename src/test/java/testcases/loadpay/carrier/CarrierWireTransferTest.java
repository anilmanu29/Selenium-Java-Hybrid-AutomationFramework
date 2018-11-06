package testcases.loadpay.carrier;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierWireTransfer;
import util.TestUtil;

public class CarrierWireTransferTest extends TestBase {

	CarrierWireTransfer cw;
	CarrierLoginPage loginPage;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/
	public CarrierWireTransferTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		cw = new CarrierWireTransfer();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getCarrierLoginData")
	public void loginTest(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

	}

	@Test(dependsOnMethods = "loginTest")
	public void carrierPaymenowWirTransfer() throws InterruptedException {
		cw.getAmount();

		cw.clickPaymenow();

		cw.getwiretransferAmount();

		cw.clickSelectButton();

		cw.clickConfirmButton();

		cw.clickPaidTab();

		cw.gettotalpaiyAmount();

		cw.verifywiretransfer();
	}

}
