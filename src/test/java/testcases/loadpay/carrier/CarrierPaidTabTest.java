package testcases.loadpay.carrier;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import pages.loadpay.carrier.CarrierLoginPage;
import pages.loadpay.carrier.CarrierNextDAYACH;
import pages.loadpay.carrier.CarrierPaidTab;
import testcases.loadpay.broker.BrokerNewPaymentTest;
import util.TestUtil;

public class CarrierPaidTabTest extends TestBase {
	CarrierLoginPage loginPage;
	CarrierPaidTab carrierPaidTab;
	CarrierNextDAYACH carriernextdayachobj;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	String searchStatusText = "";
	String searchAmountText = "";
	String searchPayerText = "";
	String searchLoadIdText = "";
	String carrierUsername, carrierPassword = "";

	public CarrierPaidTabTest() {
		super();

	}

	@BeforeClass
	public void setUp() {
		initialization();
		TestUtil.className = this.getClass().getName();
		loginPage = new CarrierLoginPage();
		carrierPaidTab = new CarrierPaidTab();
		carriernextdayachobj = new CarrierNextDAYACH();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_Login", dataProvider = "getCarrierPaidTabData")
	public void loginAsCarrier(String carrierEmail, String carrierPW, String statusText, String amountText,
			String payerText, String loadIdText) throws InterruptedException, AWTException {
		// login as carrier

		if (super.getProperties().getProperty("useDynamicCarrierData").contains("true")) {
			carrierUsername = CarrierRegisterTest.carrierUsername;
			carrierPassword = CarrierRegisterTest.carrierPassword;
			amountText = BrokerNewPaymentTest.newPaymentAmount.get(0);
			payerText = BrokerNewPaymentTest.newPaymentPayer.get(0);
			loadIdText = BrokerNewPaymentTest.newPaymentLoadId.get(0);
		} else {
			carrierUsername = carrierEmail;
			carrierPassword = carrierPW;
		}

		loginPage.Carrierlogin(carrierUsername, carrierPassword);

		carriernextdayachobj.clickPaymenow();
		carriernextdayachobj.clickSelectButton();
		carriernextdayachobj.clickConfirmButton();
		loginPage.closePaymeNowPopUp();

		searchStatusText = statusText;
		searchAmountText = TestUtil.removeDecimalZeroFormat(amountText);
		searchPayerText = payerText;
		searchLoadIdText = TestUtil.removeDecimalZeroFormat(loadIdText);

		// Assert PayMeNow, Scheduled and Paid tabs exist
		verifyCarrierTabsDisplayed();
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyPaidTab", dependsOnMethods = { "loginAsCarrier" })
	public void verifyPaidTabTest() throws InterruptedException {
		// click Paid Tab
		carrierPaidTab.clickPaidTab();

		// Assert search bar, search button, and table columns are displayed
		verifyPaidTabElementsDisplayed();
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyStatusSort", dependsOnMethods = { "verifyPaidTabTest" })
	public void verifySelectionMethodSortTest() throws InterruptedException {
		// TEST - STATUS SORT
		// click Status Column to change sort from default to ascending
		carrierPaidTab.clickselectionmethodColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Status Column to change sort from ascending to descending
		carrierPaidTab.clickselectionmethodColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare sorted data sets
		// TODO
		// Uncomment Assertion when LP-3241 https://gojira.truckstop.com/browse/LP-3241
		// is resolved

		if (carrierPaidTab.getRowCount() > 1)
			Assert.assertEquals(firstRowData, lastRowData,
					"First Row Data: \n" + firstRowData + "\nLast Row Data: \n" + lastRowData);

	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyAmountSort", dependsOnMethods = {
			"verifySelectionMethodSortTest" })
	public void verifyAmountSortTest() throws InterruptedException {
		// TEST - AMOUNT SORT
		// click Amount Column to change sort from default to ascending
		carrierPaidTab.clickAmountColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Amount Column to change sort from ascending to descending
		carrierPaidTab.clickAmountColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Thread.sleep(2000);
		/*
		 * if (carrierPaidTab.getRowCount() > 1) Assert.assertNotEquals(firstRowData,
		 * lastRowData, "First Row Data: \n" + firstRowData + "\nLast Row Data: \n" +
		 * lastRowData);
		 */
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyPayerSort", dependsOnMethods = { "verifyAmountSortTest" })
	public void verifyPayerSortTest() throws InterruptedException {
		// TEST - PAYER SORT
		// click Payer Column to change sort from default to ascending
		carrierPaidTab.clickPayerColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click Payer Column to change sort from ascending to descending
		carrierPaidTab.clickPayerColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertTrue(firstRowData.size() > 0, "Data not found - row count = 0");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifyLoadIdSort", dependsOnMethods = { "verifyPayerSortTest" })
	public void verifyLoadIdSortTest() throws InterruptedException {
		// TEST - LoadID SORT
		// click LoadID Column to change sort from default to ascending
		carrierPaidTab.clickLoadIDColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		// click LoadID Column to change sort from ascending to descending
		carrierPaidTab.clickLoadIDColumn();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = carrierPaidTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertTrue(firstRowData.size() > 0, "Data not found - row count = 0");
	}

	// TODO
	// Enable when LP-3241 https://gojira.truckstop.com/browse/LP-3241 is resolved
	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchStatus", enabled = false, dependsOnMethods = {
			"verifyLoadIdSortTest" })
	public void verifySearchStatusTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchStatusText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by status");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchAmount", dependsOnMethods = { "verifyLoadIdSortTest" })
	public void verifySearchAmountTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchAmountText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by amount");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchPayer", dependsOnMethods = { "verifySearchAmountTest" })
	public void verifySearchPayerTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchPayerText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by payer");
	}

	@Test(description = "LP-3476 CarrierPaidTabTest_VerifySearchLoadId", dependsOnMethods = { "verifySearchPayerTest" })
	public void verifySearchLoadIdTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		carrierPaidTab.enterSearchText(searchLoadIdText);
		carrierPaidTab.clickSearchButton();
		// click first row to expand
		carrierPaidTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = carrierPaidTab.getFirstRowData();
		Assert.assertTrue(firstRowData.size() > 0, "No data rows found when searching by load ID");
	}

	public void verifyCarrierTabsDisplayed() throws InterruptedException {

		// Verify that the Tab Text for Carriers is found
		Thread.sleep(2000);
		Assert.assertTrue(carrierPaidTab.payMeNowTab.getText().contains("PayMeNowTM"), "PayMeNow Tab not found");
		Assert.assertTrue(carrierPaidTab.schedulePaymentsTab.getText().contains("SCHEDULED PAYMENTS"),
				"Scheduled Payments Tab not found");
		Assert.assertTrue(carrierPaidTab.paidTab.getText().contains("PAID"), "Paid Tab not found");
	}

	public void verifyPaidTabElementsDisplayed() {

		// Verify that the web elements for the Paid tab exist
		Assert.assertTrue(carrierPaidTab.selectionmethodColumn.getText().contains("Selection Method"),
				"selectionmethod Column not found");
		Assert.assertTrue(carrierPaidTab.amountColumn.getText().contains("Amount"), "Amount Column not found");
		Assert.assertTrue(carrierPaidTab.payerColumn.getText().contains("Payer"), "Payer Column not found");
		Assert.assertTrue(carrierPaidTab.loadIDColumn.getText().contains("Load ID"), "Load ID not found");
		Assert.assertTrue(carrierPaidTab.searchButton.isDisplayed(), "Search Button not found");
		Assert.assertTrue(carrierPaidTab.searchInputField.isDisplayed(), "Search Input Field not found");
	}

}
