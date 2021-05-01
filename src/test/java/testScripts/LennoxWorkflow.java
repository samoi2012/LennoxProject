package testScripts;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import accelerators.BaseClass;
import junit.framework.Assert;
import pageObjects.LeadsPage;

public class LennoxWorkflow extends BaseClass {

	public void addLeadWorkflow(String instanceNumber, String userName, String password, String menu, String subMenu,
			String firstName, String lastName, String eMail, String phoneNumber, String scheduleDate,
			String documentType, String documentPath, String imagePath) throws Exception

	{

		test = report.startTest("LennoxDemo" + instanceNumber);

		lennoxPage.homePage().navigateToLoginPage();
		lennoxPage.loginPage().login(userName, password);
		lennoxPage.homePage().navigateToSubmenu(menu, subMenu);
		lennoxPage.buildAProposalPage().createAProposalForExistingLead();
		lennoxPage.leadsPage().navigateToAddLeadsPage();
		lennoxPage.addLeadsPage().addLeadInformation(firstName, lastName, phoneNumber, eMail, scheduleDate);
		lennoxPage.addLeadsPage().addDocument(documentPath);
		lennoxPage.addDocumentPage().addDocument(documentType, documentPath);
		lennoxPage.addDocumentPage().confirmAddDocument();
		lennoxPage.addLeadsPage().addImage(imagePath);
		lennoxPage.addLeadsPage().saveLead();
		String successMessage = lennoxPage.leadDetailsPage().linkLeadSuccessfullySaved.getText().trim();
		Assert.assertEquals("Lead Saved Successfully", successMessage);
		
		report.endTest(test);
	}

}
