package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Password {

	@FindBy (xpath = "//input[@type='password']")
	private WebElement password;
	
	@FindBy (xpath = "//input[@id='signInSubmit']")
	private WebElement signin;
	
	public Password(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void signin(String pass) {
		password.sendKeys(pass);
		signin.click();
	}
}
