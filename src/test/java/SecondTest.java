import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class SecondTest {
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
    public void demoTest1() throws InterruptedException {
        //Variables
        WebDriver driver1 = new ChromeDriver();
        WebDriver driver2 = new ChromeDriver();
        String imgOriginal = "c:\\screens\\Original.jpg";
        String imgToCompare = "c:\\screens\\Altered.jpg";
        String DiffImage = "c:\\screens\\DiffImage.jpg";


        driver1.get("http://toolsqa.com");
        driver1.manage().window().maximize();
        Thread.sleep(3000);
//Take original screenshot of a web page
        File scrFile1 = ((TakesScreenshot)driver1).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile1, new File(imgOriginal));

        driver2.get("http://toolsqa.com");
        driver2.manage().window().maximize();
        Thread.sleep(3000);

//Edit the web element color to validate the difference
        WebElement elm = driver2.findElement(By.xpath("//*[@id='primary-menu']/li[1]/a/span[1]/span/span"));
        JavascriptExecutor jse = (JavascriptExecutor)driver2;
        jse.executeScript("arguments[0].setAttribute('style','background: black;');", elm);
//Take the edited screenshot of a web page
        File scrFile2 = ((TakesScreenshot)driver2).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile2, new File(imgToCompare));

//Compare the two images captured above.
//ImageComparison(pixelPerBlockX,pixelPerBlockY,Threshold)
//        ImageComparison imageComparison = new ImageComparison(20,20,0.15);
//        if(imageComparison.fuzzyEqual(imgOriginal,imgToCompare,DiffImage))
//            System.out.println("Images are Similar.");
//        else
//            System.out.println("Images are not Similar.");

    }

    @Test
    public void demoTest2() {
        driver.navigate().to("https://www.nasa.gov/");

    }
}
