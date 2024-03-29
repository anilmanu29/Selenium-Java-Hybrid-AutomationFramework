package pages.loadpay.unmatched;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import testcases.loadpay.broker.BrokerPaymentforUnmatchedCarrierTest;

public class UnmatchedCariierEmailVerifyWiretRansfer extends TestBase {

	WebDriverWait wait = null;
	// Page Factory - OR:
	@FindBy(id = "username")
	WebElement UserName;

	@FindBy(xpath = ".//*[@id='lgnDiv']/div[9]/div/span")
	WebElement loginButton;

	@FindBy(id = "password")
	WebElement Password;

	@FindBy(xpath = "//button[contains(@class,'_hl_2 _hl_e ms-font-weight-regular')]")
	WebElement haspopup;

	@FindBy(xpath = "//span[contains(text(),'Open another mailbox')]")
	WebElement lnkopenanothermail;

	@FindBy(xpath = "//span[contains(text(),'Search mail and people')]")
	WebElement fieldSearchMail;

	@FindBy(xpath = "//input[@role='textbox']")
	WebElement fieldTextbox;

	@FindBy(xpath = "//*[@title='Loadpay Test All']")
	WebElement searchSuggestion;

	@FindBy(xpath = "//span[text()='Open']")
	WebElement buttonOpen;

	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Click to Verify ']")
	WebElement linkVerify;

	@FindBy(xpath = "//div[@id='page-main']//child::*[text()='Email Verified']")
	WebElement emailverifymessage;

	@FindBy(xpath = "//button[@type='button'][@aria-label='Activate Search Textbox']")
	WebElement fieldsearch;

	@FindBy(xpath = "//input[@aria-label='Search. Press Enter to Start Searching.']")
	WebElement fieldsearchinput;

	@FindBy(xpath = ".//*[contains(@class,'lvHighlightAllClass')]//following::*[text()='Verify your email address']")
	WebElement verifyImailMail;

	@FindBy(xpath = "//button[@aria-label='Start search']")
	WebElement btnsearch;

	@FindBy(xpath = "//*[contains(@class,'lvHighlightAllClass')][text()='Verify your email address']")
	WebElement verifyEmail;

	@FindBy(xpath = "//span[text()='Search contacts and directory']")
	WebElement buttonsearchcontacts;

	@FindBy(xpath = "//*[@role='menuitemradio'][text()='Unread']")
	WebElement lnkunread;

	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	WebElement emailid;

	// Initializing the Page Objects:
	public UnmatchedCariierEmailVerifyWiretRansfer() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void clickPopUp() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldSearchMail));
		Thread.sleep(1000);
		fieldSearchMail.click();
		wait.until(ExpectedConditions.elementToBeClickable(haspopup));
		Thread.sleep(1000);
		haspopup.click();
	}

	public void clickOpenMailBox() throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", lnkopenanothermail);

	}

	public void enterEmail(String email) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(fieldTextbox));
		fieldTextbox.sendKeys(email);
		wait.until(ExpectedConditions.elementToBeClickable(buttonsearchcontacts));
		try {
			buttonsearchcontacts.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonOpen));
			Thread.sleep(1000);
			buttonOpen.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(searchSuggestion));
			searchSuggestion.click();
			wait.until(ExpectedConditions.elementToBeClickable(buttonOpen));
			Thread.sleep(1000);
			buttonOpen.click();
		}
	}

	public void handleNewInbox() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(10000);

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));

		for (WebElement e : list) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
			wait.until(ExpectedConditions.elementToBeClickable(emailid));
			log.info(emailid.getText());
			if (emailid.getText().equalsIgnoreCase(BrokerPaymentforUnmatchedCarrierTest.al.get(1) + ";")) {
				wait.until(ExpectedConditions.elementToBeClickable(linkVerify));
				linkVerify.click();
				break;
			}
		}
	}

	public void outlookSearchInbox(String EmailAddress, String hour, String minutes) throws InterruptedException {
		WebElement searchInput;
		WebElement searchButton;
		WebElement infoMessage;
		WebElement emailid;
		Integer retryCount = 0;
		Integer maxRetryCount = 300;

		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);

		WebElement searchField = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Search mail and people']")));
		wait.until(ExpectedConditions.elementToBeClickable(searchField));
		Thread.sleep(2000);
		searchField.click();

		searchInput = driver.findElement(By.xpath("//input[@aria-label='Search. Press Enter to Start Searching.']"));
		searchButton = driver.findElement(By.xpath("//button[@aria-label='Start search']"));

		wait.until(ExpectedConditions.elementToBeClickable(searchInput));
		searchInput.sendKeys(EmailAddress);

		wait.until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();

		infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));
		log.info("Info message text: " + infoMessage.getText());

		while (infoMessage.isDisplayed() && (retryCount < maxRetryCount)) {
			searchButton.click();
			Thread.sleep(5000);
			infoMessage = driver.findElement(By.id("conv.mail_list_view_info_message"));

			if (!infoMessage.isDisplayed()) {
				if (checkEmailTimeStamp(hour, minutes))
					break;
			}

			retryCount++;
		}

		emailid = driver.findElement(By.xpath("//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]"));

		log.info("Email ID text: " + emailid.getText());
	}

	private Boolean checkEmailTimeStamp(String hour, String minutes) {
		WebElement emailTimeStamp;
		String emailTime = "";
		Integer approximateHour = 0;
		Integer approximateMinutes = 0;
		Integer actualHour = 0;
		Integer actualMinutes = 0;
		String[] timeParser;

		emailTimeStamp = driver.findElement(By.id("ItemHeader.DateReceivedLabel"));
		emailTime = emailTimeStamp.getText();
		emailTime = emailTime.substring(emailTime.length() - 8, emailTime.length());
		log.info("\n\n\nEmail time: " + emailTime);
		timeParser = emailTime.split(":");
		timeParser[0] = timeParser[0].trim();
		timeParser[1] = timeParser[1].trim();
		timeParser[1] = timeParser[1].substring(0, 2);

		approximateHour = Integer.parseInt(hour);
		approximateMinutes = Integer.parseInt(minutes);

		if (approximateHour > 12)
			approximateHour -= 12;

		log.info("Approx Hours: " + approximateHour);
		log.info("Approx Minutes: " + approximateMinutes);

		actualHour = Integer.parseInt(timeParser[0]);
		actualMinutes = Integer.parseInt(timeParser[1]);

		if (actualHour > 12)
			actualHour -= 12;

		log.info("Actual Hours: " + actualHour);
		log.info("Actual Minutes: " + actualMinutes);

		if ((approximateHour == actualHour) && (approximateMinutes <= actualMinutes)) {
			return true;
		} else {
			return false;
		}
	}

	public void verifyConfirmationMessage() throws InterruptedException {
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(emailverifymessage));
		Assert.assertTrue(emailverifymessage.isDisplayed());
		driver.close();
		driver.switchTo().window(tabs.get(1));

	}

}