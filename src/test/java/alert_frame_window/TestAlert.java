package alert_frame_window;

import base.Base;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestAlert extends Base {
    private static final String BASE_URL = "http://www.webdriveruniversity.com/Popup-Alerts/index.html";

    @BeforeClass
    public void visitWebPage() {
        driver.navigate().to(BASE_URL);
    }

    @Test
    public void testTextAlert() {
        driver.findElement(By.id("button1")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am an alert box!");
        alert.accept();
    }

    @Test
    public void testModalPopup() {
        driver.findElement(By.id("button2")).click();
        WebElement modal = driver.findElement(By.xpath("//div[@class = 'modal-content']"));
        WebElement element = modal.findElement(By.xpath(".//h4[@class = 'modal-title']"));
        System.out.println(element.getText());
        Assert.assertEquals(element.getText(), "It’s that Easy!! Well I think it is.....");
        modal.findElement(By.xpath(".//button[text() = 'Close']")).click();
    }

    @Test
    public void testConfirmBox() {
        driver.findElement(By.xpath("//*[@id = 'button4']")).click();
        Alert alert = driver.switchTo().alert();
//        alert.accept();
//        String text = driver.findElement(By.id("confirm-alert-text")).getText();
//        Assert.assertEquals(text, "You pressed OK!");

        alert.dismiss();
        String text = driver.findElement(By.id("confirm-alert-text")).getText();
        Assert.assertEquals(text, "You pressed Cancel!");
    }

    @Test
    public void testInputBoxAndConfirm() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/alert_window/popup-demo.html");
        driver.findElement(By.id("btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Jack");
        alert.accept();
        String text = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals(text, "Hello Jack!");
        System.out.println();
    }

    @Test
    public void testInputBoxAndDismiss() {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/alert_window/popup-demo.html");
        driver.findElement(By.id("btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Jack");
        alert.dismiss();
        String text = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals(text, "Hello Jack!");
        System.out.println();
    }

}
