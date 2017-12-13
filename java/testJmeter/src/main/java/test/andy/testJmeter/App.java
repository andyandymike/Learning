package test.andy.testJmeter;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App extends AbstractJavaSamplerClient {
	private WebDriver driver;

    @Override
    public void setupTest(JavaSamplerContext context) {
    	System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\i067382\\Downloads\\apache-jmeter-3.2\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
    	driver.quit();
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument("test_p1", "test");
        return defaultParameters;
    }
    
    public SampleResult runTest(JavaSamplerContext arg0) {
    	String test_p1 = arg0.getParameter("test_p1");
        SampleResult result = new SampleResult();
        boolean success = true;
        result.sampleStart();
        
        By BySearchButtonCss = By.cssSelector("#su");
		By BySearchInput = By.cssSelector("#kw"); 
		
		driver.navigate().to("http://www.baidu.com");
		WebDriverWait wait = new WebDriverWait(driver, 30000L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(BySearchButtonCss));
		WebElement SearchInput = driver.findElement(BySearchInput);
		SearchInput.findElement(BySearchButtonCss);
		WebElement searchButton = driver.findElement(BySearchButtonCss);
		SearchInput.clear();
		SearchInput.sendKeys("hello world");
		searchButton.click();
		Actions act = new Actions(driver);
		EventFiringWebDriver ewd = new EventFiringWebDriver(driver);
		try {
			FileReader fr = new FileReader("c:\temp");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		act.moveToElement(SearchInput).perform();
		try {
			Robot robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        result.sampleEnd();
        result.setSuccessful(success);
        return result;
	}
}

