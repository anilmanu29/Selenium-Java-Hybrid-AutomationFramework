package pages.loadpay.broker;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class ShipperPaymentHistory extends TestBase {

	WebDriverWait wait = null;
	JavascriptExecutor js;
	Actions act = null;

	// Page Factory - OR:
	@FindBy(xpath = "//a[text()='Payment History']")
	public WebElement paymenthistorylink;

	@FindBy(xpath = "//input[@value='All']")
	public WebElement allbutton;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement searchbutton;

	@FindBy(xpath = "//button[contains(text(),'Export')]")
	public WebElement exportbutton;

	@FindBy(xpath = "//span[contains(text(),'FILTERS')]")
	public WebElement filters;

	@FindBy(xpath = "//*[@aria-expanded='true']")
	public WebElement currentmonth;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[1]/div[4]/div[5]/div[2]/div/div[1]/h3/span")
	public WebElement filterss;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[1]/div[4]/div[5]/div[1]/uib-accordion/div/div/div[1]/h4/a/span/div//following::div[1]")
	public WebElement currentmonthstatus;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::*[contains(@class,'carrierPayment ng-scope')]")
	public List<WebElement> payments;

	@FindBy(xpath = "//li[@class='filterListItem']//child::input[@type='checkbox']")
	public List<WebElement> filtercheckboxes;

	@FindBy(xpath = "//span[text()='PayMeNow']//preceding::input[@type='checkbox']")
	public WebElement paymenowcheckbox;

	@FindBy(xpath = "//span[text()='Paid']//preceding::input[@type='checkbox'][1]")
	public WebElement paidcheckbox;

	@FindBy(xpath = "//span[text()='Failed']//preceding::input[@type='checkbox'][1]")
	public WebElement failedcheckbox;

	@FindBy(xpath = "//span[text()='Canceled']//preceding::input[@type='checkbox'][1]")
	public WebElement cancelledcheckbox;

	@FindBy(xpath = "//*[contains(@class,'carrierPayment ')]//following::*[@aria-expanded='true']//child::div[@class='row detailLineThree']//child::div[2]//preceding::div[2][contains(@class,'ng-scope')]/span")
	public WebElement paymenowpaymentstatus;

	@FindBy(id = "searchKeyword")
	public WebElement searchfield;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement searchbuttonn;

	@FindBy(xpath = "//input[@value='Advanced search']")
	public WebElement advancedsearchlink;

	@FindBy(xpath = "//label[text()='Invoice Amount Range']")
	public WebElement invoiceamtrange;

	@FindBy(xpath = "//label[text()='Date Range']")
	public WebElement daterange;

	@FindBy(id = "AmountRangeFrom")
	public WebElement amountrangefrom;

	@FindBy(id = "AmountRangeTo")
	public WebElement amountrangeto;

	@FindBy(id = "TermStartDate")
	public WebElement startdateInputField;

	@FindBy(id = "TermEndDate")
	public WebElement enddate;

	@FindBy(xpath = "//a[@title='Prev']")
	public WebElement dateprevious;

	@FindBy(xpath = "//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[3]/a")
	public WebElement startdateselect;

	@FindBy(xpath = "//*[contains(@class, 'ui-state-highlight')]")
	public WebElement enddateselect;

	@FindBy(id = "ExportStartDate")
	public WebElement exportstartdate;

	@FindBy(id = "ExportEndDate")
	public WebElement exportenddate;

	@FindBy(xpath = "//span[text()='Basic']//preceding::input[@type='radio']")
	public WebElement basicradiobutton;

	@FindBy(xpath = "//span[text()='Detailed']//preceding::input[@type='radio'][1]")
	public WebElement detailedradiobutton;

	@FindBy(xpath = ".//input[@value='Export']")
	public WebElement reportexportbutton;

	@FindBy(xpath = "//div[@field-name='Status']")
	public WebElement statuscolumn;

	@FindBy(xpath = "//div[@field-name='Pay Selection']")
	public WebElement payselectioncolumn;

	@FindBy(xpath = ".//div[@field-name='Amount']")
	public WebElement amountcolumn;

	@FindBy(xpath = "//div[@field-name='Carrier']")
	public WebElement carriercolumn;

	@FindBy(xpath = "//div[@field-name='Load ID']")
	public WebElement loadidcolumn;

	@FindBy(xpath = "//*[contains(@class,'fa-share-square pull-right')]")
	public List<WebElement> arrowexportbutton;

	@FindBy(xpath = "//*[contains(@class,'fa-angle-down')]")
	public List<WebElement> monthslist;

	@FindBy(xpath = "//*[contains(@class,'fa-angle-up')]")
	public WebElement monthup;

	@FindBy(xpath = "//*[contains(@class,'fa-angle-up')]")
	public List<WebElement> monthsup;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[1]/div[4]/div[5]/div[1]/uib-accordion/div/div/div[1]/h4/a/span/div")
	public List<WebElement> numofmonthgrids;

	// Initializing the Page Objects:
	public ShipperPaymentHistory() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		act = new Actions(driver);

	}

	public void clickPaymentHistorylink() {
		wait.until(ExpectedConditions.elementToBeClickable(paymenthistorylink));
		paymenthistorylink.click();
	}

	public void clickCurrentMonth() throws InterruptedException {
		// verifyng months can be rolled up
		Assert.assertTrue(currentmonthstatus.getAttribute("aria-expanded").contains("true"),
				"Month should be Expanded");
		wait.until(ExpectedConditions.elementToBeClickable(monthup));
		monthup.click();

	}

	public void expandcollapsePayments() throws InterruptedException {

		for (WebElement month : monthslist) {
			month.click();

			for (WebElement payment : payments) {
				wait.until(ExpectedConditions.elementToBeClickable(payment));

				payment.click();
				Thread.sleep(3000);
				Assert.assertTrue(payment.getAttribute("aria-expanded").contains("true"), "Payment should be expanded");

				js.executeScript("window.scrollBy(0,40)", "");
				Thread.sleep(1000);

				payment.click();
				Thread.sleep(3000);
				Assert.assertTrue(payment.getAttribute("aria-expanded").contains("false"),
						"Payment should be collapsed");

				break;
			}

			break;
		}

		if (monthup.isDisplayed()) {
			monthup.click();
		}

	}

	public void filtercheckboxes() throws InterruptedException {

		for (WebElement filtercheckbox : filtercheckboxes) {
			js.executeScript("window.scrollBy(0,-250)", "");
			if (filtercheckbox.isSelected()) {
				wait.until(ExpectedConditions.elementToBeClickable(filtercheckbox));
				js.executeScript("arguments[0].click();", filtercheckbox);
			} else {
				js.executeScript("arguments[0].click();", filtercheckbox);
				try {
					WebElement loadingGif = driver
							.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[2]/img"));
					while (loadingGif.isDisplayed()) {
						Thread.sleep(3000);
						log.info("Waiting for Loading Gif to disappear!");

					}

				} catch (Exception e) {
					log.info("Waiting for Loading Gif is NOT displayed");
				}
			}
		}

	}

	public void clickFilterCheckbox(WebElement element, String paymentstatus) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);

		try {
			WebElement loadingGif = driver
					.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[2]/img"));
			while (loadingGif.isDisplayed()) {
				Thread.sleep(3000);
				log.info("Waiting for Loading Gif to disappear!");

			}

		} catch (Exception e) {
			log.info("Waiting for Loading Gif is NOT displayed");
		}

		try {
			if (!driver.findElement(By.xpath(".//*[@role='alert']")).isDisplayed())

				for (WebElement month : monthslist) {
					wait.until(ExpectedConditions.elementToBeClickable(month));
					js.executeScript("arguments[0].click();", month);
				}

			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			log.info("Selected Filter Payments are NOT Available");
		}

		js.executeScript("window.scrollBy(0,-100)", "");
	}

	public void clickSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchbutton));
		js.executeScript("arguments[0].click();", searchbutton);
		try {
			WebElement loadingGif = driver
					.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[2]/img"));
			while (loadingGif.isDisplayed()) {
				Thread.sleep(3000);
				log.info("Waiting for Loading Gif to disappear!");

			}

		} catch (Exception e) {
			log.info("Waiting for Loading Gif is NOT displayed");
		}

	}

	public void searchAction(String searchtext) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchfield));
		searchfield.clear();
		searchfield.sendKeys(searchtext);
		js.executeScript("arguments[0].click();", searchbuttonn);
		try {
			WebElement loadingGif = driver
					.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[2]/img"));
			while (loadingGif.isDisplayed()) {
				Thread.sleep(3000);
				log.info("Waiting for Loading Gif to disappear!");

			}

		} catch (Exception e) {
			log.info("Waiting for Loading Gif is NOT displayed");
		}

	}

	public void AdvancedSearchLinkAction(String amt, String maxamt, String begindate, String enddatee)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(advancedsearchlink));
		js.executeScript("arguments[0].click();", advancedsearchlink);

		try {
			WebElement loadingGif = driver
					.findElement(By.xpath("//*[@id='angularScope']/div[2]/div/div[3]/div/div/div/div[2]/img"));
			while (loadingGif.isDisplayed()) {
				Thread.sleep(3000);
				log.info("Waiting for Loading Gif to disappear!");

			}

		} catch (Exception e) {
			log.info("Waiting for Loading Gif is NOT displayed");

		}
		Assert.assertTrue(invoiceamtrange.isDisplayed(), "Invoice Amount Rage field NOT found");
		Assert.assertTrue(daterange.isDisplayed(), "Date Rage field NOT found");
		amountrangefrom.clear();
		amountrangefrom.sendKeys(amt);
		amountrangeto.clear();
		amountrangeto.sendKeys(maxamt);
		wait.until(ExpectedConditions.elementToBeClickable(startdateInputField));
		startdateInputField.clear();
		// act.moveToElement(startdateInputField).click().perform();
		startdateInputField.sendKeys(begindate);
		startdateInputField.sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.elementToBeClickable(enddate));
		enddate.clear();
		enddate.sendKeys(enddatee);
		enddate.sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.elementToBeClickable(searchbuttonn));
		js.executeScript("arguments[0].click();", searchbuttonn);

		if (numofmonthgrids.size() > 1) {
			for (WebElement monthup : monthsup) {
				wait.until(ExpectedConditions.elementToBeClickable(monthup));
				js.executeScript("arguments[0].click();", monthup);
			}

			for (WebElement month : monthslist) {
				wait.until(ExpectedConditions.elementToBeClickable(month));
				js.executeScript("arguments[0].click();", month);

				for (WebElement payment : payments) {

					wait.until(ExpectedConditions.elementToBeClickable(payment));
					js.executeScript("arguments[0].click();", payment);
					Thread.sleep(2000);
					Assert.assertTrue(payment.getAttribute("aria-expanded").contains("true"),
							"Payment should be expanded");
					js.executeScript("window.scrollBy(0,80)", "");
					js.executeScript("arguments[0].click();", payment);

					break;
				}
				wait.until(ExpectedConditions.elementToBeClickable(month));
				js.executeScript("window.scrollBy(0,200)", "");
				break;
			}
		} else {
			for (WebElement payment : payments) {
				wait.until(ExpectedConditions.elementToBeClickable(payment));

				payment.click();
				Thread.sleep(3000);
				Assert.assertTrue(payment.getAttribute("aria-expanded").contains("true"), "Payment should be expanded");

				payment.click();
				Thread.sleep(3000);
				Assert.assertTrue(payment.getAttribute("aria-expanded").contains("false"),
						"Payment should be collapsed");
				break;
			}
		}
	}

	public void clickExportButton() {
		wait.until(ExpectedConditions.elementToBeClickable(exportbutton));
		js.executeScript("arguments[0].click();", exportbutton);

	}

	public void clickandEnterExportstartandEnddate(String exportbasicstartdate, String exportbasicenddate)
			throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(exportstartdate));
		exportstartdate.clear();
		exportstartdate.sendKeys(exportbasicstartdate);
		exportstartdate.sendKeys(Keys.TAB);
		wait.until(ExpectedConditions.elementToBeClickable(exportenddate));
		exportenddate.clear();
		exportenddate.sendKeys(exportbasicenddate);
		exportenddate.sendKeys(Keys.TAB);

	}

	public void clickRadioButton(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].click();", element);
	}

	public void clickReportExportButton() {
		wait.until(ExpectedConditions.elementToBeClickable(reportexportbutton));
		js.executeScript("arguments[0].click();", reportexportbutton);

	}

	public void clickArrowExportButton() throws InterruptedException {

		for (WebElement arrowexport : arrowexportbutton) {
			wait.until(ExpectedConditions.elementToBeClickable(arrowexport));
			js.executeScript("arguments[0].click();", arrowexport);

			act.moveToElement(currentmonth).click().perform();
		}

	}

}
