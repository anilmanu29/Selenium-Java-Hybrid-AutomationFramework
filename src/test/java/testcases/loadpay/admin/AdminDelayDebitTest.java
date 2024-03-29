package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.admin.AdminCancelPayByCheck;
import pages.loadpay.admin.AdminCustomersSideMenuSearch;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.admin.AdminPaymeNowTab;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerPaymentSheduledates;
import pages.loadpay.broker.SchpaymentwithoutBankAccountPayByInvoiceEnabled;
import pages.loadpay.carrier.CarrierLoginPage;
import testcases.loadpay.broker.BrokerRegisterTest;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class AdminDelayDebitTest extends TestBase {

	SchpaymentwithoutBankAccountPayByInvoiceEnabled schpaymentwithoutBankAccountPayByInvoiceenabled;
	CarrierLoginPage carrierloginPage;
	BrokerPaymentSheduledates brokerPaymentSheduledates;
	BrokerLoginPage brokerlogin;
	OutlookFunctions brokerOutlookObj;
	OutlookFunctions outlook;

	AdminCustomersSideMenuSearch adminCustomersSideMenuSearch;
	AdminCancelPayByCheck adminPayByCheckObj;
	AdminHomePage admHomePage;
	AdminLogin admLogin;
	AdminPaymeNowTab admPayMeNowTab;
	AdminPayByCheck adminExpand;

	WebElement payMeNowCheckbox;

	String payment_status = "Verified";
	String brokerUsername;
	String brokerPassword;
	String email;
	String emailid = "";
	String carrierUserName;
	String carrierPassword;
	ArrayList<String> invoiceList;
	String newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum;

	Date currentTime;
	String formattedDate = "";
	Long longTime;
	DateFormat formatter;
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];

	Boolean isPayMeNowEnabled = false;
	Boolean isDelayedDebitEnabled = false;

	/*-------Initializing driver---------*/
	public AdminDelayDebitTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		admLogin = new AdminLogin();
		admHomePage = new AdminHomePage();
		admPayMeNowTab = new AdminPaymeNowTab();
		adminExpand = new AdminPayByCheck();
		adminPayByCheckObj = new AdminCancelPayByCheck();

		brokerlogin = new BrokerLoginPage();
		brokerPaymentSheduledates = new BrokerPaymentSheduledates();

		carrierloginPage = new CarrierLoginPage();

		adminCustomersSideMenuSearch = new AdminCustomersSideMenuSearch();
		schpaymentwithoutBankAccountPayByInvoiceenabled = new SchpaymentwithoutBankAccountPayByInvoiceEnabled();
		outlook = new OutlookFunctions();
		brokerOutlookObj = new OutlookFunctions();

		invoiceList = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
		currentTime = new Date();
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String user, String pass) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = user;
			brokerPassword = pass;
		}
	}

	/*-------Admin Login ---------*/

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerCredentials")
	public void verifyDelayDebit(String Username, String pass) throws InterruptedException, AWTException {
		admHomePage.AdminURL();
		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUsername);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		admLogin.StatusIDDropDown();

		// delayed debit is currently enabled for this broker
		admLogin.Link_delaydebit();
		isDelayedDebitEnabled = admLogin.isDelayedDebitSelected();

		// click on pay me now and enable it
		admPayMeNowTab.openPayMeNowTab();
		admPayMeNowTab.clickEnrollInPayMeNow();
		admPayMeNowTab.setTermDropdown("15");
		admPayMeNowTab.clickUpdateButton();

		// click ok on notification only if delayed debit was previously enabled
		if (isDelayedDebitEnabled)
			admLogin.ClickOKButon();

		// enable delay debit
		admLogin.Link_delaydebit();
		admLogin.ClickEditDelayDebit();
		admLogin.select_DelayDebitEnabled();
		admLogin.Click_UpdateDelayDebit();

		// since pay me now is enabled in previous steps, notification popup will be
		// enabled and ok button should be present
		admLogin.ClickOKButon();
		admLogin.Link_PayMeNowTm();
		isPayMeNowEnabled = admPayMeNowTab.enrollInPaymeNowButton.isSelected();
		Assert.assertFalse(isPayMeNowEnabled,
				"Pay Me Now Enabled - should be disabled when Delayed Debit is selected!");

		admLogin.AdminLogOut();
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getBrokerLoginData", dependsOnMethods = "verifyDelayDebit")
	public void loginBroker(String un, String pwd) throws InterruptedException {
		driver.get(prop.getProperty("loadPayURL"));
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(brokerUsername, brokerPassword);
	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		brokerPaymentSheduledates = new BrokerPaymentSheduledates();
		brokerPaymentSheduledates.lnkMyAccount();
		brokerPaymentSheduledates.clicklnk_PayMeNow();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_PayMeNow.isDisplayed(), "PayMeNow Link NOT Found!");

		// verify pay me now option is disabled (from admin action above)
		payMeNowCheckbox = driver.findElement(By.xpath(".//*[@id='PMNEnrolled']"));
		Assert.assertFalse(payMeNowCheckbox.isSelected(), "Pay Me Now link is enabled - should be disabled!");

		// Store data-provider elements into publicly-accessible strings

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUserName = CarrierRegisterTest.carrierUsername;
		} else {
			carrierUserName = cemail;
		}

		invoiceno = TestUtil.getCurrentDateTime();
		newPaymentAmount = amt;
		newPaymentInvoiceNum = invoiceno;
		newPaymentLoadId = invoiceno;

		brokerPaymentSheduledates.newPayment();

		email = brokerPaymentSheduledates.carrierEmail(carrierUserName);
		brokerPaymentSheduledates.amount(newPaymentAmount);
		invoiceList.add(brokerPaymentSheduledates.invoiceNumber(newPaymentInvoiceNum));
		brokerPaymentSheduledates.loadId(newPaymentLoadId);
		brokerPaymentSheduledates.clickShedulePayment();
		log.info("Verify New Payment Link Passed");

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment")
	public void verifyAdminPayByCheck(String Username, String pass)
			throws InterruptedException, AWTException, ParseException {

		admHomePage.AdminURL();

		admLogin.adminUserPass(Username, pass);
		admLogin.adminLogin();
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUsername);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();
		adminPayByCheckObj.clickPayments();
		Thread.sleep(1000);
		adminPayByCheckObj.ClickOnsearchKeyword(invoiceList.get(1));

		// adminPayByCheckObj.ClickOnsearchKeyword(adminPayByCheckObj.getPaymentID1().getText());
		Thread.sleep(1000);
		adminPayByCheckObj.getPaymentID();
		adminPayByCheckObj.clickSearch();
		adminPayByCheckObj.searchKeyword();
		adminPayByCheckObj.clickSearch1();
		adminPayByCheckObj.clickgridcollaps();

		Assert.assertTrue(brokerPaymentSheduledates.anticipatedwidrawldate.isDisplayed(),
				"get text of Anticipated Date if NOT Found!");
		String anticipatedWithdrawalDate = brokerPaymentSheduledates.getanticipatedwidrawlDate();
		WebElement termDate = driver.findElement(By.xpath(
				".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/div[2]/div/b"));

		Long termDateDiff = TestUtil.getDifferenceBetweenDates(termDate.getText(), anticipatedWithdrawalDate);
		// Assert.assertTrue(termDateDiff == 15, "Term Date difference from withdrawal
		// date does not equal 15 days");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		Long totalDatediff = TestUtil.getDifferenceBetweenDates(dateFormat.format(date), anticipatedWithdrawalDate);
		// Assert.assertTrue(totalDatediff == 30, "Total Date difference does not equal
		// 30 days");

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyAdminPayByCheck")
	public void verifyDelayDebitpaymenow(String Username, String pass) throws InterruptedException, AWTException {
		Assert.assertTrue(admLogin.CustomerTab.isDisplayed(), "Customer Tab Link if  NOT Found!");
		admLogin.ClickOnCustomersTab();
		admLogin.ClickOnSearchBox(brokerUsername);
		admLogin.ClickOnSearchButton();
		admLogin.DoubleClickID();

		admPayMeNowTab.openPayMeNowTab();
		admPayMeNowTab.clickEnrollInPayMeNow();
		admPayMeNowTab.setTermDropdown("15");
		admPayMeNowTab.clickUpdateButton();
		admLogin.ClickOKButon();

		admLogin.Link_delaydebit();
		Assert.assertFalse(admLogin.isDelayedDebitSelected(),
				"Delayed Debit is enabled - should be disabled when PayMenNow is enabled!");

		admLogin.AdminLogOut();
		log.info("Verify Customer tab Link Passed");

	}

	@Test(dataProvider = "getoutlookLoginData", dependsOnMethods = "verifyDelayDebitpaymenow")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("loadpaytestEmail"));
		String[] timeArray = TestUtil.getTimestamp();
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
		brokerOutlookObj.outlookSearchInbox(brokerUsername, currentHour, currentMinutes);
		brokerOutlookObj.verifyEmailReceived("Your LoadPay account is now enrolled in PayMeNow.");

	}

	@Test(description = "LP-5427 Admin - Delay Debit", dependsOnMethods = "outlookloginTest")
	public void verifypaymenow() {
		driver.get(prop.getProperty("loadPayURL"));
		brokerPaymentSheduledates.lnkMyAccount();
		brokerPaymentSheduledates.clicklnk_PayMeNow();
		Assert.assertTrue(brokerPaymentSheduledates.lnk_PayMeNow.isDisplayed(), "PayMeNow Link NOT Found!");

		// verify pay me now option is disabled (from admin action above)
		payMeNowCheckbox = driver.findElement(By.xpath(".//*[@id='PMNEnrolled']"));
		Assert.assertTrue(payMeNowCheckbox.isSelected(), "Pay Me Now link is enabled - should be disabled!");
	}
}
