package locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestLocators {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://selectorshub.com/xpath-practice-page/";

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.navigate().to(URL);
    }

    @AfterClass
    public void tearDownDriver() {
        this.driver.quit();
    }

    @Test(priority = 0, enabled = false)
    public void testSelectById() {
        driver.findElement(By.id("userId")).sendKeys("example@test.com");
        driver.findElement(By.id("pass")).sendKeys("test123");
    }

    @Test(priority = 1, enabled = false)
    public void testSelectByName() {
        driver.findElement(By.name("company")).sendKeys("test company");
        driver.findElement(By.name("mobile number")).sendKeys("123123123");
    }

    @Test(priority = 2)
    public void testSelectByLinkTextAndSwitchBack() {
        WebElement downLoadLink = driver.findElement(By.linkText("DownLoad Link"));
        String prevWindow = driver.getWindowHandle();
        downLoadLink.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//        driver.switchTo().window(prevWindow);
        System.out.println();
    }

    @Test(priority = 3)
    public void testCssSelector() {
        WebElement checkoutBtn = driver.findElement(By.cssSelector("button.dropbtn"));
        Actions actions = new Actions(driver);
        actions.moveToElement(checkoutBtn)
                .build()
                .perform();
        System.out.println();
    }

    @Test(priority = 4)
    public void testXPath() {
        driver.findElement(By.xpath("//input[@value = 'Submit']"))
                .click();
        System.out.println();
    }


}
