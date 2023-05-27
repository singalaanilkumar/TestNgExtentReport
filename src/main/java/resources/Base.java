package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;


public class Base {

    public Properties prop = null;

    public WebDriver initializeBrowser() throws IOException {

        prop = new Properties();

        prop.load(new FileInputStream(new File("./data.properties")));

        String browserName = prop.getProperty("browser");

        WebDriver driver = null;

        if (browserName.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("ie")) {


            driver = new InternetExplorerDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {


            driver = new EdgeDriver();

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;

    }

    public String getTimeStamp() {

        Date date = new Date();

        return date.toString().replace(" ", "_").replace(":", "_");

    }

    public String takeScreenshot(String testName, WebDriver driver) throws IOException {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";

        FileHandler.copy(srcFile, new File(screenshotPath));

        return screenshotPath;

    }

}
