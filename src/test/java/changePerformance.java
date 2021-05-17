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
public class changePerformance {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void changePerformance() {
        driver.get("http://localhost:8080/");
        driver.manage().window().setSize(new Dimension(1920, 999));
        driver.findElement(By.linkText("Весы")).click();
        driver.findElement(By.linkText("Изменить спектакль")).click();
        driver.findElement(By.cssSelector("form li:nth-child(5)")).click();
        driver.findElement(By.id("description")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("description")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("description")).sendKeys("Новый текст");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("name")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("name")).sendKeys("Весы123");
        {
            WebElement dropdown = driver.findElement(By.id("member"));
            dropdown.findElement(By.xpath("//option[. = 'Лян Ли']")).click();
        }
        {
            WebElement element = driver.findElement(By.id("member"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = driver.findElement(By.id("member"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.id("member"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).release().perform();
        }
        driver.findElement(By.id("member")).click();
        driver.findElement(By.id("role")).click();
        driver.findElement(By.id("role")).sendKeys("АКТОР");
        driver.findElement(By.cssSelector("li:nth-child(10) > button")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText(), is("Весы123"));
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText(), is("Новый текст"));
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).getText(), is("Лян Ли - АКТОР\nЕвгений Валерьевич Гришковец - Автор и режиссёр\nАртём Алексеевич Соколов - Артём\nАртём Сергеевич Волобуев - Эдуард"));

        driver.findElement(By.linkText("Изменить спектакль")).click();
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("description")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("description")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("description")).sendKeys("Сцена Московского Художественного театра стала очередной площадкой для произведений Гришковца. Новый авторский спектакль «Весы» включает немалое количество персонажей, \n	 исполнение которых возложено на мастеров актерского искусства: Игоря Золотовицкого, Веру Харыбину, Валерия Трошина, Сергея Угрюмова, Артёма Соколова и других не менее известных актёров.\n	 В программе о спектакле «Весы» написано, что данная постановка – это «ночь в одном действии», а москвичи уже вовсю заказывают билеты!\n	 Немудрено, ведь все спектакли Гришковца проходят при абсолютных аншлагах.");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("name")).sendKeys(Keys.DELETE);
        driver.findElement(By.id("name")).sendKeys("Весы");
        driver.findElement(By.cssSelector("ul:nth-child(1) > li:nth-child(1) > button")).click();
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("description")).click();
        {
            WebElement element = driver.findElement(By.id("description"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("description")).sendKeys("Сцена Московского Художественного театра стала очередной площадкой для произведений Гришковца. Новый авторский спектакль «Весы» включает немалое количество персонажей, \n	 исполнение которых возложено на мастеров актерского искусства: Игоря Золотовицкого, Веру Харыбину, Валерия Трошина, Сергея Угрюмова, Артёма Соколова и других не менее известных актёров.\n	 В программе о спектакле «Весы» написано, что данная постановка – это «ночь в одном действии», а москвичи уже вовсю заказывают билеты!\n	 Немудрено, ведь все спектакли Гришковца проходят при абсолютных аншлагах.");
        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).sendKeys("Весы");
        driver.findElement(By.cssSelector("li:nth-child(10) > button")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText(), is("Весы"));
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText(), is("Сцена Московского Художественного театра стала очередной площадкой для произведений Гришковца. Новый авторский спектакль «Весы» включает немалое количество персонажей, исполнение которых возложено на мастеров актерского искусства: Игоря Золотовицкого, Веру Харыбину, Валерия Трошина, Сергея Угрюмова, Артёма Соколова и других не менее известных актёров. В программе о спектакле «Весы» написано, что данная постановка – это «ночь в одном действии», а москвичи уже вовсю заказывают билеты! Немудрено, ведь все спектакли Гришковца проходят при абсолютных аншлагах."));
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) ul:nth-child(1) > li")).getText(), is("Евгений Валерьевич Гришковец - Автор и режиссёр"));
    */}
}

