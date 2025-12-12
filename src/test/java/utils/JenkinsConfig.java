package utils;

public class JenkinsConfig {

	public static String getEnv() {
		return System.getProperty("env","DEV");
	}
	
	public static String getBrowser() {
		return System.getProperty("browser","chrome");
	}
}
