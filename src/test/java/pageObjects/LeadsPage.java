package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadsPage extends LennoxPage {
	
	WebDriver ldriver;
	
	public LeadsPage(WebDriver driver)
	{
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
	}
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ADD LEAD'])[2]")
	public WebElement linkAddLead;
	
	public AddLeadsPage navigateToAddLeadsPage()
	{
		linkAddLead.click();
		return new AddLeadsPage(ldriver);
	}
	
	
	
	
	
	
	
	
	}
