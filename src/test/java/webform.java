import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class webform {
    WebDriver driver;
    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Fill the webform and assert the expected message")
    @Test
    public void fillWebForm() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();


        driver.findElement(By.id("edit-name")).sendKeys("Fariha Rahman");
        driver.findElement(By.id("edit-number")).sendKeys("01883766247");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();

        driver.findElement(By.id("edit-date")).click();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        driver.findElement(By.id("edit-date")).sendKeys(currentDate, Keys.ENTER);

        driver.findElement(By.id("edit-email")).sendKeys("jucse28.345@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a fourth-year CSE student at Jahangirnagar University, currently enrolled in an SQA course on my way to becoming an SDET.");

        WebElement uploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        //uploadElement.sendKeys("C:\\Users\\Asus\\Desktop\\Logical_design.pdf");
        File file = new File(".\\src\\test\\resources\\Logical_design.pdf");
        uploadElement.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        driver.switchTo().alert().accept();

        String actual_msg = driver.findElement(By.className("page-title")).getText();
        String expected_msg = "Thank you for your submission!";
        Assertions.assertEquals(actual_msg,expected_msg);

    }
    @AfterAll
    public void closeDriver() {
        driver.quit();

    }
}
