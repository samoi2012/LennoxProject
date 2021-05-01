package pageObjects;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddDocumentPage extends LennoxPage {
	
	WebDriver ldriver;
	
	
	public AddDocumentPage(WebDriver driver)
	{
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
		action = new Actions(ldriver);
	}
	
	@FindBy(how = How.XPATH, using = "(//select[contains(@name,'.documentType')])[2]")
	public WebElement dropdownSelectDocumentType;
	
	@FindBy(how = How.XPATH, using = "//input[@id='multipleFileSelect-1']")
	public WebElement buttonSelectFile;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Add To Lead']")
	public WebElement buttonAddToLead;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Cancel']")
	public WebElement buttonCancel;
	
	
	
	public void addDocument(String documentType,String documentPath) throws Exception
	{

		Thread.sleep(5000);	
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", dropdownSelectDocumentType);
		Select documentTypeSelect = new Select(dropdownSelectDocumentType);
		documentTypeSelect.selectByValue(documentType);
		Thread.sleep(5000);
		buttonSelectFile.sendKeys(documentPath);
		Thread.sleep(5000);
	
	}
	
	public AddLeadsPage confirmAddDocument() throws Exception
	{

		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(buttonAddToLead));
		wait.until(ExpectedConditions.elementToBeClickable(buttonAddToLead));
		action.moveToElement(buttonAddToLead).perform();
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", buttonAddToLead);
		return new AddLeadsPage(ldriver);
	
	}
		
}
