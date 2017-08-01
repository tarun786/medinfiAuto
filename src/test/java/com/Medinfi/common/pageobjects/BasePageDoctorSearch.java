package com.Medinfi.common.pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Factory;

public class BasePageDoctorSearch 
{
	protected WebDriver driver;

public BasePageDoctorSearch(WebDriver driver)
{
	this.driver=driver;
}
org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("BasePageDoctorSearch");


@FindBy(xpath="//input[@id='city-locality1']")
WebElement cityXpath;

@FindBy(xpath=".//*[@id='ip1_text']")
WebElement facilities;

@FindBy(xpath=".//*[@id='autoResult1']/li[1]")
WebElement hospital;


public By doctorXapth()
{
	By doctor=By.xpath("//*[@id='autoResult1']/li[2]");
	return doctor;
}



public By availableDoctor()
{
	By listofAvailable=By.xpath(".//*[@id='resRow2']");
	return listofAvailable;
}

@FindBy(xpath=".//*[@id='autoCityResult1']/li/a")
WebElement autocityResults;

/**
 * Will get data as dynamic through properties files
 * we can modify any time 
 * @return
 */

public Properties getProp()
{
    File file = new File("Configuration//prop.properties");
    FileInputStream fileInputStream = null;
    try {
        fileInputStream = new FileInputStream(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
    Properties properties = new Properties();
    try {
        properties.load(fileInputStream);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return properties;
}

public String dropDownDataSet() throws InterruptedException
{   PropertyConfigurator.configure("Configuration//Log4j.properties");
	String part1="";
	cityXpath.click();  //search box
	logger.info("Test case has started...");
	Properties properties=getProp();
	cityXpath.sendKeys(properties.getProperty("cityName"));
	System.out.println("city name is entered..");
	logger.info("city name is entered");
	Thread.sleep(3000);
	//select area where you want to serach
	autocityResults.click();
	logger.info("autoresults has selected...");
	
	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(doctorXapth())).click();
	System.out.println("Enter the name of doctor or hospital");
	facilities.sendKeys(properties.getProperty("nameOffacilities"));
	System.out.println("facilities name has entered..");
	logger.info("facilities name has entered...");
	Thread.sleep(5000);
	java.util.List<WebElement> elements=driver.findElements(availableDoctor());
	System.out.println("Total no of serch found "+elements.size());
	logger.info("Total no of serch found "+elements.size());
	System.out.println("By default we put i==0 value to verify first doctor:");
	logger.info("By default we put i==0 value to verify first doctor:");
	int numRange=0;
	if(numRange<=elements.size())
	{
		String sValue=elements.get(numRange).getText();
        driver.findElement(By.partialLinkText(sValue)).click();
		String parts[]=sValue.split(",");
		part1=parts[0];
	   logger.info("we send the return value to test function");   
	
	}
	return part1;
    
  	
}
}