package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.carrier.CarrierCompleteAccountModule;
import pages.loadpay.carrier.CarrierLoginPage;
import util.TestUtil;

public class CarrierCompleteAccountModuleTest extends TestBase {

	CarrierCompleteAccountModule carriercompleteaccountmoduleObj;
	CarrierLoginPage loginPage;
	AdminHomePage adminhomepage;
	AdminLogin adminlogin;
	String carrierUsername, carrierPassword = "";

	/*-------Initializing driver---------*/

	public CarrierCompleteAccountModuleTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		carriercompleteaccountmoduleObj = new CarrierCompleteAccountModule();
		loginPage = new CarrierLoginPage();
		adminlogin = new AdminLogin();
		adminhomepage = new AdminHomePage();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-3473 Carrier - Complete Account module", dataProvider = "getCarrierLoginData")
	public void VerifyCarrierLogin(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

		carriercompleteaccountmoduleObj.clickAccountlink();
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_account.isDisplayed());

		carriercompleteaccountmoduleObj.clickCompanylink();
		Assert.assertTrue(carriercompleteaccountmoduleObj.lnk_Company.isDisplayed());

		carriercompleteaccountmoduleObj.enterDotnumber("1234567");
		carriercompleteaccountmoduleObj.enterEinnumber("99-9999999");
		carriercompleteaccountmoduleObj.clickCompanyUpdate();
		Assert.assertTrue(carriercompleteaccountmoduleObj.companyupdate.isDisplayed());
	}

	@Test(description = "LP-3473 Carrier - Complete Account module", dataProvider = "getAdminLoginData", dependsOnMethods = {
			"VerifyCarrierLogin" })
	public void verifyAdminAccountModule(String Username, String pass) throws InterruptedException, AWTException {
		adminhomepage.AdminURL();

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getUserName()));
		adminlogin.adminUserPass(Username, pass);

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getLoginBtn()));
		adminlogin.adminLogin();

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getCustomerTab()));
		adminlogin.ClickOnCustomersTab();

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getSearch()));
		adminlogin.ClickOnSearchBox(carrierUsername);

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getClickonSearchButton()));
		adminlogin.ClickOnSearchButton();

		wait.until(ExpectedConditions.elementToBeClickable(adminlogin.getDoubleClickID()));
		Assert.assertTrue(adminlogin.ClickonSearchButton.isDisplayed());
		adminlogin.DoubleClickID();

	}

	@Test(description = "LP-3473 Carrier - Complete Account module", dataProvider = "getShipperCompleteAccModuleData", dependsOnMethods = {
			"verifyAdminAccountModule" })
	public void verifyContactDetails(String un, String pwd, String ContactFN, String ContactLN, String contactemail,
			String ContactPN, String Contactextension, String ContactMobileNumber, String ContactFax)
			throws InterruptedException {
		driver.get(super.getProperties().getProperty("loadPayURL"));

		carriercompleteaccountmoduleObj.clickAccountlink();
		carriercompleteaccountmoduleObj.clickContactlink();
		carriercompleteaccountmoduleObj.clickAddNewContact();
		carriercompleteaccountmoduleObj.enterContactFirstName(ContactFN);
		carriercompleteaccountmoduleObj.enterContactlastName(ContactLN);
		carriercompleteaccountmoduleObj.enterContactemail(contactemail);
		carriercompleteaccountmoduleObj.enterContactphonenum(ContactPN);
		carriercompleteaccountmoduleObj.enterContactExtension(TestUtil.removeDecimalZeroFormat(Contactextension));
		carriercompleteaccountmoduleObj.enterContactMobileNumber(ContactMobileNumber);
		carriercompleteaccountmoduleObj.enterContactFax(ContactFax);
		carriercompleteaccountmoduleObj.clicksavelink();
		carriercompleteaccountmoduleObj.clickContactUpdatelink();
	}

	@Test(description = "LP-3473 Carrier - Complete Account module", dependsOnMethods = { "verifyContactDetails" })
	public void verifyNotifications() throws InterruptedException {
		carriercompleteaccountmoduleObj.clickAccountlink();
		carriercompleteaccountmoduleObj.clickNotificationlink();
		carriercompleteaccountmoduleObj.clickNotifyByNewPaymentlink();
		carriercompleteaccountmoduleObj.clickNotifyByPayMeNowlink();
		carriercompleteaccountmoduleObj.clickNotifyByDepositlink();
		carriercompleteaccountmoduleObj.clickCarrierNotificationUpdatelink();
		carriercompleteaccountmoduleObj.clickNotifyByNewPaymentlink();
		carriercompleteaccountmoduleObj.clickNotifyByPayMeNowlink();
		carriercompleteaccountmoduleObj.clickNotifyByDepositlink();
		carriercompleteaccountmoduleObj.clickCarrierNotificationUpdatelink();
	}

}
