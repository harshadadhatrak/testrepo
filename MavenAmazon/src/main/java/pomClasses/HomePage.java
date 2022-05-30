package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage<textonbtn> {
	WebDriver driver;
	@FindBy (xpath = "//input[@id='sp-cc-accept']")
	private WebElement cookies;
	
	@FindBy (xpath = "//span[text()= 'Account & Lists']")
	private WebElement accandlist;
	
   @FindBy (xpath = "//span[text()='Sign in']")
   private WebElement signin;
   
   @FindBy (xpath = "//span[text()='Sign Out']")
   private WebElement signout;
   
   @FindBy (xpath = "(//a[@class='nav-a  '])[1]")
   private WebElement todaysdeals;

   @FindBy (xpath = "(//a[@class='nav-a  '])[2]")
   private WebElement bestseller;
   
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	
	}
	
	public void cookiesaccept(){
		cookies.click();
	}
	
	public void openSignin() throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(accandlist).perform();
		Thread.sleep(1000);
		act.moveToElement(signin).click().build().perform();
	}
	
	
	public void signout() throws InterruptedException{
		Actions act = new Actions(driver);
		act.moveToElement(accandlist).perform();
		Thread.sleep(1000);
		act.moveToElement(signout).perform();
		act.click().perform();
		
	}
	public void clickontodaysdeals() throws InterruptedException{
		Thread.sleep(2000);
		todaysdeals.click();
	}

	public void clickonbestseller(){
		bestseller.click();
	}

	
}
