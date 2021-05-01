package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadDetailsPage extends LennoxPage {
	
	WebDriver ldriver;
	
	public LeadDetailsPage(WebDriver driver)
	{
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
	}
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'Lead Saved Successfully')]")
	public WebElement linkLeadSuccessfullySaved;
	
	
	
	
}
