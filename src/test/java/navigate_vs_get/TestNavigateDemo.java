package navigate_vs_get;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNavigateDemo {
    private WebDriver driver;
    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
        this.driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDownDriver() {
        this.driver.quit();
    }

    @Test
    public void testNavigate() throws InterruptedException {
        driver.navigate().to("https://www.google.com");
        driver.navigate().to("https://www.apache.org");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }
    @Test
    public void testGet() throws InterruptedException {
        driver.get("https://www.google.com");
    }

}
