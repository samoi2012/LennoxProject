package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends LennoxPage  {
	
	WebDriver ldriver;
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
	}
	
	@FindBy(how = How.XPATH, using = "//a[contains(@class,'NEEDS_AUTHENTICATION')]")
	public WebElement linkSignIn;
	
	@FindBy(how = How.XPATH, using = "//i[contains(@class,'v2-hamburger-menu')]")
	public WebElement linkMenu;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'v2-hamburger-menu-flyout')]//a[text()='Sales Tools']")
	public WebElement linkSalesTools;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Build a Proposal']")
	public WebElement linkBuildAProposal;

	public LoginPage navigateToLoginPage()
	{
		wait.until(ExpectedConditions.visibilityOf(linkSignIn));
		linkSignIn.click();
		return new LoginPage(ldriver);
	}
	
	public BuildAProposalPage navigateToSubmenu(String menuName,String subMenu)
	{
		wait.until(ExpectedConditions.visibilityOf(linkMenu));
		linkMenu.click();
		if (menuName.equals("Sales Tools"))
		{
			linkSalesTools.click();
		}
		
		if(subMenu.equals("Build A Proposal"))
		{
			linkBuildAProposal.click();
		}
		
		return new BuildAProposalPage(ldriver);
	}

	
}
