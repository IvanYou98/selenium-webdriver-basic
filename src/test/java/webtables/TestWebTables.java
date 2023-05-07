package webtables;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestWebTables {
    private WebDriver driver;
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL);
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void login() {
        driver.findElement(By.xpath("//input[@name = 'username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("admin123");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    @Test(dependsOnMethods = "login")
    public void countActiveAdmin() {
        // click the admin page in the sidebar
        driver.findElement(By.partialLinkText("Admin")).click();
        // count the number of active admins in the table
        long count = driver.findElements(By.xpath("//div[@class = \"oxd-table\"]//div[@class = 'oxd-table-card']"))
                .stream()
                .filter(row -> row.findElement(By.xpath(".//div[@role = 'cell'][last() - 1]/div")).getText().equals("Enabled") &&
                        row.findElement(By.xpath(".//div[@role = 'cell'][3]/div")).getText().equals("Admin"))
                .count();
        System.out.println(count);
    }
}
