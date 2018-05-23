package testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.BrokerLoginPage;
import pages.BrokerNewPayment;
import pages.CarrierLoginPage;
import pages.ShipperAdvancePayment;

public class ShipperAdvancePaymentTest extends TestBase 
{
	
	ShipperAdvancePayment bp;
	BrokerLoginPage bl;
	CarrierLoginPage loginPage;
	String  payment_status = "Verified";
	String paymentdate;
	 static String invoice;
	 String loadidd;
	
	/*-------Initializing driver---------*/
	public ShipperAdvancePaymentTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		
		initialization();
		bl = new BrokerLoginPage();	
		bp = new ShipperAdvancePayment();
		loginPage = new CarrierLoginPage();
	}
	/*-------Initializing driver---------*/
	
	/*-------Login to Load Pay as Broker---------*/
	
	
	@Test(dataProvider="getBrokerLoginData", priority=16)
	public void loginBroker(String un, String pwd)
	{
		bl= new BrokerLoginPage();
		bl.Brokerlogin(un, pwd);
		
	}
	
	/*-------Scheduling New Payment as a Broker---------*/
	
	@Test(dataProvider="getPaymentData", priority=17)
	public void brokernewPayment(String cemail, String invoiceno, String loadid, String amt) throws InterruptedException, InvalidFormatException, IOException {
		
		bp.newPayment();
		Thread.sleep(1000);
		bp.carrierEmail(cemail);
		Thread.sleep(1000);
		bp.amount(amt);
		Thread.sleep(1000);
		bp.invoiceNumber(invoiceno);
		Thread.sleep(1000);
		loadidd = bp.loadId(loadid);
		Thread.sleep(1000);
		bp.advanceCheckbox();
		Thread.sleep(1000);
		paymentdate = bp.getPaymentDate();
		System.out.println(paymentdate);
		bp.clickShedulePayment();
		Thread.sleep(1000);
		bp.clickShedulePaymenttab();
		Thread.sleep(1000);
		bp.searchCarrier(cemail);
		Thread.sleep(1000);
		bp.clickSearchButton();
		Thread.sleep(1000);
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);*/
		//Assert.assertEquals(bp.verifyPaymentStatus(), payment_status);
		//System.out.println(bp.verifyPaymentStatus());
		bp.logout();
		 Thread.sleep(1000);
	}
	
	
	@Test(dataProvider="getCarrierLoginData",priority = 18)
	public void loginTest(String user,String pass) throws InterruptedException
	{
		
		loginPage.Carrierlogin(user, pass);
		Thread.sleep(5000);
		 bp.verifyScheduledDate(paymentdate, loadidd);
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			Thread.sleep(2000);
	}

}
