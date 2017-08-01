package com.Medinfi.commons.tests;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.Medinfi.common.pageobjects.BasePageDoctorSearch;

public class DropDownDataSetTest
{

    WebDriver driver;
	BasePageDoctorSearch baSearch;
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("DropDownDataSetTest");


	@BeforeClass
	public void setUp()
	{
  	//	driver=getDriver();
        PropertyConfigurator.configure("Configuration//log4j.properties");
		System.out.println("Launching Firefox browser..");
		logger.info("Launching Firefox browser..");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	capabilities.setCapability("acceptInsecureCerts", true);
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("marionette", false);
		System.setProperty("webdriver.gecko.driver","C:\\geko\\geckodriver.exe");
	    driver = new FirefoxDriver(capabilities);
		driver.manage().window().maximize();
		driver.navigate().to("http://www.medinfi.com");
		logger.info("Website has lauching..");
		
	}
	
	@Test
	 public void verifyDropDownDataSetOutput() throws InterruptedException
	 {
		System.out.println("This class is able to access");
		baSearch=PageFactory.initElements(driver, BasePageDoctorSearch.class);
	//	baSearch=new BasePageDoctorSearch(driver);
		System.out.println("This class is 2nd able to access");
		String dataSetTest=baSearch.dropDownDataSet();
		Thread.sleep(3000);
		WebElement txtValue=driver.findElement(By.xpath("//h1[@class='docName']"));  ////h1[@class='docName']
        
		String expectedValue=txtValue.getText();
		System.out.println("final value is "+expectedValue);
		Assert.assertEquals(dataSetTest, expectedValue);
		logger.info("test has verified....");
	 }
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}
}
