package testScripts;

import java.io.IOException;

import org.testng.annotations.Test;

import utilities.DataProviderRepository;

public class LennoxTestCase extends LennoxWorkflow{
	
	@Test(dataProvider="Lennox",dataProviderClass=DataProviderRepository.class)
	public void lennoxScenario (String testCaseNumber,String userName,String Password,String menu,String subMenu,String firstName,String lastName,String eMail,String phoneNumber,String scheduledDate,String documentType,String documentName,String imageName) throws Exception 
	{
		System.out.println("user dir "+System.getProperty("user.dir"));
		addLeadWorkflow(testCaseNumber,userName,Password,menu,subMenu,firstName,lastName,eMail,phoneNumber,scheduledDate,documentType,System.getProperty("user.dir")+"\\documents\\"+documentName,System.getProperty("user.dir")+"\\documents\\"+imageName);
	
	}

	

}
