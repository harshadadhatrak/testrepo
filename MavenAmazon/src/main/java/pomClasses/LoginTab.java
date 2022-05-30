package pomClasses;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utility;

public class LoginTab {
	
	@FindBy (xpath = "//input[@type='email']")
	private WebElement phno;
	
	@FindBy (xpath = "//input[@id='continue']")
	private WebElement conti;
	
	public LoginTab(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void gotopassword(String mobile) throws EncryptedDocumentException, IOException {
		phno.sendKeys(mobile);
		conti.click();
	}
	
}
