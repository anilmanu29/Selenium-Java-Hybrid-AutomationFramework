package testcases.loadpay.unmatched;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import outlook.OutlookFunctions;
import pages.loadpay.unmatched.UnmatchedNewPaymentCarrierOutlook;
import util.TestUtil;

public class UnmatchedNewPaymentCarrierOutlookTest extends TestBase {
	UnmatchedNewPaymentCarrierOutlook outlookk;
	OutlookFunctions outlook;

	public UnmatchedNewPaymentCarrierOutlookTest() {
		super();

	}

	@BeforeClass
	public void setUp() throws IOException {

		initialization();
		TestUtil.className = this.getClass().getName();
		outlook = new OutlookFunctions();
		outlookk = new UnmatchedNewPaymentCarrierOutlook();
		wait = new WebDriverWait(driver, 30);
	}

	@Test(dataProvider = "getoutlookLoginData")
	public void login(String un, String pwd) throws InterruptedException, AWTException {
		outlook.outlookLogin(un, pwd);
	}

	@Test(dependsOnMethods = "login")
	public void outlookloginTest() throws InterruptedException, AWTException {
		outlookk.clickPopUp();
		outlookk.clickOpenMailBox();
		outlookk.enterEmail(super.getProperties().getProperty("loadpaytestEmail"));
		// outlookk.clickOpen();
		outlookk.handleNewInbox();
		outlookk.verifyConfirmationMessage();

	}

}
