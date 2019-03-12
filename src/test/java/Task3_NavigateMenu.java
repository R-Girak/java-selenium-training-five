import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Task3_NavigateMenu {
    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @After
    public void test_cleanup() {
        driver.quit();
    }

    @Test
    public void menuNavigationTest() {
        // Log in
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By menuBlock = By.cssSelector("#sidebar ul#box-apps-menu");
        wait.until(ExpectedConditions.elementToBeClickable(menuBlock));

        // Checking h1
        int menuSize = driver.findElement(menuBlock).findElements(By.xpath("./li")).size();

        for (int i = 1; i <= menuSize; i++) {
            driver.findElement(menuBlock).findElement(By.xpath("./li[" + i + "]")).click();
            Assert.assertTrue("No h1 for " + i + "th item", isElementPresent(By.cssSelector("h1")));

            int subMenuSize = driver.findElement(menuBlock).findElement(By.cssSelector("li.selected")).findElements(By.cssSelector("li")).size();

            if (subMenuSize > 0) {
                for (int j = 1; j <= subMenuSize; j++) {
                    driver.findElement(menuBlock).findElement(By.cssSelector("li.selected")).findElement(By.cssSelector("li:nth-of-type(" + j + ")")).click();
                    Assert.assertTrue("No h1 for " + i + "-" + j + "th item", isElementPresent(By.cssSelector("h1")));
                }
            }
        }
    }

    private boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
