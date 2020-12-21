import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class SimpleTest {


    @Test
    public void Test01 () {
        RemoteWebDriver driver=null;
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("browserName", "chrome");
        dc.setCapability("browserVersion", "87.0");
        HashMap<String, Object> map = new HashMap<>();
        map.put("enableVNC", true);
        map.put("enableVideo", true);
        dc.setCapability("selenoid:options", map);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.3.7:4444/wd/hub"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

        Wait wait = new WebDriverWait(driver,10, 200).withMessage("ExpectedConditions timeout.");

        driver.get("http://google.com");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@type='text']")));
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("astoria software");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/div[2]/div[2]/center[1]/input[1]")));
        driver.findElement(By.xpath("//body[1]/div[2]/div[2]/form[1]/div[2]/div[1]/div[2]/div[2]/div[2]/center[1]/input[1]")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'XML Content Management')]")));
        driver.quit();

    }

}
