package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierSameDAYACH;
import pages.loadpay.carrier.CarrierWireTransfer;

public class AdminPaymentsGreaterthan45DaysTest extends TestBase {

	BrokerPaymentSheduledates brokerPaymentSheduledates;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	CarrierWireTransfer carrierwireframe;
	WebElement checkbox;
	String brokerUserName;
	String brokerPassword;
	BrokerLoginPage brokerlogin;
	String invoice;
	ArrayList<String> invoiceList;
	String email;
	CarrierLoginPage carrierloginPage;
	String carrierUserName;
	String carrierPassword;
	CarrierSameDAYACH carriersamedayach;

	/*-------Initializing driver---------*/
	public AdminPaymentsGreaterthan45DaysTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		brokerlogin = new BrokerLoginPage();
		carrierloginPage = new CarrierLoginPage();
		carrierwireframe = new CarrierWireTransfer();
		brokerlogin = new BrokerLoginPage();
		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		carriersamedayach = new CarrierSameDAYACH();
		wait = new WebDriverWait(driver, 30);
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		brokerUserName = user;
		brokerPassword = pass;
	}

	/*-------Admin Login ---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyAdminPaymentHistoryStatus(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Enabled();
		admLogin.Click_paymentterm45Submit();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
		admLogin.Link_PayMeNowTm();
		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatus")
	public void loginBroker(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminPaymentsGreaterthan45Days", dependsOnMethods = "loginBroker")
	public void brokernewPaymentmorethan365(String cemail, String invoiceno, String loadid, String amt,
			String scheduledate) throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");
		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(cemail);
		brokerPaymentSheduledates.amount(amt);
		invoice = brokerPaymentSheduledates.invoiceNumber(invoiceno);
		invoiceList.add(invoice);
		brokerPaymentSheduledates.loadId(loadid);
		brokerPaymentSheduledates.clickPaymentDate(scheduledate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getBrokerLoginData", dependsOnMethods = "brokernewPaymentmorethan365")
	public void loginBrokernew(String un, String pwd) {
		driver.get(prop.getProperty("url"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getpayementmorethan45daysData", dependsOnMethods = "loginBrokernew")
	public void brokernewPayment45days(String cemail, String invoiceno, String loadid, String amt, String scheduledate)
			throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_newpayment.isDisplayed(), "newPayment Link if NOT Found!");
		brokerPaymentSheduledates.newPayment();
		email = brokerPaymentSheduledates.carrierEmail(cemail);
		brokerPaymentSheduledates.amount(amt);
		invoice = brokerPaymentSheduledates.invoiceNumber(invoiceno);
		invoiceList.add(invoice);
		brokerPaymentSheduledates.loadId(loadid);
		brokerPaymentSheduledates.clickPaymentDate(scheduledate);
		brokerPaymentSheduledates.clickShedulePayment();
		brokerPaymentSheduledates.logout();
		log.info("Verify New Payment Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment45days")
	public void verifyAdminPaymentHistoryStatusdisable(String Username, String pass)
			throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Disabled();
		admLogin.Click_paymentterm45Submit();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
		admLogin.Link_PayMeNowTm();
		admLogin.clickuncheckpaymennow();
		admLogin.ClickUpdatepaymenow();
		admLogin.ClickCloseButon();
		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");
	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getCarrierLoginData", dependsOnMethods = "verifyAdminPaymentHistoryStatusdisable")
	public void carrierloginTest(String user, String pass) throws InterruptedException {
		carrierUserName = user;
		carrierPassword = pass;
		driver.get(prop.getProperty("url"));
		carrierloginPage = new CarrierLoginPage();
		carrierloginPage.Carrierlogin(carrierUserName, carrierPassword);

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dependsOnMethods = "carrierloginTest")
	public void carrierPaymenow() throws InterruptedException {
		Assert.assertTrue(carriersamedayach.btn_paymenow.isDisplayed(), "Payme now Link if NOT Found!");
		carriersamedayach.clickPaymenow();
		carrierwireframe.clickSelectButton();
		carrierwireframe.clickConfirmButton();
		log.info("Verify Payme Now Link Passed");

	}

	@Test(description = "LP-6621 LoadPay - Selenium Test - Admin - Payments Greater than 45 Days", dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenow")
	public void verifySystemGeNnotes(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUserName);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();
		admLogin.ClickPaymentTerms();
		admLogin.clickEditPaymnttermgraterthan45days();
		admLogin.selectGreaterThan45daysId_Disabled();
		admLogin.Click_paymentterm45Submit();
		admLogin.Click_Notes();
		admLogin.Clickverifysystemnote();
		admLogin.Clickclosenotesbutton();
		log.info("Verify Customer tab Link Passed");

	}

}