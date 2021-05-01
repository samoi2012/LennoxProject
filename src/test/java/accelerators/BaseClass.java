package accelerators;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LennoxPage;
import utilities.ConfigProperties;
import utilities.Excel;

public class BaseClass {

	private WebDriver driver;
	public LennoxPage lennoxPage;
	public ConfigProperties configproperties;
	public Properties configprops;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions action;
	public static Excel excel;
	public com.relevantcodes.extentreports.ExtentReports report;
	public com.relevantcodes.extentreports.ExtentTest test;
	public com.relevantcodes.extentreports.ExtentTest childTestCase1;
	public com.relevantcodes.extentreports.ExtentTest childTestCase2;
	public com.relevantcodes.extentreports.ExtentTest childTestCase3;
	public com.relevantcodes.extentreports.ExtentTest childTestCase4;
	public com.relevantcodes.extentreports.ExtentTest childTestCase5;
	public com.relevantcodes.extentreports.ExtentTest childTestCase6;
	public com.relevantcodes.extentreports.ExtentTest childTestCase7;
	public com.relevantcodes.extentreports.ExtentTest childTestCase8;
	public com.relevantcodes.extentreports.ExtentTest childTestCase9;
	public com.relevantcodes.extentreports.ExtentTest childTestCase10;
	public String failedTestStep="";

	@BeforeSuite
	public void setupClass() throws Exception {


		configproperties = new ConfigProperties();
		configprops = configproperties.loadFrom(System.getProperty("user.dir") + "\\", "config.properties");

		if (configprops.getProperty("browser").equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		}

		if (configprops.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
		}

		java.util.Date today = new java.util.Date();
		String timeStamp = new java.sql.Timestamp(today.getTime()).toString();
		timeStamp = timeStamp.replace(" ", "_").replace(":", "_").replace(".", "_");
		System.out.println("timeStamp");

		report = new com.relevantcodes.extentreports.ExtentReports(
				System.getProperty("user.dir") + "//LennoxReport_" + timeStamp + ".html", false,
				DisplayOrder.OLDEST_FIRST);
	

	}

	@BeforeMethod
	public void setupTest() throws Exception {


		if (configprops.getProperty("browser").equals("firefox")) {

			driver = new FirefoxDriver();
			Thread.sleep(30000);

		}

		if (configprops.getProperty("browser").equals("chrome")) {

			driver = new ChromeDriver();
			Thread.sleep(30000);

		}

		driver.get("https://www.liidaveqa.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		lennoxPage = new LennoxPage(driver);
	

	}

	@AfterMethod
	public void teardown(ITestResult result) throws Exception {

		if (driver != null) {
			driver.quit();
		}
		
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, "Lead Not Created Successfully");
			
		}
		
		}
	
	@AfterSuite
	public void afterSuiteMethod()
	{
		report.flush();
	}

}
