package testcases.LoadPay.Broker;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.TestBase;
import pages.LoadPay.Broker.BrokerLoginPage;
import pages.LoadPay.Broker.BrokerProcessedTab;

public class BrokerProcessedTabTest extends TestBase {
	BrokerLoginPage loginPage;
	BrokerProcessedTab brokerProcessedTab;
	List<String> firstRowData = null;
	List<String> lastRowData = null;
	JavascriptExecutor jse;
	String searchForInvoice, searchforcompanyname, searchforLoadID, searchforAmount, searchforDate  = "";

	public BrokerProcessedTabTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new BrokerLoginPage();
		brokerProcessedTab = new BrokerProcessedTab();
		jse = (JavascriptExecutor) driver;
	}

	@Test(dataProvider = "getBrokerLoginData", priority = 17)
	public void loginAsBroker(String un, String pwd) throws InterruptedException {
		// login as broker
		loginPage.Brokerlogin(un, pwd);
	}

	@Test(dependsOnMethods = { "loginAsBroker" }, priority = 18)
	public void verifyProcessedTabTest() throws InterruptedException {

		brokerProcessedTab.clickProcessedTab();
		verifyProcessTabElementsDisplayed();
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 19)
	public void verifyPaySelectionSortTest() throws InterruptedException {
		// TEST - Pay Selection SORT
		// click pay selection Column to change sort from default to ascending
		brokerProcessedTab.clickPaySelectionColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click pay selection Column to change sort from ascending to descending
		brokerProcessedTab.clickPaySelectionColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 20)
	public void verifyDiscountSortTest() throws InterruptedException {
		// TEST - Discount SORT
		// click Discount Column to change sort from default to ascending
		brokerProcessedTab.clickDiscountColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Discount Column to change sort from ascending to descending
		brokerProcessedTab.clickDiscountColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by Discount!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 21)
	public void verifyPulledFromBankDateSortTest() throws InterruptedException {
		// TEST - Pulled from Bank Date
		// click Pulled from Bank Date to change sort from default to ascending
		brokerProcessedTab.clickPulledfromBankdataColumnColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerProcessedTab.clickPulledfromBankdataColumnColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by Pulled from Bank Date!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 22)
	public void verifyPaidToCarrierSortTest() throws InterruptedException {
		// TEST - Paid to Carrier
		// click Paid to Carrier to change sort from default to ascending
		brokerProcessedTab.clickPaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Pulled from Bank Date to change sort from ascending to descending
		brokerProcessedTab.clickPaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Paid to Carrier!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 23)
	public void verifyAmountPulledSortTest() throws InterruptedException {
		// TEST - Amount Pulled
		// click Amount Pulled to change sort from default to ascending
		brokerProcessedTab.clickAmountPulledColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Amount Pulled to change sort from ascending to descending
		brokerProcessedTab.clickAmountPulledColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 24)
	public void verifyAmountPaidToCarrierSortTest() throws InterruptedException {
		// TEST - Amount Paid To Carrier
		// click Amount Paid ToCarrier sort from default to ascending
		brokerProcessedTab.clickAmountpaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Amount Paid To Carrier change sort from ascending to descending
		brokerProcessedTab.clickAmountpaidToCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData,
				"Data appears to be equal when sorted by  Paid to Amount Pulled!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 25)
	public void verifyCarrierSortTest() throws InterruptedException {
		// TEST - Carrier
		// click Carrier sort from default to ascending
		brokerProcessedTab.clickCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click Carrier change sort from ascending to descending
		brokerProcessedTab.clickCarrierColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  Carrier!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 26)
	public void verifyLoadIDSortTest() throws InterruptedException {
		// TEST - LoadID
		// click LoadID sort from default to ascending
		brokerProcessedTab.clickLoadIDColumn();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		// click first row to expand
		//brokerProcessedTab.clickFirstRow();
		searchForInvoice = brokerProcessedTab.invoice.getText();
		searchforcompanyname = brokerProcessedTab.companyname.getText();
		searchforLoadID = brokerProcessedTab.loadid.getText();
		searchforDate = 	brokerProcessedTab.date.getAttribute("innerText").replace("PayMeNowTM", "");
		searchforAmount =	brokerProcessedTab.amount.getAttribute("innerText").replace("INVOICE AMOUNT:$", "");
		System.out.println(searchforAmount);
	
		// get the data elements from the first row displayed
		lastRowData = brokerProcessedTab.getFirstRowData();
		// compare to the database when sorted by given column-Descending
		//Assert.assertNotEquals(firstRowData, lastRowData, "Data appears to be equal when sorted by  LoadID!");
	}

	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 27)
	public void verifyCompanySearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerProcessedTab.enterSearchText(searchforcompanyname);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforcompanyname),
				"Matching text [" + searchforcompanyname + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 28)
	public void verifyInvoiceSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchForInvoice);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchForInvoice),
				"Matching text [" + searchForInvoice + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 29)
	public void verifyLoadIDSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchforLoadID);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforLoadID),
				"Matching text [" + searchforLoadID + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 30)
	public void verifyAmountSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		brokerProcessedTab.enterSearchText(searchforAmount);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforAmount),
				"Matching text [" + searchforAmount + "] NOT found in [" + firstRowData + "]");

	}
	
	@Test(dependsOnMethods = { "verifyProcessedTabTest" }, priority = 31)
	public void verifyDateSearchTest() throws InterruptedException {
		// TEST - SEARCH VERIFICATION
		
		brokerProcessedTab.enterSearchText(searchforDate);
		brokerProcessedTab.clickSearchButton();
		// click first row to expand
		brokerProcessedTab.clickFirstRow();
		// get the data elements from the first row displayed
		firstRowData = brokerProcessedTab.getFirstRowData();
		Assert.assertTrue(firstRowData.get(0).contains(searchforDate),
				"Matching text [" + searchforDate + "] NOT found in [" + firstRowData + "]");
	}
	


	public void verifyProcessTabElementsDisplayed() {

		// Verify that the web elements for the Processed tab exist
		Assert.assertTrue(brokerProcessedTab.paySelectionColumn.isDisplayed(), "PaySelection Column not found");
		Assert.assertTrue(brokerProcessedTab.discountcolumn.isDisplayed(), "Discount Column not found");
		Assert.assertTrue(brokerProcessedTab.pulledfrombankdataColumn.isDisplayed(),
				"Pulled from Bank data Column not found");
		Assert.assertTrue(brokerProcessedTab.paidtocarrierDColumn.isDisplayed(), "Paid to Carrier Column not found");
		Assert.assertTrue(brokerProcessedTab.amountpulledColumn.isDisplayed(), "Amount Pulled column not found");
		Assert.assertTrue(brokerProcessedTab.amountpulledColumn.isDisplayed(), "Amount Pulled column not found");
		Assert.assertTrue(brokerProcessedTab.amountpaidtocarrierColumn.isDisplayed(),
				"Amount Paid to carrier column not found");
		Assert.assertTrue(brokerProcessedTab.carriercolumn.isDisplayed(), "Carrier column not found");
		Assert.assertTrue(brokerProcessedTab.loadidColumn.isDisplayed(), "LoadId column not found");
		Assert.assertNotNull(brokerProcessedTab.searchInputField, "Search Input Field not found");
		Assert.assertNotNull(brokerProcessedTab.searchButton, "Search Button not found");

	}

}