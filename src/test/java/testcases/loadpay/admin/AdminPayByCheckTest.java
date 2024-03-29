
package testcases.loadpay.admin;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.RetryTest;
import util.TestUtil;

public class AdminPayByCheckTest extends TestBase {

	AdminHomePage ahp;
	AdminLogin al;
	AdminPayByCheck adminPayByCheckObj;
	String acountname;
	String brokerUsername = "";
	String brokerPassword = "";
	ArrayList<String> brokerInvoices;
	Boolean invoicesLoaded = false;

	/*-------Initializing driver---------*/

	public AdminPayByCheckTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		wait = new WebDriverWait(driver, 30);
		al = new AdminLogin();
		ahp = new AdminHomePage();
		adminPayByCheckObj = new AdminPayByCheck();
		brokerInvoices = new ArrayList<String>();
	}

	@AfterClass
	public void tearDown() {
		invoicesLoaded = false;
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void getBrokerCredentials(String username, String pwd) throws InterruptedException {
		Thread.sleep(2000);
		// login as broker
		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;

			Assert.assertTrue(brokerUsername != "",
					"Broker Username appears to be null - brokerUsername[" + brokerUsername + "]");
		} else {
			brokerUsername = username;
			brokerPassword = pwd;
		}
	}

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "getBrokerCredentials", retryAnalyzer = RetryTest.class)
	public void getBrokerInvoiceNumbers(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {
		Thread.sleep(2000);
		// get invoice numbers f payments made to carrier
		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true") && (invoicesLoaded == false)) {
			brokerInvoices.addAll(BrokerNewPaymentTest.al);
			invoicesLoaded = true;
		} else if (!invoicesLoaded) {
			brokerInvoices.add(invoiceno);
		}

		for (int i = 0; i < brokerInvoices.size(); i++) {
			Assert.assertFalse(brokerInvoices.get(i).equals(""), "Broker Invoices appear to be empty - retrying test");
		}
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "getBrokerInvoiceNumbers")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		ahp.AdminURL();

		al.adminUserPass(Username, pass);
		al.adminLogin();

		al.ClickOnCustomersTab();

		al.Uncheck_Factor();

		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);

		al.ClickOnSearchButton();

		al.DoubleClickID();

		adminPayByCheckObj.clickPayments();

		adminPayByCheckObj.ClickOnsearchKeyword(brokerInvoices.get(0));

		adminPayByCheckObj.getPaymentID(brokerInvoices.get(0));

		adminPayByCheckObj.clickSearch();

		adminPayByCheckObj.searchKeyword();

		adminPayByCheckObj.clickSearch1();

		adminPayByCheckObj.clickgridcollapse();

		adminPayByCheckObj.clickPayByCheck();

		adminPayByCheckObj.selectTerms();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheck")
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

		adminPayByCheckObj.clickAddCheckNumber();

		adminPayByCheckObj.ClickOnEnterCheckNumber();

	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "carrierPaymenowPayByCheck")
	public void verifyAdminPayByCheckTermPayment(String Username, String pass)
			throws InterruptedException, AWTException {

		al.ClickOnCustomersTab();
		al.Uncheck_Factor();

		log.info(brokerUsername);
		al.ClickOnSearchBox(brokerUsername);

		al.ClickOnSearchButton();

		al.DoubleClickID();

		adminPayByCheckObj.clickPayments();

		adminPayByCheckObj.ClickOnsearchKeyword(brokerInvoices.get(1));

		adminPayByCheckObj.getPaymentID(brokerInvoices.get(1));

		adminPayByCheckObj.clickSearch();

		adminPayByCheckObj.searchKeyword();

		adminPayByCheckObj.clickSearch1();

		adminPayByCheckObj.clickgridcollapse();

		adminPayByCheckObj.clickPayByCheck();

		adminPayByCheckObj.selectTerms();

		adminPayByCheckObj.selectTermsTermPayment();

	}

	@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData", dependsOnMethods = "verifyAdminPayByCheckTermPayment")
	public void carrierTermPaymentPayByCheck(String EnterDOTNnumber, String companyName, String streetAddress,
			String city, String state, String zip, String country, String phone, String contactName)
			throws InterruptedException {
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

		adminPayByCheckObj.clickAddCheckNumber();

		adminPayByCheckObj.ClickOnEnterCheckNumber();

	}

}
