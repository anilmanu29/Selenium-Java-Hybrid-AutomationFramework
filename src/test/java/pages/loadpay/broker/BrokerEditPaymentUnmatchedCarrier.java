package pages.loadpay.broker;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import testcases.loadpay.broker.BrokerRegisterTest;
import util.TestUtil;

public class BrokerEditPaymentUnmatchedCarrier extends TestBase {
	BrokerNewPayment brokerPaymentObj;
	BrokerLoginPage brokerLoginObj;
	String paymentStatus = "Unmatched";
	String unMatchedCarrierUsername = "";
	String unMatchedCarrierPassword = "";
	String invoiceNum = "";
	String loadId = "";
	String paymentAmount = "";
	String payto = "";
	String ein = "";

	String brokerUsername, brokerPassword = "";
	public static ArrayList<String> newPaymentAmount, newPaymentLoadId, newPaymentPayer, newPaymentInvoiceNum;

	/*-------Initializing driver---------*/
	public BrokerEditPaymentUnmatchedCarrier() {
		super();
		wait = new WebDriverWait(driver, 30);
	}

	public void setUp() {
		initialization();
		brokerLoginObj = new BrokerLoginPage();
		brokerPaymentObj = new BrokerNewPayment();
		newPaymentAmount = new ArrayList<String>();
		newPaymentLoadId = new ArrayList<String>();
		newPaymentPayer = new ArrayList<String>();
		newPaymentInvoiceNum = new ArrayList<String>();
	}

	public void loginAsBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();

		if (super.getProperties().getProperty("useDynamicBrokerData").contains("true")) {
			brokerUsername = BrokerRegisterTest.brokerUsername;
			brokerPassword = BrokerRegisterTest.brokerPassword;
		} else {
			brokerUsername = un;
			brokerPassword = pwd;
		}

		brokerLoginObj.Brokerlogin(brokerUsername, brokerPassword);
	}

	public void brokerCreateNewPayment(String cE, String iN, String lId, String pA, String pT, String Ein)
			throws InterruptedException {

		// Store data-provider elements into publicly-accessible strings

		if (super.getProperties().getProperty("useDynamicUnmatchedData").contains("true")) {
			String[] emailArray = cE.split("@");
			emailArray[0] = emailArray[0] + TestUtil.getCurrentDateTime();
			;

			unMatchedCarrierUsername = emailArray[0] + "@" + emailArray[1];
			unMatchedCarrierPassword = "Password@123";
			invoiceNum = "UM" + TestUtil.getCurrentDateTime();
			loadId = invoiceNum;
			paymentAmount = pA;
			newPaymentAmount.add(paymentAmount);
			newPaymentLoadId.add(loadId);
			newPaymentPayer.add(BrokerRegisterTest.brokerCompanyName);
			newPaymentInvoiceNum.add(invoiceNum);
		} else {
			unMatchedCarrierUsername = cE;
			invoiceNum = iN;
			loadId = lId;
			paymentAmount = pA;
		}

		payto = pT;
		ein = Ein;

		// create new payment
		brokerPaymentObj = new BrokerNewPayment();
		brokerPaymentObj.newPayment();

		brokerPaymentObj.carrierEmail(unMatchedCarrierUsername);

		brokerPaymentObj.setField_PayTo(payto);

		brokerPaymentObj.amount(paymentAmount);

		brokerPaymentObj.loadId(loadId);

		brokerPaymentObj.invoiceNumber(invoiceNum);

		brokerPaymentObj.clickShedulePayment();

		brokerPaymentObj.clickShedulePaymenttab();

		brokerPaymentObj.searchInvoice(invoiceNum);

		brokerPaymentObj.clickSearchButton();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		brokerPaymentObj.verifyInvoiceNumber(invoiceNum, paymentAmount);

		// verify payment status
		Assert.assertTrue(brokerPaymentObj.verifyPaymentStatus().equals(paymentStatus), "Payment Status not equal!");
	}

	public void verifyEditableFieldsEnabled() throws InterruptedException {
		brokerPaymentObj.clickEditIcon();

		// Verify all editable fields are enabled
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(brokerPaymentObj.getField_CarrierEmail().isEnabled(), "Carrier Email Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PayTo().isEnabled(), "Pay-To Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_CarrierDOT().isEnabled(), "Carrier DOT Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_ScheduleDate().isEnabled(), "Schedule Date Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PaymentAmount().isEnabled(), "Payment Amount Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceNum().isEnabled(), "Invoice # Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_LoadID().isEnabled(), "Load ID Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_InvoiceRecd().isEnabled(), "Invoice Received Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Memo().isEnabled(), "Memo Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getCheckbox_AdvancePayment().isEnabled(),
				"Advance Payment Checkbox Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getDropdown_OriginCountry().isEnabled(),
				"Origin Country Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_OriginCity().isEnabled(), "Origin City Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_OriginState().isEnabled(), "Origin State Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_OriginZip().isEnabled(), "Origin ZIP Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getDropdown_DestinationCountry().isEnabled(),
				"Destination Country Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DestinationCity().isEnabled(),
				"Destination City Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getDropdown_DestinationState().isEnabled(),
				"Destination State Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DestinationZip().isEnabled(),
				"Destination ZIP Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getDropdown_TrailerType().isEnabled(),
				"Trailer-tyoe Dropdown Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Miles().isEnabled(), "Miles Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_PickupDate().isEnabled(), "Pickup Date Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_DeliveryDate().isEnabled(), "Delivery Date Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getField_Commodity().isEnabled(), "Commodity Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getField_Length().isEnabled(), "Length Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Width().isEnabled(), "Width Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_Height().isEnabled(), "Height Field Disabled!");

		softAssert.assertTrue(brokerPaymentObj.getField_Weight().isEnabled(), "Weight Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_NumberOfStops().isEnabled(), "Number Of Stops Field Disabled!");
		softAssert.assertTrue(brokerPaymentObj.getField_FuelSurcharge().isEnabled(), "Fuel Surcharge Field Disabled!");

		softAssert.assertAll();
	}

	public void updatePaymentDetails(String updatedCarrierEmail, String updatedPayTo, String updatedCarrierDOT,
			String updatedScheduleDate, String updatedPaymentAmount, String updatedInvoiceNumber, String updatedLoadID,
			String updatedInvoiceRecd, String updatedMemo, String updatedAdvancedPayment, String updatedOriginCountry,
			String updatedOriginState, String updatedOriginCity, String updatedOriginZIP,
			String updatedDestinationCountry, String updatedDestinationState, String updatedDestinationCity,
			String updatedDestinationZIP, String updatedTrailerType, String updatedMiles, String updatedPickupDate,
			String updatedDeliveryDate, String updatedCommodity, String updatedLength, String updatedWidth,
			String updatedHeight, String updatedWeight, String updatedNumOfStops, String updatedFuelSurcharge)
			throws InterruptedException {
		brokerPaymentObj.setField_CarrierEmail(updatedCarrierEmail);

		brokerPaymentObj.setField_PayTo(updatedPayTo);

		brokerPaymentObj.setField_CarrierDOT(updatedCarrierDOT);

		brokerPaymentObj.setField_InvoiceRecd(updatedInvoiceRecd);

		brokerPaymentObj.setField_ScheduleDate(updatedScheduleDate);

		brokerPaymentObj.setField_PaymentAmount(updatedPaymentAmount);

		brokerPaymentObj.setField_InvoiceNum(updatedInvoiceNumber);

		brokerPaymentObj.setField_LoadID(updatedLoadID);

		brokerPaymentObj.setField_Memo(updatedMemo);

		brokerPaymentObj.setDropdown_OriginCountry(updatedOriginCountry);

		brokerPaymentObj.setField_OriginState(updatedOriginState);

		brokerPaymentObj.setField_OriginCity(updatedOriginCity);

		brokerPaymentObj.setField_OriginZip(updatedOriginZIP);

		brokerPaymentObj.setDropdown_DestinationCountry(updatedDestinationCountry);

		brokerPaymentObj.setDropdown_DestinationState(updatedDestinationState);

		brokerPaymentObj.setField_DestinationCity(updatedDestinationCity);

		brokerPaymentObj.setField_DestinationZip(updatedDestinationZIP);

		brokerPaymentObj.setDropdown_TrailerType(updatedTrailerType);

		brokerPaymentObj.setField_Miles(updatedMiles);

		brokerPaymentObj.setField_PickupDate(updatedPickupDate);

		brokerPaymentObj.setField_DeliveryDate(updatedDeliveryDate);

		brokerPaymentObj.setField_Commodity(updatedCommodity);

		brokerPaymentObj.setField_Length(updatedLength);

		brokerPaymentObj.setField_Width(updatedWidth);

		brokerPaymentObj.setField_Height(updatedHeight);

		brokerPaymentObj.setField_Weight(updatedWeight);

		brokerPaymentObj.setField_NumberOfStops(updatedNumOfStops);

		brokerPaymentObj.setField_FuelSurcharge(updatedFuelSurcharge);

		brokerPaymentObj.clickShedulePayment();

		brokerPaymentObj.searchInvoice(updatedInvoiceNumber);
		Assert.assertTrue(brokerPaymentObj.isEditIconEnabled(), "Edit icon not found for updated payment");
	}
}
