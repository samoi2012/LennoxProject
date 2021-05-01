package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddLeadsPage extends LennoxPage {

	WebDriver ldriver;
	
	public AddLeadsPage(WebDriver driver) {
		super(driver);
		this.ldriver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(ldriver, 30);
		js = (JavascriptExecutor) ldriver;
		action = new Actions(ldriver);
	}

	@FindBy(how = How.ID, using = "firstName")
	public WebElement inputFirstName;

	@FindBy(how = How.ID, using = "lastName")
	public WebElement inputLastName;

	@FindBy(how = How.ID, using = "phNo")
	public WebElement inputPhoneNumber;

	@FindBy(how = How.ID, using = "email")
	public WebElement inputEmail;

	@FindBy(how = How.ID, using = "calender1")
	public WebElement inputCalendar;

	@FindBy(how = How.XPATH, using = "//span[text()='Next']")
	public WebElement buttonNext;

	@FindBy(how = How.XPATH, using = "//span[text()='Prev']")
	public WebElement buttonPrevious;
	
	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-month']")
	public WebElement datePickerMonth;
	
	@FindBy(how = How.XPATH, using = "//span[@class='ui-datepicker-year']")
	public WebElement datePickerYear;
	
	@FindBy(how = How.XPATH, using = "//table[@class='ui-datepicker-calendar']")
	public WebElement datePickerDate;

	@FindBy(how = How.XPATH, using = "//a[@class='open-add-document']")
	public WebElement buttonAddDocument;

	@FindBy(how = How.XPATH, using = "//input[@name='imageFiles']")
	public WebElement buttonAddImage;

	@FindBy(how = How.XPATH, using = "//span[text()='SAVE LEAD']")
	public WebElement buttonsaveLead;

	public AddDocumentPage addDocument(String documentPath) throws Exception {

		wait.until(ExpectedConditions.visibilityOf(buttonAddDocument));
		
		if (!documentPath.equals("")) {
			buttonAddDocument.click();
		}

		return new AddDocumentPage(ldriver);
			
	}

	public void addLeadInformation(String firstName, String lastName, String phoneNumber, String email,
			String scheduledDate) throws InterruptedException {	
		
		wait.until(ExpectedConditions.visibilityOf(inputFirstName));
		inputFirstName.sendKeys(firstName);
		inputLastName.sendKeys(lastName);
		inputPhoneNumber.sendKeys(phoneNumber);
		inputEmail.sendKeys(email);
		String[] scheduledDateSplitter = scheduledDate.split("-");
		inputCalendar.click();		
		wait.until(ExpectedConditions.visibilityOf(buttonNext));
		while((!datePickerMonth.getText().equals(scheduledDateSplitter[1]))||(!datePickerYear.getText().equals(scheduledDateSplitter[2])))
		{
			js.executeScript("arguments[0].click();", buttonNext);
		}
		
		datePickerDate.findElement(By.xpath("//td/a[text()='"+scheduledDateSplitter[0]+"']")).click();
		Thread.sleep(5000);
	}

	public void addImage(String imagePath) throws IOException, InterruptedException {
		Thread.sleep(5000);
		if (!imagePath.equals("")) {
			buttonAddImage.sendKeys(imagePath);
		}
		Thread.sleep(10000);
		ArrayList tabs = new ArrayList(ldriver.getWindowHandles());
		ldriver.switchTo().window(tabs.get(0).toString());
	}

	public LeadsPage saveLead() throws InterruptedException {
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView(true);",buttonsaveLead);
		js.executeScript("arguments[0].click();", buttonsaveLead);
		return new LeadsPage(ldriver);
	}

}
