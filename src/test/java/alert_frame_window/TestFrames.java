package alert_frame_window;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestFrames {
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
    public void testSwitchFrame() {
        driver.navigate().to("http://webdriveruniversity.com/IFrame/index.html");
        driver.switchTo().frame(0);
        driver.findElement(By.id("button-find-out-more")).click();
        driver.switchTo().defaultContent();
    }
}
