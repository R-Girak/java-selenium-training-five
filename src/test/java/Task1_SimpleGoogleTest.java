import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task1_SimpleGoogleTest {

    WebDriver drv;

//    @Before
//    public void googleTest_setup() {
//    }

    @After
    public void googleTest_cleanup() {
        drv.quit();
    }

    @Test
    public void googleTestChrome() {
        WebDriverManager.chromedriver().setup();
        drv = new ChromeDriver();
        drv.get("https://www.google.com/");
        drv.findElement(By.name("q")).sendKeys("Selenium");
        WebDriverWait wait = new WebDriverWait(drv, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        element.click();
        drv.findElement(By.className("TbwUpd")).click();
    }

    @Test
    public void googleTestFirefox() {
        WebDriverManager.firefoxdriver().setup();
        drv = new FirefoxDriver();
        drv.get("https://www.google.com/");
        drv.findElement(By.name("q")).sendKeys("Selenium");
        WebDriverWait wait = new WebDriverWait(drv, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
        element.click();
        drv.findElement(By.className("TbwUpd")).click();
    }

}
