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

public class VerifyTextusingTestNg {
	
	WebDriver driver;
	HomePage homePage;
	LoginTab logintab;
	Password password;
	
	@Parameters ("browserName")
	@BeforeTest
	public void launchBrowser(String browser)
	{
		System.out.println(browser);
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
	public void logingin()
	{
		driver.get("https://www.amazon.co.uk/");
		driver.manage().window().maximize();
	}

	@Test
	public void verifyURLafterlogin() throws InterruptedException, EncryptedDocumentException, IOException
	{homePage.cookiesaccept();
	homePage.openSignin();
	Thread.sleep(3000);
     String data = Utility.getDatafromExcelSheet(1,0);
	logintab.gotopassword(data);
    
	data = Utility.getDatafromExcelSheet(1, 1);
	password.signin(data);
		Thread.sleep(3000);
		String url1 = driver.getCurrentUrl();
		String title1 = driver.getTitle();
		System.out.println(url1);
		System.out.println(title1);
		Assert.assertEquals(url1,"https://www.amazon.co.uk/?ref_=nav_signin&");
		Assert.assertEquals(title1, "Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more");
		
	}
	
	@Test
	public void verifyURLoftodaysdeals() throws InterruptedException, EncryptedDocumentException, IOException
	{  Thread.sleep(3000);
		homePage.cookiesaccept();
	    Thread.sleep(3000);
	    homePage.openSignin();
	    Thread.sleep(3000);
	    String data = Utility.getDatafromExcelSheet(1,0);
		logintab.gotopassword(data);
		Thread.sleep(3000);
		data = Utility.getDatafromExcelSheet(1, 1);
		password.signin(data);
		
		Thread.sleep(3000);
        homePage.clickontodaysdeals();
		
		Thread.sleep(3000);
		String url2 = driver.getCurrentUrl();
		String title2 = driver.getTitle();
		System.out.println(url2);
		System.out.println(title2);
		Assert.assertEquals(url2,"https://www.amazon.co.uk/deals?ref_=nav_cs_gb");
		Assert.assertEquals(title2, "Today's Deals: New Deals. Every Day.");
	}
	
	@AfterMethod
	public void loggingout(ITestResult result) throws InterruptedException, IOException
	{ if (ITestResult.FAILURE == result.getStatus())
	{
		Utility.captureScreenShot(driver, result.getName());
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
