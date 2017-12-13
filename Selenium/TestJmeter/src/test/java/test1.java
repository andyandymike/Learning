/**
 * Created by I067382 on 9/11/2017.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test1 {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\i067382\\Downloads\\apache-jmeter-3.2\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
}

    @Test
    public void testSelenium() throws Exception {
        driver.navigate().to("http://www.google.com");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
