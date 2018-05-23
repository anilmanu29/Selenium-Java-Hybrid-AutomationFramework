package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CarrierLoginPage;
import pages.CarrierWireTransfer;
import pages.UnmatchedCarrierWireTransfer;

public class UnmatchedCarrierWireTransferTest extends TestBase {


	UnmatchedCarrierWireTransfer uct;

	/*-------Initializing driver---------*/
	public UnmatchedCarrierWireTransferTest() {
		super();
	}

	@BeforeClass
	public void setUp() {

		initialization();
		uct= new UnmatchedCarrierWireTransfer();
	
	}
	/*-------Initializing driver---------*/

	/*-------Login to Load Pay as Broker---------*/

	@Test(priority = 41)
	public void carrierPaymenowWirTransfer() throws InterruptedException {
		uct.carrierLogin(); 
		uct.getAmount();
		uct.clickPaymenow();
		uct.getwiretransferAmount();
		uct.clickSelectButton();
		uct.clickConfirmButton();
		uct.clickPaidTab();
		uct.gettotalpaiyAmount();
		uct.verifywiretransfer();
	} 

}
