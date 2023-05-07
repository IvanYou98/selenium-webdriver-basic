package alert_frame_window;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestWindow {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();;
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSwitchBetweenWindows() {
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/alert_frame_window/window.html");
        String windowHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("Go to Google")).click();
        driver.switchTo().window(windowHandle);
        driver.findElement(By.linkText("Go to Selenium")).click();
        driver.switchTo().window(windowHandle);
        System.out.println();
    }
}
