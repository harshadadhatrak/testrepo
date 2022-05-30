package testClasses;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pomClasses.HomePage;
import pomClasses.LoginTab;
import pomClasses.Password;
import utils.Utility;

public class VerifyURL {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Nikhil-PC\\\\Desktop\\\\Harshada-testing\\\\Automation\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.co.uk/");
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println(url);
		System.out.println(title);
		if(url.equals("https://www.amazon.co.uk/") && title.equals("Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		HomePage homePage = new HomePage(driver);
		homePage.cookiesaccept();
		homePage.openSignin();
		Thread.sleep(3000);
	
		
		LoginTab logintab = new LoginTab(driver);
		String data = Utility.getDatafromExcelSheet(1,0);
		logintab.gotopassword(data);
		
		Password password = new Password(driver);
		 data = Utility.getDatafromExcelSheet(1,1);
			password.signin(data);
		
		Thread.sleep(2000);
		String url1 = driver.getCurrentUrl();
		String title1 = driver.getTitle();
		System.out.println(url1);
		System.out.println(title1);
		if(url1.equals("https://www.amazon.co.uk/?ref_=nav_signin&") && title1.equals("Amazon.co.uk: Low Prices in Electronics, Books, Sports Equipment & more"))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		homePage.clickontodaysdeals();
		
		Thread.sleep(2000);
		String url2 = driver.getCurrentUrl();
		String title2 = driver.getTitle();
		System.out.println(url2);
		System.out.println(title2);
		if(url2.equals("https://www.amazon.co.uk/deals?ref_=nav_cs_gb") && title2.equals("Today's Deals: New Deals. Every Day."))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("Fail");
		}
		
		
		
	homePage.signout();
	driver.close();

	}

}
