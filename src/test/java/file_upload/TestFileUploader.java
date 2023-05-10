package file_upload;

import base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class TestFileUploader extends Base {

    @Test
    public void testFileUploader() {
        driver.navigate().to("http://www.webdriveruniversity.com/File-Upload/index.html");
        driver.findElement(By.id("myFile")).sendKeys(System.getProperty("user.dir") + "/src/test/java/file_upload/sample.txt");
        driver.findElement(By.id("submit-button")).click();
        System.out.println();
    }
}
