package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.broker.BrokerOutlook;
import pages.loadpay.broker.BrokerRegister;
import pages.loadpay.broker.SchpaymentwithoutBankAccountPayByInvoiceEnabled;
import pages.loadpay.outlook.outlooklogin;

public class SchpaymentwithoutBankAccountPayByInvoiceEnabledTest extends TestBase {

	// page objects
	SchpaymentwithoutBankAccountPayByInvoiceEnabled schpaymentwithoutBankAccountPayByInvoiceenabled;
	AdminHomePage adminhomepage;
	AdminLogin adminlogin;
	BrokerRegister brokerregister;
	BrokerOutlook brokerOutlookObj;
	outlooklogin outlookLoginObj;

	String brokerUsername;
	public static String emailid;
	public static String EIN = "99-9999999";
	public static String pwd;

	// timestamp variables
	Date currentTime;
	String formattedDate = "";
	String currentHour = "";
	String currentMinutes = "";
	String timeArray[] = new String[2];
	Long longTime;
	DateFormat formatter;

	/*-------Initializing driver---------*/

	public SchpaymentwithoutBankAccountPayByInvoiceEnabledTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		adminhomepage = new AdminHomePage();
		adminlogin = new AdminLogin();
		brokerregister = new BrokerRegister();
		brokerOutlookObj = new BrokerOutlook();
		outlookLoginObj = new outlooklogin();
		currentTime = new Date();
		schpaymentwithoutBankAccountPayByInvoiceenabled = new SchpaymentwithoutBankAccountPayByInvoiceEnabled();
	}

	@Test(dataProvider = "getBrokerRegisterData")
	public void BrokerRegister(String Dotnumber, String CompanyName, String DoingBussinessAS, String Email,
			String ConfirmEmail, String ZipCode1, String Address, String City, String FirstNames, String LastName,
			String PhoneNumber, String Password, String ConfirmPassword, String NameonAccount, String RoutingNumber,
			String BankAccountNumber, String ConfirmbankAccountNumber) throws IOException, InterruptedException {

		brokerUsername = Email;
		pwd = Password;
		brokerregister.signup();
		brokerregister.shipperRegister();

		// gets a better random seed for indexing
		int randomNum = ThreadLocalRandom.current().nextInt(0, 30);

		if (randomNum < 10)
			randomNum = 0;
		else if (randomNum < 20)
			randomNum = 1;
		else
			randomNum = 2;

		brokerregister.setMotorCarrierSelector(randomNum);

		randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999999);
		brokerregister.setMotorCarrierField(randomNum);

		brokerregister.companyname(CompanyName);
		brokerregister.doingbussiness(DoingBussinessAS);
		brokerregister.selectType();

		Select type = new Select(driver.findElement(By.xpath(".//*[@id='EntityType']")));
		type.selectByVisibleText("C Corporation");

		brokerregister.countryofincorporation();

		Select countryof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationCountry']")));
		countryof.selectByIndex(0);

		brokerregister.stateofincorporation();

		Select stateof = new Select(driver.findElement(By.xpath(".//*[@id='IncorporationState']")));
		stateof.selectByVisibleText("California");

		emailid = brokerregister.BrokerEmail(Email);

		brokerregister.confirmEmail(ConfirmEmail);
		brokerregister.iCertifyClick();
		brokerregister.paymentTerm();
		brokerregister.clickNextBtnOnCompanyForm();
		brokerregister.ZipCode(ZipCode1);
		brokerregister.country();

		Select country = new Select(driver.findElement(By.xpath(".//*[@id='OriginCountry']")));
		country.selectByVisibleText("USA");

		brokerregister.address(Address);
		brokerregister.city(City);
		brokerregister.State();

		Select state = new Select(driver.findElement(By.xpath(".//*[@id='State']")));
		state.selectByVisibleText("CA");

		brokerregister.clickNextBtnOnAddressForm();

		brokerregister.ContactFirstName(FirstNames);
		brokerregister.LastName(LastName);
		brokerregister.Phone(PhoneNumber);
		brokerregister.Password(ConfirmPassword);
		brokerregister.ConfirmPassword(ConfirmPassword);

		brokerregister.clickNextBtnOnContactForm();

		schpaymentwithoutBankAccountPayByInvoiceenabled.clickaddlater();
		System.out.println("Broker Register Completed...");
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlookLoginObj.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {

		brokerOutlookObj.clickPopUp();
		brokerOutlookObj.clickOpenMailBox();
		brokerOutlookObj.enterEmail(super.prop.getProperty("email"));

		getTimestamp();
		brokerOutlookObj.outlookSearchInbox(emailid, currentHour, currentMinutes);
		schpaymentwithoutBankAccountPayByInvoiceenabled.handleNewInbox(brokerUsername);
		schpaymentwithoutBankAccountPayByInvoiceenabled.verifyConfirmationMessage();

	}

	public void getTimestamp() {
		TimeZone tz = Calendar.getInstance().getTimeZone();

		formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("MST"));
		longTime = currentTime.getTime();
		formattedDate = formatter.format(longTime);
		timeArray = formattedDate.split(":");
		currentHour = timeArray[0];
		currentMinutes = timeArray[1];
	}

	@Test(description = "Switch to admin URL", dependsOnMethods = "outlookloginTest")
	public void Home() throws IOException, AWTException, InterruptedException {
		adminhomepage.AdminURL();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "outlookloginTest")
	public void adminLogin(String Username, String pass) throws IOException, InterruptedException, AWTException {

		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		adminlogin.ClickOnCustomersTab();
		adminlogin.ClickOnSearchBox(brokerUsername);
		adminlogin.ClickOnSearchButton();
		adminlogin.DoubleClickID();
		adminlogin.StatusIDDropDown();
		adminlogin.UpdateButton();
		adminlogin.click_AdminBanking();
		adminlogin.Banking_editbtnPayByInvoice();
		adminlogin.selectPayByInvoieStatus();
		adminlogin.clickupdatebtnPayByInvoice();
	}

	@Test(dependsOnMethods = "adminLogin")
	public void BrokerFirstloginTest() throws InterruptedException {
		driver.get(super.prop.getProperty("url"));
		schpaymentwithoutBankAccountPayByInvoiceenabled.brokerfirstLogin();
		schpaymentwithoutBankAccountPayByInvoiceenabled.clickAcceptedTerms();
		schpaymentwithoutBankAccountPayByInvoiceenabled.clicksubmit();
		WebElement confirmationPopup = driver
				.findElement(By.xpath("//*[@id='angularScope']/div[3]/div/div/div[2]/button"));
		wait.until(ExpectedConditions.elementToBeClickable(confirmationPopup));
		confirmationPopup.click();
		schpaymentwithoutBankAccountPayByInvoiceenabled.newPayment();
		schpaymentwithoutBankAccountPayByInvoiceenabled.BrokerLogout();

	}

	@Test(dependsOnMethods = "BrokerFirstloginTest")
	public void adminDisablePayByInvoice() throws IOException, InterruptedException, AWTException {

		adminhomepage.AdminURL();
		adminlogin.ClickOnCustomersTab();
		adminlogin.ClickOnSearchBox(brokerUsername);
		adminlogin.ClickOnSearchButton();
		adminlogin.DoubleClickID();
		adminlogin.click_AdminBanking();
		adminlogin.Banking_editbtnPayByInvoice();
		adminlogin.DisablePayByInvoieStatus();
		adminlogin.clickupdatebtnPayByInvoice();
		/* adminlogin.AdminLogOut(); */
	}

	@Test(dependsOnMethods = "adminDisablePayByInvoice")
	public void BrokerSecondloginTest() throws InterruptedException {
		driver.get(super.prop.getProperty("url"));
		schpaymentwithoutBankAccountPayByInvoiceenabled.brokerSecondLogin();
	}

	public void verifySchpaymentwithoutBankAccountPayByInvoiceEnabledDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(schpaymentwithoutBankAccountPayByInvoiceenabled.click_addlater.isDisplayed(),
				"AddLater button not found");
		Assert.assertTrue(schpaymentwithoutBankAccountPayByInvoiceenabled.linkVerify.isDisplayed(),
				"Verify Link for Outlook Email Llink not found");
		Assert.assertTrue(adminlogin.editbtnPayByInvoice.isDisplayed(), "RTF Login Column not found");
		Assert.assertTrue(adminlogin.select_PayByInvoieStatus.isDisplayed(), "Select Enable dropdown should not found");
		Assert.assertTrue(adminlogin.select_PayByInvoieStatus.isDisplayed(),
				"Select Disable dropdown should not found not found");
		Assert.assertTrue(schpaymentwithoutBankAccountPayByInvoiceenabled.AcceptedTerms.isDisplayed(),
				"Accepted Terms and condition Validation not found");

	}

}