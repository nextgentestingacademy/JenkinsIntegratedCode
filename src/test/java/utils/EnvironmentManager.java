package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class EnvironmentManager {
	private static Properties prop = new Properties();
	
	static {
		try {
				FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
				prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getBaseUrl() {
		String env = JenkinsConfig.getEnv();
		return prop.getProperty(env + ".url");
	}
}
