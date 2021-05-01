package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import accelerators.BaseClass;

public class LennoxPage extends BaseClass {
	
	WebDriver ldriver;
	
	public LennoxPage(WebDriver driver)
	{
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public HomePage homePage()
	{
		return new HomePage(ldriver);
	}
	
	public LoginPage loginPage()
	{
		return new LoginPage(ldriver);
	}
	
	public LeadDetailsPage leadDetailsPage()
	{
		return new LeadDetailsPage(ldriver);
	}
	
	public BuildAProposalPage buildAProposalPage()
	{
		return new BuildAProposalPage(ldriver);
	}
	
	public AddLeadsPage addLeadsPage()
	{
		return new AddLeadsPage(ldriver);
	}
	
	public AddDocumentPage addDocumentPage()
	{
		return new AddDocumentPage(ldriver);
	}
	
	public LeadsPage leadsPage()
	{
		return new LeadsPage(ldriver);
	}
	
	
	

}
