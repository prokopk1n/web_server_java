// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BuyTicket {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void buyTicket() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 999));
        driver.findElement(By.linkText("Весы")).click();
        driver.findElement(By.linkText("2021-03-21T19:15")).click();
        driver.findElement(By.linkText("Билеты")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) li:nth-child(2) button")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) li:nth-child(3)")).getText(), is("1 3 2500.0\nКупить"));
        driver.findElement(By.linkText("Изменить билеты")).click();
        driver.findElement(By.cssSelector("form:nth-child(6) #cost")).click();
        driver.findElement(By.cssSelector("form:nth-child(6) #cost")).sendKeys("2500");
        driver.findElement(By.cssSelector("form:nth-child(6) button")).click();
        driver.findElement(By.linkText("Спектакли")).click();
        driver.findElement(By.linkText("Весы")).click();
        driver.findElement(By.linkText("2021-03-21T19:15")).click();
        driver.findElement(By.linkText("Билеты")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) ul")).getText(), is("Ряд Место Стоимость\\\\n1 2 2500.0\\\\nКупить\\\\n1 3 2500.0\\\\nКупить\\\\n2 1 2100.0\\\\nКупить\\\\n2 2 2100.0\\\\nКупить\\\\n2 3 2100.0\\\\nКупить\\\\n1 1 2500.0\\\\nКупить"));
    }
}

