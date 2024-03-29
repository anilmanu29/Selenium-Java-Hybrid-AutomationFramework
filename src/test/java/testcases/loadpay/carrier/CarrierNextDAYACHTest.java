package testcases.loadpay.carrier;

import java.awt.AWTException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import util.TestUtil;

public class CarrierNextDAYACHTest extends TestBase {

	CarrierNextDAYACH carrierNextDayObj;
	CarrierLoginPage loginPage;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/
	public CarrierNextDAYACHTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		carrierNextDayObj = new CarrierNextDAYACH();
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
	public void carrierPaymenowNextDAYACH() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		carrierNextDayObj.getAmount();
		carrierNextDayObj.clickPaymenow();
		carrierNextDayObj.getnextdayAmount();
		carrierNextDayObj.clickSelectButton();
		carrierNextDayObj.clickConfirmButton();

		if (loginPage.getDonotshowagaincheckbox().isDisplayed()) {
			loginPage.getDonotshowagaincheckbox().click();
			loginPage.getPayMeNowPopupSaveButton().click();
		}

		carrierNextDayObj.clickPaidTab();
		carrierNextDayObj.gettotalpaiyAmount();
		carrierNextDayObj.verifyNextDayach();

	}

}
