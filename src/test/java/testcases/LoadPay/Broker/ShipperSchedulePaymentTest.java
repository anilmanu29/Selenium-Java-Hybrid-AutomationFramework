package testcases.LoadPay.Broker;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Broker.BrokerNewPayment;

public class ShipperSchedulePaymentTest extends TestBase 
{
	
	BrokerNewPayment bp;
	BrokerLoginPage bl;
	String  payment_status = "Verified";
	 static String invoice;
	
	/*-------Initializing driver---------*/
	public ShipperSchedulePaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bp = new BrokerNewPayment();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=14)
	public void loginBroker(String un, String pwd)
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	
	@Test(dataProvider="getPaymentData", priority=15)
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException {
		
		bp = new BrokerNewPayment();
		bp.newPayment();
		Thread.sleep(1000);
		bp.carrierEmail(cemail);
		Thread.sleep(1000);
		bp.amount(amt);
		Thread.sleep(1000);
		invoice = 	bp.invoiceNumber(invoiceno);
		Thread.sleep(1000);
		bp.loadId(loadid);
		Thread.sleep(1000);
		//bp.advanceCheckbox();
		//Thread.sleep(1000);
		bp.clickShedulePayment();
		Thread.sleep(1000);
		bp.clickShedulePaymenttab();
		Thread.sleep(1000);
		bp.searchCarrier(cemail);
		Thread.sleep(1000);
		bp.clickSearchButton();
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		 bp.verifyInvoiceNumber(invoiceno,amt);
		 Thread.sleep(1000);
		Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		System.out.println(bp.verifyPaymentStatus());
		//bp.logout();
	}

}