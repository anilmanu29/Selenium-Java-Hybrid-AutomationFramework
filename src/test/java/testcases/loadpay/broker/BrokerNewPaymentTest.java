package testcases.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import testcases.loadpay.carrier.CarrierRegisterTest;
import util.TestUtil;

public class BrokerNewPaymentTest extends TestBase {

	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String payment_status = "Verified";
	static String invoice;
	public static ArrayList<String> al;
	public static String email;

	String carrierUsername = "";
	String brokerUsername, brokerPassword = "";
	String dateTime = "";

	/*-------Initializing driver---------*/
	public BrokerNewPaymentTest() {
		super();

	}

	@BeforeClass
	public void setUp() {

		initialization();
		bl = new BrokerLoginPage();
		bp = new BrokerNewPayment();
		al = new ArrayList<String>();
		wait = new WebDriverWait(driver, 30);
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String email, String pwd) {
		bl = new BrokerLoginPage();
		dateTime = TestUtil.getCurrentDateTime();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = email;
			brokerPassword = pwd;
		}

		bl.Brokerlogin(brokerUsername, brokerPassword);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		bp = new BrokerNewPayment();
		bp.newPayment();

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			invoiceno = "UM" + TestUtil.getCurrentDateTime();
			loadid = invoiceno;
		} else {
			carrierUsername = cemail;
		}

		bp.carrierEmail(carrierUsername);

		bp.amount(amt);

		invoice = bp.invoiceNumber(invoiceno);
		al.add(invoice);

		bp.loadId(loadid);

		// bp.advanceCheckbox();
		//
		bp.clickShedulePayment();

		bp.clickShedulePaymenttab();

		bp.searchCarrier(carrierUsername);

		bp.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		bp.verifyInvoiceNumber(invoiceno, amt);

		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		log.info(bp.verifyPaymentStatus());
		// bp.logout();
	}

}
