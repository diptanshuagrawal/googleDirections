package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import utility.Security;

public class ApiConfig {
	
	public static String decryptApiKey(String env, String encryptionPassword) throws IOException {
		FileReader reader=new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\configs\\"+env+"\\application.properties");  
	      
	    Properties p=new Properties();  
	    p.load(reader);
	    
        String apiKey = p.getProperty("apiKey");
  
	    return Security.decryptor(encryptionPassword, apiKey);
	}
}
