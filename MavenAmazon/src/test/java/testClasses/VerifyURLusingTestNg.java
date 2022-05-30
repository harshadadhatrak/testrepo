package testClasses;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BaseClass;
import pomClasses.HomePage;
import pomClasses.LoginTab;
import pomClasses.Password;
import utils.Utility;

public class VerifyURLusingTestNg {
	WebDriver driver;
	HomePage homePage;
	LoginTab logintab;
	Password password;
	int row =1;
	int cell;
	String id;
	
	@Parameters ("browserName")
	@BeforeTest
	public void launchBrowser(String browser)
	{
		if(browser.equals("Chrome"))
		{
			driver = BaseClass.openChromeBrowser();
		}
		
		if(browser.equals("Firefox"))
		{
			driver = BaseClass.openFirefoxBrowser();
		}
		
		if(browser.equals("Edge"))
		{
			driver = BaseClass.openEdgeBrowser();
	}
		
	}
	
	@BeforeClass
	public void createPOMobj()
	{
		homePage = new HomePage(driver);
		logintab = new LoginTab(driver);
		password = new Password(driver);
	}
	
	@BeforeMethod
	public void logingin() throws InterruptedException
	{
		driver.get("https://www.amazon.co.uk/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(invocationCount = 2)
	public void verifyURLafterlogin() throws InterruptedException, EncryptedDocumentException, IOException
	{ id = "1001";
		homePage.cookiesaccept();
	homePage.openSignin();
	Thread.sleep(3000);

	 String data = Utility.getDatafromExcelSheet(row,0);
		logintab.gotopassword(data);
	 data = Utility.getDatafromExcelSheet(row,1);
	password.signin(data);
		Thread.sleep(3000);
		String url1 = driver.getCurrentUrl();
		String title1 = driver.getTitle();
		Assert.assertEquals(url1,"https://www.amazon.co.uk/?ref_=nav_signin&");
		Assert.assertEquals(title1, "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more");
		row++;
	}
	
	@Test (invocationCount = 2)
	public void verifyURLoftodaysdeals() throws InterruptedException, EncryptedDocumentException, IOException
	{  id = "1002";
		Thread.sleep(3000);
		homePage.cookiesaccept();
	    Thread.sleep(3000);
	    homePage.openSignin();
	    Thread.sleep(3000);
	    String data = Utility.getDatafromExcelSheet(row,0);
	    System.out.println(data);
		logintab.gotopassword(data);
		Thread.sleep(3000);
		  data = Utility.getDatafromExcelSheet(row,1);
		password.signin(data);
		
		Thread.sleep(3000);
        homePage.clickontodaysdeals();
		
		Thread.sleep(3000);
		String url2 = driver.getCurrentUrl();
		String title2 = driver.getTitle();
		Assert.assertEquals(url2,"https://www.amazon.co.uk/deals?ref_=nav_cs_gb");
		Assert.assertEquals(title2, "Today's Deals: New Deals. Every Day.");
	     row++;
	}
	
	
	@AfterMethod
	public void loggingout(ITestResult result) throws InterruptedException, IOException
	{ if (ITestResult.FAILURE == result.getStatus())
	    {
		Utility.captureScreenShot(driver, id);
	    }
		homePage.signout();
		
	}
	
	@AfterClass
	public void clearobj()
	{
		homePage = null;
		logintab = null;
		password = null;
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
		driver = null;
		System.gc();
	}
	

}
