package util;

import java.io.FileReader;
import java.util.Properties;

public class ConfigReader {
	
	//instance
	private static ConfigReader instance;
	private Properties config = new Properties();
	
	final String CONFIG_FILE_PATH = "/Volumes/Data HD/Workspace/sts/todos-app/src/main/resources/config.properties";
	
	// private constructor
	private ConfigReader() {
		try {
			FileReader reader = new FileReader(CONFIG_FILE_PATH);
			config.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigReader getInstance() {
		if(instance == null) {
			instance = new ConfigReader();
		}
		return instance;
	}
	
	public String getProperty(String key) {
		return config.getProperty(key);
	}
}
