package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends LennoxPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
	}
	
	@FindBy(how = How.ID, using = "j_username")
	public WebElement txtboxUserId;
	
	@FindBy(how = How.ID, using = "j_password")
	public WebElement txtPassword;
	
	@FindBy(how = How.ID, using = "loginSubmit")
	public WebElement buttonSignIn;	
	
	
	public HomePage login(String userName, String password)
	{
		wait.until(ExpectedConditions.visibilityOf(txtboxUserId));
		txtboxUserId.sendKeys(userName);
		txtPassword.sendKeys(password);
		buttonSignIn.click();
		return new HomePage(ldriver);
	}

}
