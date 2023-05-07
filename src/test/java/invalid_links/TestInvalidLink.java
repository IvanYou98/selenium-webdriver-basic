package invalid_links;

import base.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestInvalidLink extends Base {

    @Test
    public void testFindInvalidLinks() {
        driver.navigate().to("file://" + System.getProperty("user.dir") + "/src/test/java/invalid_links/test-links.html");
        driver.findElements(By.tagName("a")).forEach(element -> {
            try {
                HttpURLConnection huc = (HttpURLConnection)new URL(element.getAttribute("href")).openConnection();
                // only the head will be returned
                huc.setRequestMethod("HEAD");
                huc.connect();
                if (huc.getResponseCode() >= 400) {
                    System.out.println(element.getAttribute("href") + " is broken");
                }
            } catch (IOException e) {
                System.out.println(element.getAttribute("href") + " is broken");
            }
        });
    }
}
