import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AshotTest {
    WebDriver driver;
    DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
    String logDate = dateFormat.format(Calendar.getInstance().getTime());

    @BeforeEach//metoda bedzie wyk przed kazdym testem
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void demoTest1() throws InterruptedException, IOException {
        driver.get("https://www.nasa.gov/");
        Thread.sleep(2000);

//        WebElement webElement = driver.findElement(By.className("dropdown-toggle"));
        WebElement webElement = driver.findElement(By.className("ember-application"));
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "/Images/BodyImageWrong.png"));

        Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, webElement);
//        ImageIO.write(logoImageScreenshot.getImage(),"PNG", new File(System.getProperty("user.dir")+"/Images/BodyImage.png"));
        BufferedImage actualImage = logoImageScreenshot.getImage();
//
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

        if (diff.hasDiff()) {
            ImageIO.write(diff.getTransparentMarkedImage(), "PNG", new File(System.getProperty("user.dir") + "/ImagesDiffs/BodyDiff_" + logDate + ".png"));
        }
        Assertions.assertFalse(diff.hasDiff());

//        Thread.sleep(2000);
    }

//    @Test
//    public void demoTest2() {
//        driver.navigate().to("https://www.nasa.gov/");
//
//    }
}
