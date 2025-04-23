package HRM.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HRM.qa.base.Testbase;
import HRM.qa.util.TestUtil;

public class PIMPage extends Testbase {
	
	@FindBy(xpath = "(//a[@class='oxd-main-menu-item'])[2]")
	WebElement Pim;
	@CacheLookup
	@FindBy(xpath = "//button[@type='button'][@class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement Add;
	@CacheLookup
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement FTName;
	@CacheLookup
	@FindBy(xpath = "//input[@name='middleName']")
	WebElement MTName;
	@CacheLookup
	@FindBy(xpath = "//input[@name='lastName']")
	WebElement LTName;
	
	public PIMPage() {
		PageFactory.initElements(driver,this);
	}
	
	public void PIM_click_operation()
	{
		Pim.click();
		Add.click();
		
	}
	public void enterValue(String ft,String mt,String lt)
	{
		
		FTName.sendKeys(ft);
		MTName.sendKeys(mt);
		LTName.sendKeys(lt);
	}
	

}
