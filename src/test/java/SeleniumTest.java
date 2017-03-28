import com.jayway.restassured.RestAssured;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author Andreas
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

    private WebDriver driver;

    public int GetCarsTableSize(WebDriver driver) {
        return driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr")).size();
    }

    public WebElement GetCarRow(List<WebElement> rows, String carId) {
        for (WebElement row : rows) {
            if (carId.equals(row.findElements(By.tagName("td")).get(0).getText())) {
                return row;
            }
        }
        return null;
    }

    public void WaitForPageRefresh(String elementToWatch) {
        WebElement wait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + elementToWatch + "')]")));
    }

    @Before
    public void SetUp() {
        String dir = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", dir+"\\Drivers\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
        RestAssured.given().get("http://localhost:3000/reset");
        driver = new ChromeDriver();
        driver.get("localhost:3000");
    }

    @After
    public void TearDown() {
        driver.quit();
        RestAssured.given().get("http://localhost:3000/reset");
    }

    @Test
    public void TestCars1() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                int tableSize = GetCarsTableSize(driver);
                assertThat(tableSize, is(5));
                return true;
            }
        });
    }

    @Test
    public void TestCars2() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("filter")).sendKeys("2002");
                int tableSize = GetCarsTableSize(driver);
                assertThat(tableSize, is(2));
                return true;
            }
        });
    }

    @Test
    public void TestCars3() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("filter")).sendKeys("asdads");
                int tableSize = GetCarsTableSize(driver);
                assertThat(tableSize, is(0));
                driver.findElement(By.id("filter")).clear();
                tableSize = GetCarsTableSize(driver);
                assertThat(tableSize, is(0));
                driver.findElement(By.id("filter")).sendKeys(" ");
                tableSize = GetCarsTableSize(driver);
                assertThat(tableSize, is(5));
                return true;
            }
        });
    }

    @Test
    public void TestCars4() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("h_year")).click();
                List<WebElement> rows = driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr"));
                String id1 = rows.get(0).findElements(By.tagName("td")).get(0).getText();
                assertThat(id1, is("938"));
                String id2 = rows.get(rows.size() - 1).findElements(By.tagName("td")).get(0).getText();
                assertThat(id2, is("940"));
                return true;
            }
        });
    }

    @Test
    public void TestCars5() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                List<WebElement> rows = driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr"));
                WebElement row = GetCarRow(rows, "938");
                List<WebElement> cells = row.findElements(By.tagName("td"));
                cells.get(cells.size() - 1).findElements(By.tagName("a")).get(0).click();
                WebElement descriptionField = driver.findElement(By.id("description"));
                descriptionField.clear();
                descriptionField.sendKeys("Cool car");
                driver.findElement(By.id("save")).click();
                WaitForPageRefresh("Cool car");
                assertThat(cells.get(5).getText(), is("Cool car"));
                return true;
            }
        });
    }

    @Test
    public void TestCars6() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("new")).click();
                driver.findElement(By.id("save")).click();
                String error = driver.findElement(By.id("submiterr")).getText();
                assertThat(error, is("All fields are required"));
                assertThat(GetCarsTableSize(driver), is(5));
                return true;
            }
        });
    }

    @Test
    public void TestCars7() {
        (new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                driver.findElement(By.id("new")).click();
                driver.findElement(By.id("year")).sendKeys("2008");
                driver.findElement(By.id("registered")).sendKeys("2002-5-5");
                driver.findElement(By.id("make")).sendKeys("Kia");
                driver.findElement(By.id("model")).sendKeys("Rio");
                driver.findElement(By.id("description")).sendKeys("As new");
                driver.findElement(By.id("price")).sendKeys("31000");
                driver.findElement(By.id("save")).click();
                WaitForPageRefresh("Rio");
                WebElement row = GetCarRow(driver.findElement(By.id("tbodycars")).findElements(By.tagName("tr")), "942");
                assertThat(GetCarsTableSize(driver), is(6));
                assertThat(row, notNullValue());
                return true;
            }
        });
    }
}
