package testcases.LoadPay.Unmatched;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Carrier.CarrierLoginPage;
import pages.LoadPay.Carrier.CarrierSameDAYACH;
import pages.LoadPay.Unmatched.UnmatchedCarrierSameDAYACH;

public class UnmatchedCarrierSameDayACHTest extends TestBase {

	
	UnmatchedCarrierSameDAYACH usdach;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierSameDayACHTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		usdach = new UnmatchedCarrierSameDAYACH();
	
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	
	

	

	@Test(priority = 32)
	public void carrierPaymenowSameDayACH() throws InterruptedException {
		
		
		usdach.carrierLogin();
		usdach.getAmount();
		usdach.clickPaymenow();
		usdach.getsamedayAmount();
		usdach.clickSelectButton();
		usdach.clickConfirmButton();
		usdach.clickPaidTab();
		usdach.gettotalpaiyAmount();
		usdach.verifySamedayach();
//		
	}

}