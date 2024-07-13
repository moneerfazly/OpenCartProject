package testBase;

import Utilities.ExtentReportManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;


public class BaseTestClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setUp(String os, String br) throws IOException {

        logger= LogManager.getLogger(this.getClass());// log4j2 code

        FileReader file=new FileReader("./src//test//resources//config.properties");
        p=new Properties();
        p.load(file);

        //OS
        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities capabilities=new DesiredCapabilities();

            if(os.equalsIgnoreCase("MacBook Pro")){
                capabilities.setPlatform(Platform.MAC);
            }else if(os.equalsIgnoreCase("Linux")){
                capabilities.setPlatform(Platform.LINUX);
            }else if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else{
                System.out.println("No matching os");
                return;
            }
            //browser
            switch (br.toLowerCase()){
                case "chrome": capabilities.setBrowserName("chrome");break;
                case "firefox": capabilities.setBrowserName("firefox");break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
                default:
                    System.out.println("No matching browser...");return;
            }
            driver=new RemoteWebDriver(new URL("http://10.0.0.158:4444"),capabilities);
        }


        if(p.getProperty("execution_env").equalsIgnoreCase("local")){

            //browser
            switch (br.toLowerCase()){
                case "chrome": driver=new ChromeDriver();break;
                case "safari": driver=new SafariDriver();break;
                case "firefox": driver=new FirefoxDriver();break;
                case "edge": driver=new EdgeDriver(); break;
                default: System.out.println("Invalid browser..."); return;
            }
        }


        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("url"));//reading url from properties file
        driver.manage().window().maximize();
    }


    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(6);
        return generatedString;
    }

    public String randomNumber() {
        String generatedNumber = RandomStringUtils.randomAlphanumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumber() {
        String generatedNumber = RandomStringUtils.randomAlphanumeric(3);
        String generatedString = RandomStringUtils.randomAlphabetic(3);
        return (generatedNumber+"@"+generatedString);

    }
    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = "/Users/mfazly/Documents/SDET_Projects/OpenCartProject/src/test/resources/ScreenShots" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);


        sourceFile.renameTo(targetFile);
        return targetFilePath;

    }
    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        driver.quit();
    }

    }

