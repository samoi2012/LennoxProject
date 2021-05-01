package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import accelerators.BaseClass;

public class DataProviderRepository extends BaseClass {
	
	@DataProvider(name="Lennox")
	
		public static Object[][] dataProviderMethod(Method method) throws Exception
		{
			excel = new Excel();
			Object[][] testDataArray = null;
			if(method.getName().equals("lennoxScenario"))
			{
				testDataArray = excel.getTestDataAsTwoDimesionalObjectArray(System.getProperty("user.dir"),"\\DataSheet.xls","Sheet1");
			}
			return testDataArray;
		}
	}


