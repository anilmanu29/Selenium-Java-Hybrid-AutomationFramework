package testcases.loadpay.broker;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerPaymentTermsChargeSender;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerPaymentTermsChargeSenderTest extends TestBase {
	BrokerPaymentTermsChargeSender brokerPaymentTermsChargeSenderObj;
	public static String brokeremailid = "";
	String brokerUsername, brokerPassword = "";
	public static ArrayList<String> newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum;

	/*-------Initializing driver---------*/
	public BrokerPaymentTermsChargeSenderTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {
		initialization();
		TestUtil.className = this.getClass().getName();
		brokerPaymentTermsChargeSenderObj = new BrokerPaymentTermsChargeSender();
		wait = new WebDriverWait(driver, 30);
	}

	/*-------Login to Load Pay as Broker---------*/
	@Test(dataProvider = "getBrokerLoginData")
	public void loginAsBrokerTest(String un, String pwd) throws InterruptedException {

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerPaymentTermsChargeSenderObj.loginAsBroker(brokerUsername, brokerPassword);
		brokeremailid = brokerUsername;

		brokerPaymentTermsChargeSenderObj.uncheckEnablePaymentTerms();
	}

	@Test(description = "LP-5399 Broker Payment Terms Charge Sender", dependsOnMethods = "loginAsBrokerTest")
	public void verifyErrorForPaymentLessThanFourteenDays() throws InterruptedException {

		String invoiceNum = "NP" + TestUtil.getCurrentDateTime();
		String loadID = invoiceNum;
		Integer paymentAmount = TestUtil.getRandomNumber(100, 999);

		brokerPaymentTermsChargeSenderObj.brokerCreateNewPayment(CarrierRegisterTest.carrierUsername, invoiceNum,
				loadID, paymentAmount.toString(), true);
	}

	@Test(description = "LP-5399 Broker Payment Terms Charge Sender", dependsOnMethods = "verifyErrorForPaymentLessThanFourteenDays")
	public void verifyEnablePaymentTerms() throws InterruptedException {

		brokerPaymentTermsChargeSenderObj.activatePaymentTerms();

		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.paymenttermscheckbox.isDisplayed(),
				"Enable Payment Term for less than 14 days check box NOT found");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.chargerecipientradiobutton.isDisplayed(),
				"Charge Recipient radio button NOT found");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.chargesenderradiobutton.isDisplayed(),
				"Charge Sender radio button NOT found");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.updatebutton.isDisplayed(), "Update button NOT found");
	}

	@Test(description = "LP-5399 Broker Payment Terms Charge Sender", dataProvider = "getAdminLoginData", dependsOnMethods = "verifyEnablePaymentTerms")
	public void verifyAdminPaymentTermsEnable(String un, String pwd) throws AWTException, InterruptedException {
		brokerPaymentTermsChargeSenderObj.verifyPaymentTermsAdmin(un, pwd);
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminstatusvalue.getAttribute("value").contains("Enabled"),
				"Status is Disabled");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminpercentageradiobutton.isDisplayed(),
				"Percentage radio button NOT found in Admin");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminflatfeeradiobutton.isDisplayed(),
				"Flat fee radio button NOT found in Admin");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminpaymenttermeditbutton.isDisplayed(),
				"Edit button NOT found");
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.adminchargesenderfield.isDisplayed(),
				"Charge Sender field NOT found in Admin");
	}

	@Test(dependsOnMethods = "verifyAdminPaymentTermsEnable")
	public void verifyEditPercentagePaymentterms() throws InterruptedException {
		brokerPaymentTermsChargeSenderObj.editPaymentTermsPercentage();
		Assert.assertTrue(brokerPaymentTermsChargeSenderObj.numberAsString
				.equalsIgnoreCase(brokerPaymentTermsChargeSenderObj.brokerfee.getAttribute("value")));
	}

	@Test(dependsOnMethods = "verifyEditPercentagePaymentterms")
	public void verifyEditFlatFeePaymentterms() throws InterruptedException {
		brokerPaymentTermsChargeSenderObj.editPaymentTermsPercentageFlatFee();
		brokerPaymentTermsChargeSenderObj.resetStatusFlatFeeValuse();
	}

	@Test(dependsOnMethods = "verifyEditFlatFeePaymentterms")
	public void verifybrokerNewPaymentforLessthanFourthteendays() throws InterruptedException {

		String invoiceNum = "NP" + TestUtil.getCurrentDateTime();
		String loadID = invoiceNum;
		Integer paymentAmount = TestUtil.getRandomNumber(100, 999);

		brokerPaymentTermsChargeSenderObj.brokerCreateNewPayment(CarrierRegisterTest.carrierUsername, invoiceNum,
				loadID, paymentAmount.toString(), false);

		brokerPaymentTermsChargeSenderObj.uncheckEnablePaymentTerms();
	}

}
