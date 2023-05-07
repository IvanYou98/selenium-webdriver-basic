package wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestImplicitWait {
    private WebDriver driver;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/wait/implicit_wait_demo.html");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testWithoutImplicitWait() {
        System.out.println(driver.findElement(By.id("msg1")).getText());
        System.out.println(driver.findElement(By.id("msg2")).getText());
    }

    @Test
    public void testImplicitWait() {

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        testWithoutImplicitWait();
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
    }
}
