package HRM.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import HRM.qa.base.Testbase;
import HRM.qa.util.TestUtil;

public class LoginPage extends Testbase{
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;
	@CacheLookup
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@CacheLookup
	@FindBy(xpath = "//button[@type='submit']")
	WebElement Loginbutton;
	@CacheLookup
	@FindBy(xpath = "//img[@alt='company-branding']")
	WebElement Logo;
	
	
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	public String validate_title()
	{
		return driver.getTitle();
	}
	
	public boolean validate_logo()
	{
		TestUtil.PresenceOfElement(Logo, 10);
		return Logo.isDisplayed();
	}
    
	public  PIMPage login_action(String user,String pass) 
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		Loginbutton.click();
		return  new PIMPage();
	}

}
