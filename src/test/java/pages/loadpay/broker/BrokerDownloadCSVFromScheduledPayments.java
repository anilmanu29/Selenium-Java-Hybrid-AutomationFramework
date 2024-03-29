package pages.loadpay.broker;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class BrokerDownloadCSVFromScheduledPayments extends TestBase {
	BrokerLoginPage brokerLoginObj;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//a[@href='#/Payments/ScheduledPayments']")
	private WebElement scheduledpaymentstab;

	@FindBy(xpath = "//div[contains(@ng-show, 'ScheduledPayments')]//child::button[@ng-click='Export();']")
	private WebElement exportbutoon;

	/*-------Initializing driver---------*/
	public BrokerDownloadCSVFromScheduledPayments() {
		PageFactory.initElements(driver, this);
		brokerLoginObj = new BrokerLoginPage();
		wait = new WebDriverWait(driver, 30);
	}

	public void loginAsBroker(String un, String pwd) {
		brokerLoginObj = new BrokerLoginPage();
		brokerLoginObj.Brokerlogin(un, pwd);
	}

	public WebElement getScheduledPaymenttab() {
		wait.until(ExpectedConditions.elementToBeClickable(scheduledpaymentstab));
		return scheduledpaymentstab;
	}

	public void clickScheduledPaymetsTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(scheduledpaymentstab));
		Thread.sleep(1000);
		scheduledpaymentstab.click();
		Thread.sleep(1000);
	}

	public WebElement getExportButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(exportbutoon));
		Thread.sleep(1000);
		return exportbutoon;
	}

	public void clickExportButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(exportbutoon));
		Thread.sleep(1000);
		exportbutoon.click();
		Thread.sleep(1000);

	}

}
