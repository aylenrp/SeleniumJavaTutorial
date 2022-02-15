package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import helpers.Helpers;

public class PageReservation {
	private WebDriver driver;
	private By titleText;
	private By flightsButton;
	private By passengersDrop;
	private By fromDrop;
	private By toDrop;

	public PageReservation(WebDriver driver) {
		this.driver = driver;
		titleText=By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[1]/td/font/font/b/font/font");
		flightsButton=By.xpath("/html/body/div[2]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a");
	    passengersDrop=By.name("passCount");
	    fromDrop=By.name("fromPort");
	    toDrop = By.name("toPort");
	}
    public void clickFlights() {
    	driver.findElement(flightsButton).click();
    	Helpers helper = new Helpers();
		helper.sleepSeconds(4);
    }
    
	public void assertPage() {
		 Assert.assertTrue(driver.findElement(titleText).getText().contains("Flight Details"));
	}
	public void selectPassengers(int cant) {
		long duracion = 10;
		WebDriverWait wait= new WebDriverWait(driver, duracion);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPasajeros = new Select(cantidadPasajeros);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	public void selectFromPort(int index) {
		Select selectFrom= new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
}
