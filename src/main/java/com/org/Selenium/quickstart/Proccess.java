package com.org.Selenium.quickstart;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;

public class Proccess {
	private String URL = "http://www.facebook.com";
	private WebDriver driver;
	
	public void init() {
		System.setProperty("webdriver.gecko.driver", Settings.geckoDriver);
		driver = new FirefoxDriver();
	}
	
	public void Login(String username, String password) {
		WebElement element = driver.findElement(By.name("email"));
		element.sendKeys(username);
		element = driver.findElement(By.name("pass"));
		element.sendKeys(password);
	}
	
	public boolean isLoggedIn() {
		return false;
	}
	
	public void newTab() {
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                          
		r.keyPress(KeyEvent.VK_CONTROL); 
		r.keyPress(KeyEvent.VK_T); 
		r.keyRelease(KeyEvent.VK_CONTROL); 
		r.keyRelease(KeyEvent.VK_T);   
	}
	
	public void goToTab(int tab) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tab));
	}
	
	
	public Thread ControlApp = new Thread() {
		int i =0;
		public void run(){

		    try {

		      for(;;){
		        System.out.println(++i);
		        Thread.sleep(1000);
		      }

		    } catch(InterruptedException e) {

		      System.out.println("sleep interrupted");      
		    }
		  }
	};
		
		
//		Thread.sleep(5000);

//		
//		Set<Cookie> cookies = driver.manage().getCookies();
//        System.out.println("Size: " + cookies.size());
//        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(0));
//       	
//        Thread.sleep(1000);
//        driver.switchTo().window(tabs.get(0));
	

}
