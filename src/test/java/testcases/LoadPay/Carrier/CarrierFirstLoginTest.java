package testcases.LoadPay.Carrier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Carrier.CarrierFirstLogin;
import testcases.LoadPay.Broker.BrokerPaymentforUnmatchedCarrierTest;


public class CarrierFirstLoginTest extends TestBase{
	CarrierFirstLogin loginPage;
	
	public CarrierFirstLoginTest()
	{
		super();
	}
	
	@BeforeClass
	public void setUp()
	{
		initialization();
		loginPage = new CarrierFirstLogin();	
	}

	@Test(priority=40)
	public void loginTest() throws InterruptedException
	{
		loginPage.carrierfirstLogin();
		loginPage.clickNext(BrokerPaymentforUnmatchedCarrierTest.einno);
		loginPage.clickAcceptCheckbox();
		loginPage.clickEmailcheckbox();
		loginPage.clickFinish();
		loginPage.clickClose();	
	}

}