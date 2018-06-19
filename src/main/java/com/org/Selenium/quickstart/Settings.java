package com.org.Selenium.quickstart;

import java.io.File;
import java.io.IOException;

public class Settings {

	public static String rootDirectory = "WebAutomationSelenium/";
	public static String configDirectory = System.getenv("USERPROFILE") + "/documents/" + rootDirectory;
	public static String StoreDirName = "FacebookPostAutomation/";
	public static String configFileName = "config.ini";
	public static String autoFileName = "automation.json";
	
	public static String geckoDriver = "D:\\Selenium\\geckodriver.exe";
	
	
	public static void initiateDirectory() {
		Settings.createDirectory();
		Settings.createFiles();
	}
	
	// create directory if it doesn't exist
	private static void createDirectory() {
		File f = new File(configDirectory + StoreDirName);
		if(!f.exists())
			f.mkdirs();
	}
	
	//create file inside directory if doesn't exist
	// config.ini and automation.json
	private static void createFiles() {
		File conf = new File(configDirectory + StoreDirName + configFileName);
		File autojs = new File(configDirectory + StoreDirName + autoFileName);
		if(!conf.exists())
			try {conf.createNewFile();} catch (IOException e) {e.printStackTrace();}
		if(!autojs.exists())
			try {autojs.createNewFile();} catch (IOException e) {e.printStackTrace();}
	}
	
	public static String getJSONAbsolutePath() {
		return configDirectory + StoreDirName + autoFileName;
	}
	
	public static String getINIAbsolutePath() {
		return configDirectory + StoreDirName + configFileName;
	}
	
	
}
