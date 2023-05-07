package dropdown_checkbox_radiobutton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestDropdown {
    private WebDriver driver;
    private static final String BASE_URL = "http://www.webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDropdown() {
        Select dropDownMenu = new Select(driver.findElement(By.id("dropdowm-menu-1")));
//        dropDownMenu.selectByVisibleText("C#");
//        dropDownMenu.selectByIndex(2);
//        dropDownMenu.selectByValue("sql");
        System.out.println();
    }

    @Test
    public void testCheckbox() {
        driver.findElements(By.xpath("//div[@id = 'checkboxes']//input")).forEach(option -> {
            if (!option.isSelected()) {
//                option.click();
                option.sendKeys(Keys.SPACE);
            }
        });
    }

    @Test
    public void testRadioBtn() {
        driver.findElements(By.xpath("//form[@id = 'radio-buttons']//input")).forEach(option -> {
            if (option.getAttribute("value").equals("orange") && !option.isSelected()) {
//                option.click();
                option.sendKeys(Keys.SPACE);
            }
        });
        System.out.println();
    }

}
