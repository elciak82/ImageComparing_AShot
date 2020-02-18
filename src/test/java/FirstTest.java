import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    WebDriver driver;

    @BeforeEach//metoda bedzie wyk przed kazdym testem
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(500, 600));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void demoTest1() {
        driver.get("https://www.nasa.gov/");

    }

    @Test
    public void demoTest2() {
        driver.navigate().to("https://www.nasa.gov/");

    }
}
