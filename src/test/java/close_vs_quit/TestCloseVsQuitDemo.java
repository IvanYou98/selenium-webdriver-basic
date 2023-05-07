package close_vs_quit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/*
driver. quit() : The quit() method quits the driver, closing every associated window.
driver. close() : The close() method closes the currently focused window,
 */
public class TestCloseVsQuitDemo {
    private WebDriver driver = null;
    private final String[] websites = {
            "https://www.google.com",
            "https://www.selenium.dev/",
            "https://developer.mozilla.org/en-US/"};

    // set up the webdriver
    @BeforeClass
    public void setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // quit() will shut down the entire browser
    @Test
    public void testQuitWhenMultipleTabs() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.open(arguments[0])", websites[i]);
        }
        Thread.sleep(10000);
        driver.quit();
    }

    // close() will only close the current tab when there are multiple tabs
    @Test
    public void testCloseWhenMultipleTabs() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.open(arguments[0])", websites[i]);
        }
        Thread.sleep(10000);
        driver.close();
    }
}
