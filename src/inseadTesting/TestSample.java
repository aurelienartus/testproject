package inseadTesting;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSample {

  private WebDriver driver = null;
  
  // --------------------------------------------------------------------------------------------------------
  // Create a browser before every test method
  @Parameters({ "server-host", "server-port", "browser-name", "browser-version", "operating-system" })
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod(String serverHost, String serverPort, String browserName, String browserVersion, String operatingSystem) {

	  try {
		  // Construct the string used to connect to the remote web driver (servername+port)
		  String connectionString = "http://" + serverHost + ":" + serverPort + "/wd/hub";
		  DesiredCapabilities capability = new DesiredCapabilities(browserName, browserVersion, Platform.extractFromSysProperty(operatingSystem));
		  driver = new RemoteWebDriver(new URL(connectionString), capability);
	  } catch (Exception e) {
		  System.out.println(e);
		  throw new IllegalStateException("Can't start web driver", e);
	  }
  }

  // --------------------------------------------------------------------------------------------------------
  // Destroy the browser after every test method
  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
	  driver.close();
	  driver.quit();
	  driver = null;
  }
  
  // --------------------------------------------------------------------------------------------------------
  // Update an active Primary Name in Peoplesoft
  @Test
  public void actualTestCode() throws InterruptedException
  {
      System.out.println("Hello World");
      
      driver.get("https://www.google.com.sg/");
      
      WebElement searchBox = driver.findElement(By.id("lst-ib"));
      
      searchBox.sendKeys("INSEAD");
      
      WebElement searchButton = driver.findElement(By.name("btnG"));
      
      searchButton.click();
      
      WebDriverWait wait = new WebDriverWait(driver, 15);
      
      wait.until(ExpectedConditions.titleIs("INSEAD - Google Search"));
      
      WebElement searchResult = driver.findElement(By.partialLinkText("The Business School for the World | INSEAD"));
      
      Assert.assertNotNull(searchResult);
      
      System.out.println("Test successfull");
      

  }
  
}
