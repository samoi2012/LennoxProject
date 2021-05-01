package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuildAProposalPage extends LennoxPage {
	WebDriver ldriver;
	public BuildAProposalPage(WebDriver driver) {
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
		js = (JavascriptExecutor) ldriver;
	}

	@FindBy(how = How.XPATH, using = "//a[text()='SELECT LEAD']")
	public WebElement linkSelectLead;

	public LeadsPage createAProposalForExistingLead() {

		wait.until(ExpectedConditions.visibilityOf(linkSelectLead));
		wait.until(ExpectedConditions.elementToBeClickable(linkSelectLead));
		js.executeScript("arguments[0].click();", linkSelectLead);
		return new LeadsPage(ldriver);
	}

}
