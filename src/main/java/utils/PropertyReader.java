package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public Properties properties;

	String configPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
	String validationPath = System.getProperty("user.dir") + "/src/main/resources/validation.properties";

	public PropertyReader(){
		BufferedReader reader1,reader2;
		try {
			reader1 = new BufferedReader(new FileReader(configPath));
			reader2 = new BufferedReader(new FileReader(validationPath));
			properties = new Properties();
			try {
				properties.load(reader1);
				properties.load(reader2);
				reader1.close();
				reader2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + configPath);
		}
	}

	public String readProperty(String key) {
		return properties.getProperty(key);
	}
}
