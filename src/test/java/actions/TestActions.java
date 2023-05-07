package actions;

import base.Base;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActions extends Base {
    private static final String BASE_URL = "http://www.webdriveruniversity.com/Actions/index.html";

    @BeforeClass
    public void goToPage() {
        driver.navigate().to(BASE_URL);
    }

    @Test
    public void testDoubleClick() {
        WebElement doubleClickArea = driver.findElement(By.id("double-click"));
        Actions actions = new Actions(driver);
//        actions.doubleClick(doubleClickArea).build().perform();
        doubleClickArea.click();
        doubleClickArea.click();
        String bgColor = doubleClickArea.getCssValue("background-color");
        System.out.println(bgColor);
        Assert.assertEquals(bgColor, "rgba(147, 203, 90, 1)");
    }

    @Test
    public void testClickAndHold() {
        WebElement clickBox = driver.findElement(By.id("click-box"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(clickBox).build().perform();
        Assert.assertEquals(clickBox.getText(), "Well done! keep holding that click now.....");
    }

    @Test(dependsOnMethods = "testClickAndHold")
    public void testRelease() {
        WebElement clickBox = driver.findElement(By.id("click-box"));
        new Actions(driver).release(clickBox)
                .build()
                .perform();
        Assert.assertEquals(clickBox.getText(), "Dont release me!!!");
    }


    @Test
    public void testHover() {
        WebElement firstBtn = driver.findElement(By.xpath("//button[@class = 'dropbtn'][1]"));
        System.out.println(firstBtn.getCssValue("color"));
        Actions actions = new Actions(driver);
        actions.moveToElement(firstBtn)
                .build()
                .perform();
        System.out.println(firstBtn.getCssValue("color"));
    }

    @Test
    public void testRightClick() {
        driver.navigate().to("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClickArea = driver.findElement(By.xpath("//span[contains(text(), 'right click')]"));
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickArea)
                .click(driver.findElement(By.xpath("//span[contains(text(), 'Delete')]")))
                .build()
                .perform();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "clicked: delete");
        alert.accept();
    }

    @Test
    public void testDragAndDrop() {
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        new Actions(driver)
                .dragAndDrop(source, target)
                .build()
                .perform();
        System.out.println();

        WebElement dropArea = driver.findElement(By.xpath("//*[@id = 'droppable']/p"));
        Assert.assertEquals(dropArea.getCssValue("background-color"), "rgba(244, 89, 80, 1)");
    }

    @Test
    public void testDragAndDropBySlider() {
        driver.navigate().to("https://jqueryui.com/slider/");
        WebElement frameArea = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(frameArea);
        WebElement handler = driver.findElement(By.xpath("//div[@id = 'slider']/span"));
        new Actions(driver)
                .dragAndDropBy(handler, 300, 0)
                .build()
                .perform();
        driver.switchTo().defaultContent();
        System.out.println();
    }

    @Test
    public void testDragAndDropByResizable() {
        driver.navigate().to("https://jqueryui.com/resizable/#default");
        driver.switchTo().frame(0);
        WebElement resizableArea = driver.findElement(By.id("resizable"));
        System.out.println("Height: " + resizableArea.getSize().getHeight());
        System.out.println("Width: " + resizableArea.getSize().getWidth());
        WebElement resizableHandle = driver.findElement(By.xpath("//*[contains(@class, 'ui-resizable-handle')][3]"));
        new Actions(driver)
                .dragAndDropBy(resizableHandle, 100, 100)
                .build()
                .perform();
        System.out.println("Height: " + resizableArea.getSize().getHeight());
        System.out.println("Width: " + resizableArea.getSize().getWidth());
    }
}
