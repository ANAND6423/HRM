package HRM.qa.Tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import HRM.qa.base.Testbase;
import HRM.qa.pages.LoginPage;
import HRM.qa.pages.PIMPage;
import HRM.qa.util.TestUtil;
@Listeners (HRM.qa.util.MyListener.class)
public class LoginPageTest extends Testbase{
	LoginPage Login;
	PIMPage Pimpage;
	
	
	
	public LoginPageTest() {
		super();
	}
	
   @BeforeMethod
	public void setup()
	{
		Initialization();
		Login = new LoginPage();
	}
   @Test(priority =1)
	public void ValidateTitleTest()
	{
		String actualtitle = Login.validate_title();
        Assert.assertEquals(actualtitle,"OrangeHRM","TitleMismatch");
	}
   @Test(priority =2)
	public void ValidateLogoTest()
	{
         boolean flag = Login.validate_logo();
         Assert.assertTrue(flag);
         
	}
   
   @Test(priority =3)
	public void LoginAction()
	{
	   Pimpage = Login.login_action(prop.getProperty("username"), prop.getProperty("password"));
	  
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
