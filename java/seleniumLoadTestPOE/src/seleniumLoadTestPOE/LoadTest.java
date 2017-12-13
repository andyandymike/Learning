package seleniumLoadTestPOE;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class LoadTest {

	private WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\i067382\\Downloads\\apache-jmeter-3.2\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		By BySearchButtonCss = By.cssSelector("#su");
		By BySearchInput = By.cssSelector("#kw"); 
		
		driver.navigate().to("http://www.baidu.com");
		WebDriverWait wait = new WebDriverWait(driver, 30000L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(BySearchButtonCss));
		WebElement SearchInput = driver.findElement(BySearchInput);
		WebElement searchButton = driver.findElement(BySearchButtonCss);
		SearchInput.clear();
		SearchInput.sendKeys("hello world");
		searchButton.click();
	}

}
