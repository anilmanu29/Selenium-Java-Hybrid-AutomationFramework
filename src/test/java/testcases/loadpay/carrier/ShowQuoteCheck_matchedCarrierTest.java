package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.admin.AdminHomePage;
import pages.loadpay.admin.AdminLogin;
import pages.loadpay.admin.AdminPayByCheck;
import pages.loadpay.broker.BrokerAdvancePaymenttoUnmatchedCarrier;
import pages.loadpay.broker.BrokerLoginPage;
import pages.loadpay.broker.BrokerNewPayment;
import pages.loadpay.broker.BrokerPaymentforUnmatchedCarrier;
import pages.loadpay.unmatched.UnmatchedCarrierAdminPayByCheck;

public class ShowQuoteCheck_matchedCarrierTest extends TestBase {

	BrokerPaymentforUnmatchedCarrier brokerPaymentforUnmatchedCarrier;
	BrokerLoginPage brokerlogin;
	BrokerNewPayment brokerNewPayment;
	String payment_status = "Unmatched";
	String invoice;
	String umemail;
	String einno;
	ArrayList<String> arraylistin;
	String invoiceNum;
	BrokerLoginPage brokerloginobj;
	BrokerNewPayment brokerpaymentobj;
	BrokerAdvancePaymenttoUnmatchedCarrier brokeradvancepaymentobj;
	AdminHomePage homepage;
	AdminLogin adminlogin;
	AdminPayByCheck adminPayByCheck;
	UnmatchedCarrierAdminPayByCheck UnCarrierAdminPBC;
	ArrayList<String> arraylist;
	String cemail;
	String email;
	String brokerUsername;
	String brokerPassword;

	/*-------Initializing driver---------*/
	public ShowQuoteCheck_matchedCarrierTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		brokerlogin = new BrokerLoginPage();
		brokerNewPayment = new BrokerNewPayment();
		brokerPaymentforUnmatchedCarrier = new BrokerPaymentforUnmatchedCarrier();
		arraylist = new ArrayList<String>();
		brokerloginobj = new BrokerLoginPage();
		brokerpaymentobj = new BrokerNewPayment();
		brokeradvancepaymentobj = new BrokerAdvancePaymenttoUnmatchedCarrier();
		homepage = new AdminHomePage();
		adminlogin = new AdminLogin();
		arraylist = new ArrayList<String>();
		adminPayByCheck = new AdminPayByCheck();
		UnCarrierAdminPBC = new UnmatchedCarrierAdminPayByCheck();
	}

	@Test(dataProvider = "getBrokerLoginData")
	public void loginBroker(String un, String pwd) {
		brokerlogin = new BrokerLoginPage();
		brokerlogin.Brokerlogin(un, pwd);

	}

	/*-------Scheduling New Payment as a Broker---------*/

	@Test(dataProvider = "getPaymentData", dependsOnMethods = "loginBroker")
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt)
			throws InterruptedException {

		brokerNewPayment = new BrokerNewPayment();
		brokerNewPayment.newPayment();
		email = brokerNewPayment.carrierEmail(cemail);
		brokerNewPayment.amount(amt);
		invoice = brokerNewPayment.invoiceNumber(invoiceno);
		arraylist.add(invoice);
		brokerNewPayment.loadId(loadid);
		// bp.advanceCheckbox();
		/* brokerNewPayment.setField_PayTo(payTo); */
		brokerNewPayment.clickShedulePayment();
		brokerNewPayment.clickShedulePaymenttab();
		brokerNewPayment.searchCarrier(cemail);
		arraylist.add(umemail);
		brokerNewPayment.clickSearchButton();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		brokerNewPayment.verifyInvoiceNumber(invoiceno, amt);
		// Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(brokerNewPayment.verifyPaymentStatus());
		// bp.logout();
	}

	@Test(dataProvider = "getAdminLoginData", dependsOnMethods = "brokernewPayment")
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {

		homepage.AdminURL();
		adminlogin.adminUserPass(Username, pass);
		adminlogin.adminLogin();
		adminlogin.ClickOnCustomersTab();
		/*
		 * System.out.println(brokerUsername);
		 * adminlogin.ClickOnSearchBox(brokerUsername);
		 */
		adminlogin.ClickOnSearchBox(brokerloginobj.bemail);
		Thread.sleep(1000);
		adminlogin.ClickOnSearchButton();
		Thread.sleep(1000);
		adminlogin.DoubleClickID();
		Thread.sleep(1000);
		adminPayByCheck.clickPayments();
		Thread.sleep(1000);
		System.out.println(UnCarrierAdminPBC.getPaymentId1().getText());
		adminPayByCheck.ClickOnsearchKeyword(UnCarrierAdminPBC.getPaymentId1().getText());
		Thread.sleep(1000);
		adminPayByCheck.getPaymentID();
		adminPayByCheck.clickSearch();
		adminPayByCheck.searchKeyword();
		adminPayByCheck.clickSearch1();
		adminPayByCheck.clickgridcollapse();
		adminPayByCheck.clickPayByCheck();
		adminPayByCheck.selectTerms();
		adminPayByCheck.clickShowQuote();
		adminPayByCheck.selectTermsTermPayment();
		adminPayByCheck.clickShowQuote();
	}

	public void verifyShowQuoteCheck_UnmatchedCarrierTestDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerNewPayment.field_InvoiceNum.isDisplayed(), " invoicenum field not found");
		Assert.assertTrue(brokerNewPayment.btn_search.isDisplayed(), "Search button not found");
		Assert.assertTrue(adminPayByCheck.link_Payments.isDisplayed(), "Payment ID not found");
		Assert.assertTrue(adminPayByCheck.select_Terms.isDisplayed(), "Payments Term & Term Payment not found");
		Assert.assertTrue(adminPayByCheck.ShowQuoteClose.isDisplayed(), "ShowQuoteClose button not found");

	}

}