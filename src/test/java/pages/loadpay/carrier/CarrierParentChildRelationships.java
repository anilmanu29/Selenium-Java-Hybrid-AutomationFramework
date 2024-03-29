package pages.loadpay.carrier;

import java.awt.AWTException;
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

import base.TestBase;
import outlook.OutlookFunctions;
import testcases.loadpay.carrier.CarrierParentChildRelationshipsTest;

public class CarrierParentChildRelationships extends TestBase {

	WebDriverWait wait = null;
	// Actions act = null;
	// JavascriptExecutor js;
	OutlookFunctions brokeroutlook;
	OutlookFunctions outlooklog;
	List<String> dataElements = new ArrayList<String>();
	String code;
	// Actions actions = new Actions(driver);
	List<String> firstRowData = null;
	public static String pwd;
	int j;

	@FindBy(xpath = "//a[text()='Account']")
	private WebElement linkaccount;

	@FindBy(xpath = "//a[contains(text(),'Email / Login / Users')]")
	public WebElement emaillink;

	@FindBy(xpath = "//input[@value='Add User']")
	public WebElement adduserbtn;

	@FindBy(id = "FirstName")
	public WebElement firstnamefield;

	@FindBy(id = "LastName")
	public WebElement lastnamefield;

	@FindBy(id = "NewCarrierEmail")
	public WebElement newemailidfield;

	@FindBy(xpath = "//label[@for='AccountDenied']")
	public WebElement paymentaccesslabel;

	@FindBy(xpath = "//*[@id='formAddEmail']/div[3]/div/label/i")
	public WebElement paymentaccesbutton;

	@FindBy(xpath = "//input[@value='Save'][@type='submit']")
	public WebElement savebtn;

	@FindBy(xpath = "//div[@role='alert']")
	public WebElement alertmessage;

	@FindBy(xpath = "//div[@id='AddNewEmail']//child::input[@value='Cancel']")
	public WebElement cancelbutton;

	@FindBy(id = "searchKeyword")
	public WebElement searchInputField;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]")
	private WebElement expandCollapseFirstRow;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[6]/span")
	public WebElement invoice;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[4]//child::div[1]/span")
	public WebElement companyname;

	@FindBy(xpath = "//*[@aria-expanded='true']//child::div[5]//child::div[1]/span")
	public WebElement loadid;

	@FindBy(xpath = "//a[text()='Verify Email Address']")
	public WebElement verifyemaillink;

	@FindBy(xpath = "//*[@id='ItemHeader.ToContainer']/div/div/div/span/span/div/span[2]")
	WebElement emailid;

	@FindBy(xpath = "//*[@id='Item.MessageUniqueBody']/div/div/div[3]/table/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/span/p[3]")
	public WebElement verificationcode;

	@FindBy(id = "txtVC1")
	public WebElement textfield1;

	@FindBy(id = "txtVC2")
	public WebElement textfield2;

	@FindBy(id = "txtVC3")
	public WebElement textfield3;

	@FindBy(id = "txtVC4")
	public WebElement textfield4;

	@FindBy(id = "txtVC5")
	public WebElement textfield5;

	@FindBy(id = "txtVC6")
	public WebElement textfield6;

	@FindBy(xpath = "//input[@value='Verify'][@type='button']")
	private WebElement verifybutton;

	@FindBy(xpath = "//table/tbody/tr/td//child::a[text()='Reset Your Password']")
	public WebElement buttonresetpassword;

	@FindBy(id = "User_Password")
	public WebElement newpasswordfield;

	@FindBy(id = "User_ConfirmPassword")
	public WebElement confirmpassword;

	@FindBy(xpath = "//input[@value='Submit']")
	public WebElement submitbutton;

	@FindBy(xpath = "//a[@href='/Account/LogOff']")
	private WebElement logoffbutton;

	@FindBy(xpath = "//div[@class='ellipsis ng-binding']")
	private List<WebElement> accountemailids;

	@FindBy(xpath = "//*[@title='Force Password Reset']")
	private List<WebElement> forceemailicons;

	@FindBy(xpath = "//i[text()='mode_edit']")
	private List<WebElement> editicon;

	@FindBy(xpath = ".//*[contains(@title,'Delete')]")
	private List<WebElement> deleteicon;

	@FindBy(xpath = "//button[text()='Confirm']")
	private WebElement buttonconfirm;

	public CarrierParentChildRelationships() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		// act = new Actions(driver);
		// js = (JavascriptExecutor) driver;
		brokeroutlook = new OutlookFunctions();
		outlooklog = new OutlookFunctions();
	}

	public void clickAccountLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(linkaccount));
		// js.executeScript("arguments[0].click();", linkaccount);
		linkaccount.click();
	}

	public void clickEmailLink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emaillink));
		// js.executeScript("arguments[0].click();", emaillink);
		emaillink.click();
	}

	public void clickAddUserButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(adduserbtn));
		// js.executeScript("arguments[0].click();", adduserbtn);
		adduserbtn.click();
	}

	public void enterFirstName(String fn) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(firstnamefield));
		firstnamefield.clear();
		firstnamefield.sendKeys(fn);

	}

	public void enterLastName(String ln) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lastnamefield));
		lastnamefield.click();
		lastnamefield.clear();
		lastnamefield.sendKeys(ln);

	}

	public String enterNewEmailID(String nemail) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(newemailidfield));
		newemailidfield.click();
		newemailidfield.clear();
		newemailidfield.sendKeys(nemail);
		wait.until(ExpectedConditions.elementToBeClickable(savebtn));
		Thread.sleep(2000);
		return nemail;

	}

	public void enablePaymentAccess() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymentaccesslabel));
		wait.until(ExpectedConditions.elementToBeClickable(paymentaccesbutton));
		if (paymentaccesslabel.getText().contains("Disabled")) {
			// js.executeScript("arguments[0].click();", paymentaccesbutton);
			paymentaccesbutton.click();
		}

	}

	public void clickSaveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(savebtn));
		// js.executeScript("arguments[0].click();", savebtn);
		savebtn.click();
	}

	public String getAlertMessage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(alertmessage));
		return alertmessage.getText();

	}

	public void clickCancelButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(cancelbutton));
		// js.executeScript("arguments[0].click();", cancelbutton);
		cancelbutton.click();
	}

	public void enterSearchText(String searchText) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchInputField));
		searchInputField.click();
		searchInputField.clear();
		searchInputField.sendKeys(searchText);
	}

	public void outlookLogin(String un, String pwd) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(1000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		try {
			outlooklog.outlookLogin(un, pwd);
			brokeroutlook.clickPopUp();
			brokeroutlook.clickOpenMailBox();
			brokeroutlook.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(1000);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(2));
		Thread.sleep(1000);

	}

	public List<String> getFirstRowData() throws InterruptedException {
		List<String> dataElements = new ArrayList<String>();
		List<WebElement> webElements = driver.findElements(By.xpath("//table/tbody/tr/td//child::p[3]//child::span"));

		for (WebElement element : webElements) {

			dataElements.add(element.getText());
		}

		return dataElements;
	}

	public void getVerificationCodeData() throws InterruptedException {

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement element : list) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			log.info(CarrierParentChildRelationshipsTest.nemail);
			log.info(emailid.getText());
			wait.until(ExpectedConditions.elementToBeClickable(emailid));
			if (emailid.getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail + ";")) {
				firstRowData = getFirstRowData();
				break;
			}
		}
		driver.close();
		Thread.sleep(1000);
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));
		Thread.sleep(1000);
		driver.close();
		Thread.sleep(1000);
		ArrayList<String> tabb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabb.get(0));
		Thread.sleep(1000);

	}

	public void enterVerificationCode() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifyemaillink));
		// js.executeScript("arguments[0].click();", verifyemaillink);
		verifyemaillink.click();
		wait.until(ExpectedConditions.elementToBeClickable(textfield1));
		textfield1.click();
		textfield1.sendKeys(firstRowData.get(0));
		textfield2.sendKeys(firstRowData.get(1));
		textfield3.sendKeys(firstRowData.get(2));
		textfield4.sendKeys(firstRowData.get(3));
		textfield5.sendKeys(firstRowData.get(4));
		textfield6.sendKeys(firstRowData.get(5));
	}

	public void clickVerifyButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifybutton));
		// js.executeScript("arguments[0].click();", verifybutton);
		verifybutton.click();
		wait.until(ExpectedConditions.elementToBeClickable(logoffbutton));
		// js.executeScript("arguments[0].click();", logoffbutton);
		logoffbutton.click();
	}

	public void carrierLogOut() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(logoffbutton));
		// js.executeScript("arguments[0].click();", logoffbutton);
		logoffbutton.click();
	}

	public void clickResetPasswordButton(String newwd, String confpwd) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(emailid));
		if (emailid.getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail + ";")) {

			List<WebElement> childrenElements = emailid.findElements(By.xpath(".//*"));

			for (WebElement child : childrenElements) {

				if (child.getText().contains("Reset")) {
					System.out.println(child.getText());
				}
			}

			// wait.until(ExpectedConditions.elementToBeClickable(buttonresetpassword));
			// js.executeScript("arguments[0].click();", buttonresetpassword);
			// buttonresetpassword.click();
		}

		ArrayList<String> newtab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtab.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(newpasswordfield));
		newpasswordfield.sendKeys(newwd);
		confirmpassword.sendKeys(confpwd);
		wait.until(ExpectedConditions.elementToBeClickable(submitbutton));
		submitbutton.click();
	}

	public String resetPassword(String newwd, String confpwd) throws InterruptedException {

		ArrayList<String> newtabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtabs.get(2));

		List<WebElement> list = driver
				.findElements(By.xpath("//*[@class='ms-font-l lvHighlightSubjectClass lvHighlightAllClass']"));
		for (WebElement element : list) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			log.info(CarrierParentChildRelationshipsTest.nemail);
			log.info(emailid.getText());
			wait.until(ExpectedConditions.elementToBeClickable(emailid));
			if (emailid.getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail + ";")) {
				wait.until(ExpectedConditions.elementToBeClickable(buttonresetpassword));
				// js.executeScript("arguments[0].click();", buttonresetpassword);
				buttonresetpassword.click();
				break;
			}
		}

		ArrayList<String> newtab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newtab.get(3));
		wait.until(ExpectedConditions.elementToBeClickable(newpasswordfield));
		newpasswordfield.sendKeys(newwd);
		confirmpassword.sendKeys(confpwd);
		wait.until(ExpectedConditions.elementToBeClickable(submitbutton));
		// js.executeScript("arguments[0].click();", submitbutton);
		submitbutton.click();
		return confpwd;

	}

	public void forcePasswordReset() throws InterruptedException {

		log.info(CarrierParentChildRelationshipsTest.nemail);
		int count = accountemailids.size();
		for (int i = 0; i < count; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(accountemailids.get(i)));
			if (accountemailids.get(i).getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail)) {
				log.info(CarrierParentChildRelationshipsTest.nemail);
				log.info(i);

				j = i;

				WebElement r = forceemailicons.get(j);
				// js.executeScript("arguments[0].click();", r);
				r.click();
				break;
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(buttonconfirm));
		// js.executeScript("arguments[0].click();", buttonconfirm);
		buttonconfirm.click();
	}

	public void editAccount() throws InterruptedException {

		int count = accountemailids.size();
		for (int i = 0; i < count; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(accountemailids.get(i)));
			if (accountemailids.get(i).getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail)) {
				log.info(CarrierParentChildRelationshipsTest.nemail);
				log.info(i);

				j = i;

				WebElement edit = editicon.get(j);
				// js.executeScript("arguments[0].click();", edit);
				edit.click();
				break;
			}
		}

	}

	public void deleteChildAccount() throws InterruptedException {

		int count = accountemailids.size();
		for (int i = 0; i < count; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(accountemailids.get(i)));
			if (accountemailids.get(i).getText().equalsIgnoreCase(CarrierParentChildRelationshipsTest.nemail)) {
				log.info(CarrierParentChildRelationshipsTest.nemail);
				log.info(i);
				j = i;

				WebElement delete = deleteicon.get(j);
				// js.executeScript("arguments[0].click();", delete);
				delete.click();
				break;
			}
		}
		wait.until(ExpectedConditions.elementToBeClickable(buttonconfirm));
		// js.executeScript("arguments[0].click();", buttonconfirm);
		buttonconfirm.click();
	}

}
