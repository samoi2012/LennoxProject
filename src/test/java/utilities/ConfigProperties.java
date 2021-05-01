package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import accelerators.BaseClass;


public class ConfigProperties extends BaseClass {
	
	public Properties properties;

	

	public Properties loadFrom(String propertyFilePath, String propertyFileName) throws Exception {

		
		try {
			if (!propertyFileName.equals("")) {
				if(propertyFileName.indexOf(".")>0)
				 {
					String fileFormat = propertyFileName.substring(propertyFileName.indexOf("."));
					if(fileFormat.equalsIgnoreCase(".properties"))
					 {
						File file = new File(propertyFilePath + propertyFileName);
						FileInputStream fs = new FileInputStream(file);
						properties = new Properties();
						properties.load(fs);
					} else {
						
						throw new Exception("FILE FORMAT SEEMS TO BE INCORRECT" + "\n");
					}
				} else {
					
					throw new Exception("FILE FORMAT IS MISSING" + "\n");
				}
			} else {
				
				throw new Exception("FILE NAME SEEMS TO BE EMPTY" + "\n");
			}
		} catch (Exception e) {
			
			throw new Exception(
					"UNABLE TO LOAD USER PROPERTIES FROM THE FILE(" + propertyFileName + ")\n" + e.getMessage() + "\n");
		}
		
		return properties;
	}

	}
