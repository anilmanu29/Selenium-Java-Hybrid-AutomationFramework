package pages.loadpay.outlook;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class outlooklogin extends TestBase {

	Robot r;

	@FindBy(xpath = "//input[@id='username']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='password']")
	WebElement Password;

	@FindBy(xpath = "//span[contains(.,'sign in')]")
	WebElement signin;

	// Initializing the Page Objects:
	public outlooklogin() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void outlookLogin(String un, String pwd) throws AWTException, InterruptedException {

		driver.get(super.getProperties().getProperty("outlookurl"));
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", signin);
	}


}