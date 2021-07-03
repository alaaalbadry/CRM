package com.techvalueinsight.jsf.primefaces.util.configurations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigProperties {

		
		private Properties prop = new Properties();

		private static ConfigProperties configProperties = null;

		private ConfigProperties() {

			 //try (InputStream input = new FileInputStream("src//main//resources//config.properties")) {
			System.out.println("Start loading Properties");
			String propFileName = "config.properties";
			try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(propFileName)) {
				// load a properties file
				if (input != null) {
					prop.load(input);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				prop.load(input);
				System.out.println("Loading Properties succeeded ");
				
			} catch (IOException ex) {
				System.err.println("Loading Properties failed: " + ex);
				ex.printStackTrace();
			}

		}
		
		public static Properties getAllProperties() {
			if (configProperties == null) {
				configProperties = new ConfigProperties();
			}
			return configProperties.prop;
		}

		public static String getProperty(String key) {
			if (configProperties == null) {
				configProperties = new ConfigProperties();
			}
			return configProperties.prop.getProperty(key);
		}
		
}


