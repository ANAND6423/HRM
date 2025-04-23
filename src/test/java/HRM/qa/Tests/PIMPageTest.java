package HRM.qa.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import HRM.qa.base.Testbase;
import HRM.qa.pages.LoginPage;
import HRM.qa.pages.PIMPage;
import HRM.qa.util.TestUtil;
@Listeners (HRM.qa.util.MyListener.class)
public class PIMPageTest extends Testbase {
	LoginPage Login;
	PIMPage Pimpage;
	String sheetname = "Sheet1";
	public PIMPageTest() {
		super();
	}
	
   @BeforeMethod
	public void setup()
	{
		Initialization();
		Login = new LoginPage();
		Pimpage = Login.login_action(prop.getProperty("username"), prop.getProperty("password"));
		Pimpage.PIM_click_operation();
	}
   
   @DataProvider
	public Object[][] hrmgetTestdata()
	{
		Object data[][] = TestUtil.getTestData(sheetname);
		return data;
	}
   
  @Test(priority =1,dataProvider ="hrmgetTestdata" )
 public void PIMAction(String FTName,String MTName,String LTName)
 {
	   Pimpage.enterValue(FTName, MTName, LTName);
 }
   
   @AfterMethod
   public void tearDown()
   {
	   driver.quit();
   }
}
