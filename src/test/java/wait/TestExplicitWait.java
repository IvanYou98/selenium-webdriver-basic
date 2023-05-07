package wait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestExplicitWait {
    private WebDriver driver;
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/wait/explicit_wait_demo.html");
        driver.findElement(By.id("btn")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testWithoutExplicitWait() {
        driver.findElement(By.id("msg"));
    }

    @Test
    public void testWithExplicitWait() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("msg")));
        String msg = driver.findElement(By.id("msg")).getText();
        Assert.assertEquals(msg, "Hello World!");
//        System.out.println(msg);

    }

    @Test
    public void testWithFluentWait() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        String msg = wait.until(driver -> driver.findElement(By.id("msg")))
                .getText();
        Assert.assertEquals(msg, "Hello World!");
    }

}
