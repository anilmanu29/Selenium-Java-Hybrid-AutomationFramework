package testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.AdminHomePage;
import pages.AdminLogin;
import pages.AdminPayByCheck;
import pages.BrokerLoginPage;

public class AdminPayByCheckTest extends TestBase {

	AdminHomePage ahp;
	AdminLogin al;
	AdminPayByCheck apbc;
	public static String acountname;

	/*-------Initializing driver---------*/

	public AdminPayByCheckTest() {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		
		al = new AdminLogin();
		ahp = new AdminHomePage(); 
		apbc=new AdminPayByCheck();
	}

	

	@Test(dataProvider = "getAdminLoginData", priority =18 )
	public void verifyAdminPayByCheck(String Username, String pass) throws InterruptedException, AWTException {
		Thread.sleep(1000);
		ahp.AdminURL(); 
		Thread.sleep(2000);
		al.adminUserPass(Username, pass);
		al.adminLogin();
		Thread.sleep(1000);
		al.ClickOnCustomersTab();
		Thread.sleep(1000);
		System.out.println(BrokerLoginPage.bemail);
		al.ClickOnSearchBox(BrokerLoginPage.bemail);
		Thread.sleep(1000);
		al.ClickOnSearchButton();
		Thread.sleep(1000);
		al.DoubleClickID();
		Thread.sleep(1000);
		apbc.clickPayments();
		Thread.sleep(3000);
		apbc.ClickOnsearchKeyword(BrokerNewPaymentTest.al.get(0));
		Thread.sleep(2000);
		apbc.getPaymentID();
		Thread.sleep(2000);
		apbc.clickSearch();
		Thread.sleep(2000);
		apbc.searchKeyword();
		Thread.sleep(2000);
		apbc.clickSearch1();
		Thread.sleep(2000);
		apbc.clickgridcollapse();
		Thread.sleep(2000);
		apbc.clickPayByCheck();
		Thread.sleep(2000);
		apbc.selectTerms();
		Thread.sleep(3000);
		
	}
		@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=19)
		public void carrierPaymenowPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
		apbc.EnterDOTNnumber(EnterDOTNnumber);
		Thread.sleep(3000);
		apbc.ContactName(ContactName);
		Thread.sleep(3000);
		apbc.clickPayByChecksubmit();
		Thread.sleep(3000);
		al.AdminLogOut();
	

	}
		
		@Test(dataProvider = "getAdminLoginData", priority =20 )
		public void verifyAdminPayByCheckTermPayment(String Username, String pass) throws InterruptedException, AWTException {
			Thread.sleep(1000);
			ahp.AdminURL(); 
			Thread.sleep(2000);
			al.adminUserPass(Username, pass);
			al.adminLogin();
			Thread.sleep(1000);
			al.ClickOnCustomersTab();
			Thread.sleep(1000);
			System.out.println(BrokerLoginPage.bemail);
			al.ClickOnSearchBox(BrokerLoginPage.bemail);
			Thread.sleep(1000);
			al.ClickOnSearchButton();
			Thread.sleep(1000);
			al.DoubleClickID();
			Thread.sleep(1000);
			apbc.clickPayments();
			Thread.sleep(3000);
			apbc.ClickOnsearchKeywordterm(BrokerNewPaymentTest.al.get(1));
			Thread.sleep(2000);
			apbc.getPaymentID();
			Thread.sleep(2000);
			apbc.clickSearch();
			Thread.sleep(2000);
			apbc.searchKeyword();
			Thread.sleep(2000);
			apbc.clickSearch1();
			Thread.sleep(2000);
			apbc.clickgridcollapse();
			Thread.sleep(2000);
			apbc.clickPayByCheck();
			Thread.sleep(2000);
			apbc.selectTerms();
			Thread.sleep(2000);
			apbc.selectTermsTermPayment();
			Thread.sleep(3000);
			
		}
			@Test(dataProvider = "getCcarrierMatchedPayByCheckPayMNWData",priority=21 )
			public void carrierTermPaymentPayByCheck(String EnterDOTNnumber,String ContactName) throws InterruptedException {
			apbc.EnterDOTNnumber(EnterDOTNnumber);
			Thread.sleep(3000);
			apbc.ContactName(ContactName);
			Thread.sleep(3000);
			apbc.clickPayByChecksubmit();
			Thread.sleep(3000);
			
			

		}

	
}
