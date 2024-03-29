package pages.loadpay.admin;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class AdminCustomersSideMenuSearch extends TestBase {
	Select s;

	@FindBy(xpath = ".//*[@id='UserName']")
	WebElement UserName;

	@FindBy(xpath = ".//*[@id='Password']")
	WebElement Password;

	@FindBy(xpath = "html/body/div[1]/div/div/div[2]/form/div[4]/input")
	WebElement loginBtn;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[8]/a")
	WebElement logOut;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[1]/div/nav/div[2]/ul/li[3]/a")
	public WebElement CustomerTab;

	@FindBy(xpath = ".//*[@id='PMNEnrolled']")
	public WebElement uncheckpaymennow;

	@FindBy(xpath = (".//*[@id='updatebtnPayByInvoice']"))
	WebElement click_updatebtnPayByInvoice;

	@FindBy(xpath = ("//a[contains(text(),'Credit')]"))
	WebElement CreditTab;

	@FindBy(xpath = (".//*[@id='angularScope']/div[3]/div/div/div[2]/button"))
	WebElement CloseButon;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[6]/div[2]/div/div/div[3]/div[2]"))
	WebElement verifysystemnote;

	@FindBy(xpath = (".//*[@id='greaterThan45daysId']"))
	WebElement selectgreaterThan45daysId;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[1]/li[1]/input"))
	public WebElement active;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[1]/li[2]/input"))
	public WebElement ToBeReviewed;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[1]/li[3]/input"))
	public WebElement Pending;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[1]/li[4]/input"))
	public WebElement Inactive;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[1]/li[5]/input"))
	public WebElement Denied;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[2]/li[1]/input"))
	public WebElement Broker;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[2]/ul[2]/li[2]/input"))
	public WebElement Carrier;

	@FindBy(xpath = (".//*[@id='btnEditPaymentTermsGreaterThan45Days']"))
	WebElement Paymnttermgraterthan45days;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[14]/div/div/table/tbody/tr[1]/td[3]/button"))
	WebElement forwardfile;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[12]/div/div/table/tbody/tr[1]/td[3]/button"))
	WebElement carrierforwardfile;

	@FindBy(xpath = (".//*[@id='btnUpdatePaymentTermsGreaterThan45Days']"))
	WebElement Clickpaymentterm45Submit;

	@FindBy(xpath = (".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[6]"))
	WebElement clickNotes;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[14]/div/div/table/tbody/tr[1]/td[3]/a")
	public WebElement paymnt_Historydownload;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[2]/div[12]/div/div/table/tbody/tr[1]/td[3]/a")
	public WebElement paymnt_carrierHistorydownload;

	@FindBy(xpath = ".//*[@id='formPMN']/div/div[3]/input[2]")
	public WebElement Update_paymenow;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[13]")
	WebElement PaymentHistory;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]")
	WebElement PaymentTerms;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[5]")
	WebElement PayMeNowTm;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[8]")
	WebElement carrierPaymentHistory;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[2]")
	WebElement AdminBanking;

	@FindBy(xpath = "//*[@id='angularScope']/div[1]/div/div[2]/div/div/div[2]/div/div[2]/table/tbody/tr/td[1]/a")
	WebElement CustomerId;

	@FindBy(xpath = ".//*[@id='PayByInvoieStatus']")
	public WebElement select_PayByInvoieStatus;

	@FindBy(xpath = ".//*[@id='searchKeyword']")
	public WebElement search;

	@FindBy(xpath = ".//*[@id='angularScope']/div[3]/div/div/div[2]/button[2]")
	WebElement click_AdminResetpwdConfirm;

	@FindBy(xpath = ".//*[@id='formCustomerNote']/div[2]/div/div/button")
	WebElement closenotesbutton;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement ClickonSearchButton;

	public @FindBy(xpath = ".//*[@id='emailTo']") WebElement emailTo;

	public @FindBy(xpath = ".//*[@id='editbtnPayByInvoice']") WebElement editbtnPayByInvoice;

	public @FindBy(xpath = ".//*[contains(@title, 'send email')]") WebElement clickSendPaymentHistoryEmail;

	public @FindBy(xpath = ".//*[contains(@title, 'cancel')]") WebElement clickCancelPaymentHistorySendEmail;

	@FindBy(xpath = "//a[contains(@class,'ng-binding')]")
	public WebElement DoubleClickID;

	@FindBy(xpath = ".//*[@id='CustomerStatusId']")
	WebElement CustomersatatusIdDropDown;

	@FindBy(xpath = ".//*[@id='angularScope']/div[1]/div/div[2]/div/div/div/div[1]/div[3]/div[1]/a[7]")
	WebElement editloginuser;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[6]/input")
	WebElement CancelLockout;

	@FindBy(xpath = ".//*[@id='TabList']/div/table/tbody/tr/td[7]/div/i")
	WebElement clickAdmin_ResetPassword;

	@FindBy(xpath = ".//*[@id='formCompany']/div[2]/input")
	WebElement updateButton;

	@FindBy(xpath = "//*[@id='ExtendedCredit']")
	WebElement ExtendedCredit;

	@FindBy(xpath = "//*[@id='formCredit']/div[3]/input")
	WebElement CreditSubmit;

	public AdminCustomersSideMenuSearch() throws IOException {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	public void adminUserPass(String Username, String pass) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(UserName));
		UserName.sendKeys(Username);
		wait.until(ExpectedConditions.elementToBeClickable(Password));
		Password.sendKeys(pass);
	}

	public void adminLogin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		loginBtn.click();
	}

	public void AdminLogOut() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(logOut));
		logOut.click();
	}

	public void Banking_editbtnPayByInvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(editbtnPayByInvoice));
		editbtnPayByInvoice.click();
	}

	public void Link_PayMeNowTm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PayMeNowTm));
		PayMeNowTm.click();
	}

	public void Click_paymentterm45Submit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Clickpaymentterm45Submit));
		Clickpaymentterm45Submit.click();

	}

	public void Clickverifysystemnote() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(verifysystemnote));
		verifysystemnote.click();

	}

	public void Clickclosenotesbutton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(closenotesbutton));
		Thread.sleep(2000);
		closenotesbutton.click();

	}

	public void clickupdatebtnPayByInvoice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_updatebtnPayByInvoice));
		click_updatebtnPayByInvoice.click();
	}

	public void selectPayByInvoieStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_PayByInvoieStatus));
		Thread.sleep(1000);
		select_PayByInvoieStatus.click();
		s = new Select((select_PayByInvoieStatus));
		/* s.deselectByVisibleText("Active"); */
		s.selectByVisibleText("Enabled");
	}

	public void DisablePayByInvoieStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(select_PayByInvoieStatus));
		Thread.sleep(1000);
		select_PayByInvoieStatus.click();
		s = new Select((select_PayByInvoieStatus));
		/* s.deselectByVisibleText("Active"); */
		s.selectByVisibleText("Disabled");
	}

	public void ClickOnCreditTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CreditTab));
		CreditTab.click();
	}

	public void click_AdminBanking() throws InterruptedException {

		AdminBanking.click();
	}

	public void ClickOnCreditSubmitButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CreditSubmit));
		CreditSubmit.click();
	}

	public void selectGreaterThan45daysId_Enabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectgreaterThan45daysId);
		pay.selectByVisibleText("Enabled");
	}

	public void selectGreaterThan45daysId_Disabled() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
		selectgreaterThan45daysId.click();
		Select pay = new Select(selectgreaterThan45daysId);
		pay.selectByVisibleText("Disabled");
	}

	// public void selectGreaterThan45daysId(Boolean enabled) throws
	// InterruptedException {
	// wait.until(ExpectedConditions.elementToBeClickable(selectgreaterThan45daysId));
	// selectgreaterThan45daysId.click();
	// Select pay = new Select(selectgreaterThan45daysId);
	//
	// if (enabled) {
	// pay.selectByVisibleText("Enabled");
	// } else {
	// pay.selectByVisibleText("Disabled");
	// }
	// }

	public void EnterExtendedCredit(String CreditAmount) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ExtendedCredit));
		ExtendedCredit.click();
		ExtendedCredit.clear();
		ExtendedCredit.sendKeys(CreditAmount);
	}

	public void ClickOnCustomersTab() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomerTab));
		CustomerTab.click();
		Thread.sleep(2000);
	}

	public void ClickOnSearchBoxclear() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(search));
		search.click();
		search.clear();

	}

	public void ClickOnSearchButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ClickonSearchButton));
		ClickonSearchButton.click();
		Thread.sleep(1000);
	}

	public void ClickactiveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(active));
		active.click();
		Thread.sleep(1000);
	}

	public void ClickToBeReviewedButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(ToBeReviewed));
		ToBeReviewed.click();
		Thread.sleep(1000);
	}

	public void ClickPendingButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Pending));
		Pending.click();
		Thread.sleep(1000);
	}

	public void ClickInactiveButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Inactive));
		Inactive.click();
		Thread.sleep(1000);
	}

	public void ClickDeniedButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Denied));
		Denied.click();
		Thread.sleep(1000);
	}

	public void ClickBrokerButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Broker));
		Broker.click();
		Thread.sleep(1000);
	}

	public void ClickCarrierButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Carrier));
		Carrier.click();
		Thread.sleep(1000);
	}

	public void ClickSendEmailToVerify() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickSendPaymentHistoryEmail));
		clickSendPaymentHistoryEmail.click();
	}

	public void ClickUpdatepaymenow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Update_paymenow));
		Update_paymenow.click();
		Thread.sleep(2000);
	}

	public void ClickCloseButon() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CloseButon));
		CloseButon.click();
		Thread.sleep(2000);
	}

	public void ClickPaymentTerms() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PaymentTerms));
		Thread.sleep(2000);
		PaymentTerms.click();
	}

	public void Click_Notes() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickNotes));
		clickNotes.click();
	}

	public void ClickCancelSendEmailToVerify() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickCancelPaymentHistorySendEmail));
		clickCancelPaymentHistorySendEmail.click();
	}

	public void DoubleClickID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(DoubleClickID));
		DoubleClickID.click();
	}

	public void clickCustomerId() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomerId));
		CustomerId.click();
	}

	public void clickeditloginuser() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(editloginuser));
		editloginuser.click();
	}

	public void click_AdminResetPassword() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(clickAdmin_ResetPassword));
		clickAdmin_ResetPassword.click();
	}

	public void clickAdmin_ResetpwdConfirm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(click_AdminResetpwdConfirm));
		click_AdminResetpwdConfirm.click();
	}

	public void clickEditPaymnttermgraterthan45days() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Paymnttermgraterthan45days));
		Paymnttermgraterthan45days.click();
	}

	public void clickCancelLockout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CancelLockout));
		CancelLockout.click();
	}

	public void clickPaymentHistory() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(PaymentHistory));
		PaymentHistory.click();
	}

	public void clickcarrierPaymentHistory() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierPaymentHistory));
		carrierPaymentHistory.click();
	}

	public void clickuncheckpaymennow() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(uncheckpaymennow));
		uncheckpaymennow.click();
	}

	public void clickpaymnt_Historydownload() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymnt_Historydownload));
		paymnt_Historydownload.click();
		Thread.sleep(5000);
	}

	public void clickpaymntcarrierHistorydownload() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(paymnt_carrierHistorydownload));
		paymnt_carrierHistorydownload.click();
		Thread.sleep(5000);
	}

	public void clicforwardfile() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(forwardfile));
		forwardfile.click();
		Thread.sleep(6000);

	}

	public void cliccarrierforwardfile() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(carrierforwardfile));
		carrierforwardfile.click();
		Thread.sleep(6000);

	}

	public void EnterEmailTo(String keyword) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailTo));
		emailTo.click();
		emailTo.clear();
		emailTo.sendKeys(keyword);
	}

	public void StatusIDDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(CustomersatatusIdDropDown));
		CustomersatatusIdDropDown.click();
		s = new Select(CustomersatatusIdDropDown);
		s.selectByVisibleText("Active");
	}

	public void UpdateButton() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
	}

	/**
	 * @return the userName
	 */
	public WebElement getUserName() {
		return UserName;
	}

	/**
	 * @return the password
	 */
	public WebElement getPassword() {
		return Password;
	}

	/**
	 * @return the loginBtn
	 */
	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * @return the logOut
	 */
	public WebElement getLogOut() {
		return logOut;
	}

	/**
	 * @return the customerTab
	 */
	public WebElement getCustomerTab() {
		return CustomerTab;
	}

	/**
	 * @return the customerId
	 */
	public WebElement getCustomerId() {
		return CustomerId;
	}

	/**
	 * @return the search
	 */
	public WebElement getSearch() {
		return search;
	}

	/**
	 * @return the click_AdminResetpwdConfirm
	 */
	public WebElement getClick_AdminResetpwdConfirm() {
		return click_AdminResetpwdConfirm;
	}

	/**
	 * @return the clickonSearchButton
	 */
	public WebElement getClickonSearchButton() {
		return ClickonSearchButton;
	}

	/**
	 * @return the doubleClickID
	 */
	public WebElement getDoubleClickID() {
		return DoubleClickID;
	}

	/**
	 * @return the customersatatusIdDropDown
	 */
	public WebElement getCustomersatatusIdDropDown() {
		return CustomersatatusIdDropDown;
	}

	/**
	 * @return the editloginuser
	 */
	public WebElement getEditloginuser() {
		return editloginuser;
	}

	/**
	 * @return the cancelLockout
	 */
	public WebElement getCancelLockout() {
		return CancelLockout;
	}

	/**
	 * @return the clickAdmin_ResetPassword
	 */
	public WebElement getClickAdmin_ResetPassword() {
		return clickAdmin_ResetPassword;
	}

	/**
	 * @return the updateButton
	 */
	public WebElement getUpdateButton() {
		return updateButton;
	}

	public WebElement getPaymentTerms() {
		// TODO Auto-generated method stub
		return PaymentTerms;
	}

	public WebElement getPaymnttermgraterthan45days() {
		// TODO Auto-generated method stub
		return Paymnttermgraterthan45days;
	}
}