package pages.loadpay.carrier;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CarrierCompleteAccountModule extends TestBase {
	WebDriverWait wait = null;
	JavascriptExecutor js;
	ArrayList<String> tabs;
	String amount;
	String pennyamount;
	String pennyamountt;
	int j = 0;
	String pennyamt;
	String pennyamounts;
	String pennyamoun;

	@FindBy(xpath = "//a[text()='Account']")
	public WebElement lnk_account;

	@FindBy(xpath = "//a[contains(text(),'Company')]")
	public WebElement lnk_Company;

	@FindBy(xpath = "//input[@name='DOT']")
	public WebElement dotnum;

	@FindBy(xpath = "//input[@name='EIN']")
	public WebElement einnum;

	@FindBy(xpath = ".//*[@id='formCompany']/div/div[15]/input")
	public WebElement companyupdate;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	public WebElement lnk_Contact;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/a")
	public WebElement clcik_AddNewContact;

	@FindBy(xpath = "//input[@id='ContactFirstName']")
	public WebElement ContactFirstName;

	@FindBy(xpath = ".//*[@id='PMNTerm']")
	WebElement click_PaymentTerm;

	@FindBy(xpath = "//*[@id='Contact_LastName']")
	WebElement Contact_LastName;

	public @FindBy(xpath = "//*[@id='Contact_Email']") WebElement Contact_Email;
	@FindBy(xpath = "//*[@id='Contact_Phone']")
	WebElement Contact_Phone;
	@FindBy(xpath = "//*[@id='Contact_Ext']")
	WebElement Contact_Ext;
	@FindBy(xpath = "//*[@id='Contact_Mobile']")
	WebElement Contact_Mobile;
	@FindBy(xpath = "//*[@id='Contact_Fax']")
	WebElement Contact_Fax;
	public @FindBy(xpath = "//*[@id='formAddContacts']/input") WebElement click_Save;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[2]/ng-form/div[7]/input") WebElement click_ContactUpdate;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[2]/div[2]/div[2]/div[3]/ng-form/div[7]/input[2]") WebElement click_DeleteContact;

	@FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[2]/div/nav/div[2]/ul/li[5]/a")
	WebElement Click_Account1;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement Click_BrokerpaymeNowUpdate;

	public @FindBy(xpath = ".//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[5]") WebElement lnk_Notifications;

	@FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	WebElement check_NotifyByWithdrawal;

	@FindBy(xpath = "//*[@id='formNotifications']/div[3]/input")
	WebElement click_updatebtn;

	@FindBy(xpath = ".//*[@id='NotifyByWithdrawal']")
	WebElement uncheck_NotifyByWithdrawal;

	public @FindBy(xpath = ".//*[@id='NotifyByNewPayment']") WebElement uncheck_NotifyByNewPayment;

	public @FindBy(xpath = ".//*[@id='NotifyByPayMeNow']") WebElement uncheck_NotifyByPayMeNow;

	public @FindBy(xpath = ".//*[@id='NotifyByDeposit']") WebElement uncheck_NotifyByDeposit;

	public @FindBy(xpath = ".//*[@id='formNotifications']/div[5]/input") WebElement uncheck_CarrierNotificationUpdate;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement text_saved;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[4]")
	WebElement lnk_Credit;

	@FindBy(xpath = "//*[@id='ExtendedCredit']")
	WebElement text_ExtendedCredit;

	@FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	WebElement click_Update;

	@FindBy(xpath = "//a[contains(text(),'My Credit')]")
	WebElement lnk_MyCredit;

	@FindBy(xpath = "//*[@id='angularScope']/div[2]/div/div[3]/div/div/div[1]/div/div[3]/div/div[1]/a[7]")
	WebElement link_paymeNow;

	@FindBy(xpath = "//*[@id='PMNEnrolled']")
	WebElement check_PMNEnrolled;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement click_brokpaymeupdate;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]")
	WebElement click_adminpaymenow;

	@FindBy(xpath = "//*[@id='PMNEnrolled']")
	WebElement ucheck_adminPMNEnrolled;

	@FindBy(xpath = "//*[@id='formPMN']/div/div[3]/input[2]")
	WebElement click_adminupdate;

	public CarrierCompleteAccountModule() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
	}

	public void clickAccountlink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(lnk_account));
		js.executeScript("arguments[0].click();", lnk_account);
	}

	public void clickCompanylink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(lnk_Company));
		js.executeScript("arguments[0].click();", lnk_Company);
	}

	public void enterDotnumber(String Dot) {
		dotnum.clear();
		dotnum.sendKeys(Dot);
	}

	public void enterEinnumber(String EIN) {
		einnum.sendKeys(EIN);
	}

	public void clickCompanyUpdate() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(companyupdate));
		js.executeScript("arguments[0].click();", companyupdate);
	}

	public void clickContactlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(lnk_Contact));
		js.executeScript("arguments[0].click();", lnk_Contact);
	}

	public void clickAddNewContact() throws InterruptedException {

		// wait.until(ExpectedConditions.elementToBeClickable(clcik_AddNewContact));
		js.executeScript("arguments[0].click();", clcik_AddNewContact);
	}

	public void enterContactFirstName(String ContactFN) {
		wait.until(ExpectedConditions.elementToBeClickable(ContactFirstName));
		ContactFirstName.sendKeys(ContactFN);
	}

	public void enterContactlastName(String ContactLN) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_LastName));
		Contact_LastName.sendKeys(ContactLN);
	}

	public void enterContactemail(String contactemail) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Email));
		Contact_Email.sendKeys(contactemail);
	}

	public void enterContactphonenum(String ContactPN) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Phone));
		Contact_Phone.sendKeys(ContactPN);
	}

	public void enterContactExtension(String Contactextension) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Ext));
		Contact_Ext.sendKeys(Contactextension);
	}

	public void enterContactMobileNumber(String ContactMobileNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Mobile));
		Contact_Mobile.sendKeys(ContactMobileNumber);
	}

	public void enterContactFax(String ContactFax) {
		wait.until(ExpectedConditions.elementToBeClickable(Contact_Fax));
		Contact_Fax.sendKeys(ContactFax);
	}

	public void clicksavelink() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_Save));
		js.executeScript("arguments[0].click();", click_Save);
	}

	public void clickContactUpdatelink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_ContactUpdate));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
		js.executeScript("arguments[0].click();", click_ContactUpdate);

	}

	public void clickdeletecontactlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_DeleteContact));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
		js.executeScript("arguments[0].click();", click_DeleteContact);

		driver.switchTo().alert().accept();

	}

	public void clickAccount1link() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(Click_Account1));
		js.executeScript("arguments[0].click();", Click_Account1);
	}

	public void clickNotifyByNewPaymentlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByNewPayment));
		js.executeScript("arguments[0].click();", uncheck_NotifyByNewPayment);
	}

	public void clickNotifyByPayMeNowlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByPayMeNow));
		js.executeScript("arguments[0].click();", uncheck_NotifyByPayMeNow);
	}

	public void clickNotifyByDepositlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByDeposit));
		js.executeScript("arguments[0].click();", uncheck_NotifyByDeposit);
	}

	public void clickCarrierNotificationUpdatelink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(uncheck_CarrierNotificationUpdate));
		js.executeScript("arguments[0].click();", uncheck_CarrierNotificationUpdate);
	}

	public void clickNotificationlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(lnk_Notifications));
		js.executeScript("arguments[0].click();", lnk_Notifications);
	}

	public void checkNotifyByWithdrwallink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(check_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", check_NotifyByWithdrawal);
	}

	public void clickUpdatebuttonlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtn));
		js.executeScript("arguments[0].click();", click_updatebtn);
	}

	public void uncheckNotifyByWithdrawallink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(uncheck_NotifyByWithdrawal));
		js.executeScript("arguments[0].click();", uncheck_NotifyByWithdrawal);
		text_saved.getText();

	}

	public void clickcreditlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(lnk_Credit));
		js.executeScript("arguments[0].click();", lnk_Credit);
	}

	public void enterExtendedCredit(String ExtendedCredit) {
		wait.until(ExpectedConditions.elementToBeClickable(text_ExtendedCredit));
		text_ExtendedCredit.clear();
		text_ExtendedCredit.sendKeys(ExtendedCredit);
	}

	public void clickupdatelink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_Update));
		js.executeScript("arguments[0].click();", click_Update);
	}

	public void clickPaymentTermlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_PaymentTerm));
		js.executeScript("arguments[0].click();", click_PaymentTerm);

		Select pay = new Select(click_PaymentTerm);

		pay.selectByIndex(11);
	}

	public void clickBrokerpaymeNowUpdatelink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(Click_BrokerpaymeNowUpdate));
		js.executeScript("arguments[0].click();", Click_BrokerpaymeNowUpdate);
	}

	public void clickmycreditlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(lnk_MyCredit));
		js.executeScript("arguments[0].click();", lnk_MyCredit);
	}

	public void clickpaymenowlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(link_paymeNow));
		js.executeScript("arguments[0].click();", link_paymeNow);

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
	}

	public void checkpaymenowenroll() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(check_PMNEnrolled));
		js.executeScript("arguments[0].click();", check_PMNEnrolled);
	}

	public void clickbrokerpaymeupdatelink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_brokpaymeupdate));
		js.executeScript("arguments[0].click();", click_brokpaymeupdate);
	}

	public void clickadminpaymenowlink() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_adminpaymenow));
		js.executeScript("arguments[0].click();", click_adminpaymenow);
	}

	public void uncheckpaymenowenroll() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(ucheck_adminPMNEnrolled));
		js.executeScript("arguments[0].click();", ucheck_adminPMNEnrolled);
	}

	public void clickadminupdate() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(click_adminupdate));
		js.executeScript("arguments[0].click();", click_adminupdate);
	}

}