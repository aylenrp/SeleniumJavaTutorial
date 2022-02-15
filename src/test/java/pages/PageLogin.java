package pages;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import helpers.Helpers;

public class PageLogin {
	private WebDriver driver;
	private By userfield;
	private By passwordfield;
	private By loginButton;
	private By fields;

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userfield = By.name("userName");
		passwordfield = By.name("password");
		loginButton = By.name("submit");
		fields = By.tagName("input");
	}

	public void login(String user, String pass) {
		driver.findElement(userfield).sendKeys(user);
		driver.findElement(passwordfield).sendKeys(pass);
		driver.findElement(loginButton).click();
		/*File myScreenshots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(myScreenshots, new File("LOGIN " + System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void fields_login(String user, String pass) {
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user);
		loginFields.get(2).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	 public void verifyfields() {
		 List<WebElement> loginFields = driver.findElements(fields);
		 System.out.println(loginFields.size());
		 Assert.assertTrue(loginFields.size()==4);
	 }
}
