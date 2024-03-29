package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierPayMeNowEmailNotification;
import pages.loadpay.carrier.CarrierPaymeNowFuelCard;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class CarrierPayMeNowEmailNotificationTest extends TestBase {

	CarrierPayMeNowEmailNotification carrierpaymenowemailobj;
	CarrierLoginPage carrierloginobj;
	CarrierNextDAYACH carriernexdayachobj;
	CarrierSameDAYACH carriersamedayachobj;
	OutlookFunctions carrieroutlookobj;
	CarrierWireTransfer carrierwiretransferobj;
	CarrierPaymeNowFuelCard carrierpaymenowfuelcardobj;

	OutlookFunctions outloginobj;

	BrokerLoginPage brokerloginobj;
	BrokerNewPayment brokernewpayobj;

	AdminHomePage adminhomepageobj;
	AdminLogin adminloginobj;
	AdminPayByCheck adminPayByCheckObj;

	String carrierUsername = "";
	String carrierPassword = "";
	String brokerUsername, brokerPassword = "";
	String invoice = "";

	public CarrierPayMeNowEmailNotificationTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException, AWTException {

		initialization();
		TestUtil.className = this.getClass().getName();
		wait = new WebDriverWait(driver, 30);
		carrierpaymenowemailobj = new CarrierPayMeNowEmailNotification();
		carrierloginobj = new CarrierLoginPage();
		carriernexdayachobj = new CarrierNextDAYACH();
		carrieroutlookobj = new OutlookFunctions();
		carriersamedayachobj = new CarrierSameDAYACH();
		carrierwiretransferobj = new CarrierWireTransfer();
		carrierpaymenowfuelcardobj = new CarrierPaymeNowFuelCard();

		outloginobj = new OutlookFunctions();

		brokerloginobj = new BrokerLoginPage();
		brokernewpayobj = new BrokerNewPayment();

		adminhomepageobj = new AdminHomePage();
		adminloginobj = new AdminLogin();
		adminPayByCheckObj = new AdminPayByCheck();
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dataProvider = "getCarrierLoginData")
	public void carrierLogin(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
		} else {
			carrierUsername = user;
			carrierPassword = pass;
		}

		carrierloginobj.Carrierlogin(carrierUsername, carrierPassword);
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"carrierLogin" }, dataProvider = "getoutlookLoginData")
	public void pmnNextDayEmailNotification(String un, String pwd) throws InterruptedException, AWTException {
		// verify PayMeNow tab
		wait.until(ExpectedConditions.elementToBeClickable(carrierpaymenowemailobj.getPaymenowtab()));
		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		// Pay Me Now - Next Day
		carriernexdayachobj.clickPaymenow();
		carriernexdayachobj.clickSelectButton();
		carriernexdayachobj.clickConfirmButton();

		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		outloginobj.outlookLogin(un, pwd);
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox(
				"PayMeNow Payment Notification " + BrokerNewPaymentTest.newPaymentLoadId.get(1), carrierUsername,
				"NextDayACH");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"pmnNextDayEmailNotification" }, dataProvider = "getoutlookLoginData")
	public void pmnSameDayEmailNotification(String un, String pwd) throws InterruptedException, AWTException {
		// verify PayMeNow tab

		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		// Pay Me Now - Same Day
		carriersamedayachobj.clickPaymenow();
		carriersamedayachobj.clickSelectButton();
		carriersamedayachobj.clickConfirmButton();

		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		driver.get(super.getProperties().getProperty("outlookURL"));
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox(
				"PayMeNow Payment Notification " + BrokerNewPaymentTest.newPaymentLoadId.get(2), carrierUsername,
				"SameDayACH");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"pmnSameDayEmailNotification" }, dataProvider = "getoutlookLoginData")
	public void pmnWireTransferEmailNotification(String un, String pwd) throws InterruptedException, AWTException {
		// verify PayMeNow tab

		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		// Pay Me Now Wire transfer
		carrierwiretransferobj.clickPaymenow();
		carrierwiretransferobj.clickSelectButton();
		carrierwiretransferobj.clickConfirmButton();

		// switch to new window
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		driver.get(super.getProperties().getProperty("outlookURL"));
		carrieroutlookobj.clickPopUp();
		carrieroutlookobj.clickOpenMailBox();
		carrieroutlookobj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		carrierpaymenowemailobj.switchToNewWindow(2);
		carrierpaymenowemailobj.outlookSearchInbox(
				"PayMeNow Payment Notification " + BrokerNewPaymentTest.newPaymentLoadId.get(3), carrierUsername,
				"WireTransfer");
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(1);
		driver.close();
		carrierpaymenowemailobj.switchToNewWindow(0);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"pmnWireTransferEmailNotification" }, dataProvider = "getCarrierFuelcardaccountNumbersData")
	public void pmnFuelCardEmailNotification(String fleet_accountnbr, String fts_accountnbr)
			throws InterruptedException, AWTException {
		// verify PayMeNow tab

		Assert.assertTrue(carrierpaymenowemailobj.paymenowtab.isDisplayed(), "PayMeNow tab is NOT available");
		// verify Scheduled tab
		Assert.assertTrue(carrierpaymenowemailobj.scheduledpaymetstab.isDisplayed(),
				"Scheduled Payments tab is NOT available");
		// verify Paid tab
		Assert.assertTrue(carrierpaymenowemailobj.paidtab.isDisplayed(), "Paid tab is NOT available");

		// Pay Me Now - Fuel Card Fleet
		carrierpaymenowfuelcardobj.clickPaymenow();
		carrierpaymenowfuelcardobj.clickSelectButton();
		carrierpaymenowfuelcardobj.clickaddnewcard();
		carrierpaymenowfuelcardobj.clickfleetone();
		carrierpaymenowfuelcardobj.input_accountnbr(fleet_accountnbr);
		carrierpaymenowfuelcardobj.clicksubmit();
		carrierpaymenowfuelcardobj.clickfuelcardsubmit();
		carrierpaymenowfuelcardobj.clickConfirmButton();

		carrierpaymenowfuelcardobj.clickPaidTab();
		carrierpaymenowfuelcardobj.clickpaymenowtab();

		// Pay Me Now - Fuel Card FTS
		carrierpaymenowfuelcardobj.clickPaymenow();
		carrierpaymenowfuelcardobj.clickSelectButton();
		carrierpaymenowfuelcardobj.clickaddnewcard();
		carrierpaymenowfuelcardobj.clickFTS();
		carrierpaymenowfuelcardobj.input_accountnbr(fts_accountnbr);
		carrierpaymenowfuelcardobj.clicksubmit();
		carrierpaymenowfuelcardobj.clickfuelcardsubmit();
		carrierpaymenowfuelcardobj.clickConfirmButton();

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"pmnFuelCardEmailNotification" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		{
			// switch to new window
			carrierpaymenowemailobj.openandSwitchToNewWindow(1);
			driver.get(super.getProperties().getProperty("outlookURL"));
			carrieroutlookobj.clickPopUp();
			carrieroutlookobj.clickOpenMailBox();
			carrieroutlookobj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
			carrierpaymenowemailobj.switchToNewWindow(2);
			carrierpaymenowemailobj.outlookSearchInbox(
					"PayMeNow Payment Notification " + BrokerNewPaymentTest.newPaymentLoadId.get(1), carrierUsername,
					"FuelCard");
			driver.close();
			carrierpaymenowemailobj.switchToNewWindow(1);
			driver.close();
			carrierpaymenowemailobj.switchToNewWindow(0);
			carrierloginobj.CarrierLogout();

		}
	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifyCarrierPaymenowEmailNotification" }, dataProvider = "getBrokerLoginData")
	public void verifybrokerLogin(String un, String pwd) {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerloginobj.Brokerlogin(brokerUsername, brokerPassword);

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifybrokerLogin" }, dataProvider = "getPaymentData")
	public void verifybrokerNewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		brokernewpayobj.newPayment();
		brokernewpayobj.carrierEmail(carrierUsername);
		brokernewpayobj.amount(BrokerNewPaymentTest.newPaymentAmount.get(2));
		brokernewpayobj.invoiceNumber(BrokerNewPaymentTest.newPaymentInvoiceNum.get(2));
		brokernewpayobj.loadId(BrokerNewPaymentTest.newPaymentLoadId.get(2));
		brokernewpayobj.clickShedulePayment();

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"verifybrokerNewPayment" }, dataProvider = "getAdminLoginData")
	public void verifyPayByCheckPaymenow(String un, String pwd) throws InterruptedException, AWTException {
		carrierpaymenowemailobj.openandSwitchToNewWindow(1);
		adminhomepageobj.AdminURL();
		adminloginobj.adminUserPass(un, pwd);
		adminloginobj.adminLogin();
		adminloginobj.ClickOnCustomersTab();
		adminloginobj.ClickOnSearchBox(brokerUsername);
		adminloginobj.ClickOnSearchButton();

		adminloginobj.DoubleClickID();

		adminPayByCheckObj.clickPayments();
		adminPayByCheckObj.ClickOnsearchKeyword(BrokerNewPaymentTest.newPaymentInvoiceNum.get(3));
		adminPayByCheckObj.getPaymentID(BrokerNewPaymentTest.newPaymentInvoiceNum.get(3));
		adminPayByCheckObj.clickSearch();

		adminPayByCheckObj.searchKeyword();

		adminPayByCheckObj.clickSearch1();

		adminPayByCheckObj.clickgridcollapse();

		adminPayByCheckObj.clickPayByCheck();
		adminPayByCheckObj.selectTerms();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = { "verifyPayByCheckPaymenow" })
	public void carrierPaymenowPayByCheck(String EnterDOTNnumber, String companyName, String streetAddress, String city,
			String state, String zip, String country, String phone, String contactName) throws InterruptedException {
		adminPayByCheckObj.setCarrierDOT(TestUtil.removeDecimalZeroFormat(EnterDOTNnumber));
		adminPayByCheckObj.setCarrierStreet(streetAddress);
		adminPayByCheckObj.setCarrierCity(city);
		adminPayByCheckObj.setCarrierState(state);
		adminPayByCheckObj.setCarrierZIP(TestUtil.removeDecimalZeroFormat(zip));
		adminPayByCheckObj.setCarrierCountry(country);
		adminPayByCheckObj.setCarrierPhone(phone);
		adminPayByCheckObj.setCarrierContactName(contactName);
		adminPayByCheckObj.setCarrierCompanyName(companyName);
		adminPayByCheckObj.clickPayByChecksubmit();

	}

	@Test(description = "LP-5422 Carrier - Send Carrier Email Notification When PayMeNow Selected", dependsOnMethods = {
			"carrierPaymenowPayByCheck" }, dataProvider = "getoutlookLoginData")
	public void verifyCarrierPayByCheckPaymenowEmailNotification(String un, String pwd)
			throws InterruptedException, AWTException {
		{
			// switch to new window
			carrierpaymenowemailobj.openandSwitchToNewWindow(2);
			driver.get(super.getProperties().getProperty("outlookURL"));
			carrieroutlookobj.clickPopUp();
			carrieroutlookobj.clickOpenMailBox();
			carrieroutlookobj.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
			carrierpaymenowemailobj.switchToNewWindow(3);
			carrierpaymenowemailobj.outlookSearchInbox("PayMeNow Payment Notification", carrierUsername, "Check");

		}
	}

}
