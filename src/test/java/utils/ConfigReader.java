package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;
	
	public static void loadProperties() {
		properties = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
			properties.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static String get(String key) {
//		if(properties == null) {
//			loadProperties();
//		}
//		return properties.getProperty(key);
//	}
	public static String get(String key) {

	    // 1️⃣ First check Jenkins / Maven parameter
	    String value = System.getProperty(key);

	    // 2️⃣ If Jenkins did NOT send it, read from config file
	    if (value == null || value.isEmpty()) {
	        if (properties == null) {
	            loadProperties();
	        }
	        value = properties.getProperty(key);
	    }

	    return value;
	}

}
