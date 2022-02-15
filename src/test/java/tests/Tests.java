package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.Screenshooter;
import helpers.WebDriverManager;
import io.netty.handler.codec.http.HttpContentEncoder.Result;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;


public class Tests {
  private WebDriver driver;
  ArrayList<String> tabs;
  @BeforeMethod
  public void setUp() {
	  //DesiredCapabilities caps = new DesiredCapabilities();
	  System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize(); 
	  //driver.manage().window().fullscreen();
	  //driver.manage().window().setSize(new Dimension(200, 200));
	  //for (int i=0; i < 800; i++)
	  //driver.manage().window().setPosition(new Point(i,i));
	  driver.navigate().to("https://demo.guru99.com/test/newtours/");
	  //Helpers helper = new Helpers();
	  //helper.sleepSeconds(5);
	  JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
	  String googleWindow = "window.open('http://www.google.com')";
	  javaScriptExecutor.executeScript(googleWindow);
	  
	  tabs = new ArrayList<String>(driver.getWindowHandles());
  }
  @Test
  public void pruebaUno() {
	  WebDriverManager.setWindowSize(driver, "maximize");
	  driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com/user/Draculinio");
	  driver.switchTo().window(tabs.get(0));
	  PageLogin pageLogin = new PageLogin(driver);
	  pageLogin.login("user","user");	
	  PageLogon pageLogon = new PageLogon(driver);
	  pageLogon.assertLogonPage();
  }
  @Test
  public void pruebaDos() {
	  WebDriverManager.setWindowSize(driver, "fullscreen");
	  PageLogin pageLogin = new PageLogin(driver);
	  pageLogin.login("mercury","mercury");
	  PageReservation pageReservation = new PageReservation(driver);
	  pageReservation.clickFlights();
	  pageReservation.assertPage();
  }
  @Test
  public void pruebaTres() {
	  WebDriverManager.setWindowsSize(driver,400, 400);
	  PageLogin pageLogin = new PageLogin(driver);
	  pageLogin.login("mercury","mercury");
	  PageReservation pageReservation = new PageReservation(driver);
	  pageReservation.clickFlights();
	  pageReservation.selectPassengers(2);
	  pageReservation.selectFromPort(3);
	  pageReservation.selectToPort("London");
	  pageReservation.assertPage();
  }
  /*@Test
  public void pruebaCantidadDeCampos() {
	  PageLogin pageLogin = new PageLogin(driver);
	  pageLogin.verifyfields();
  }*/
  @AfterMethod
  public void tearDown(ITestResult result) {
	  if (!result.isSuccess()) {
		  Screenshooter.takeScreenshoot("Error", driver);
	  }
	  //driver.close();
	  driver.switchTo().window(tabs.get(1)).close();
	  driver.switchTo().window(tabs.get(0)).close();
	  
  }
  
  
}
