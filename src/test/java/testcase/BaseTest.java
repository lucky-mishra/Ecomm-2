package testcase;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.BrowserFactory;
import utils.DriverFactory;
import utils.PropertyReader;
import utils.Utility;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public BrowserFactory browserFactory;
    String browserName = null;
    static ExtentReports extent = ExtentManager.getInstance();

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    public void setUp() {
        browserName = new PropertyReader().readProperty("browser");
        browserFactory = new BrowserFactory();
        DriverFactory.getInstance().setDriver(browserFactory.initBrowser(browserName));
        //driver = browserFactory.initBrowser(browserName);
        driver = DriverFactory.getInstance().getDriver();
        String host = new PropertyReader().readProperty("host");
        driver.get(host);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //@AfterMethod
    public String takeScreenshot(ITestResult testResult) {
        String temp = null;
        if (ITestResult.FAILURE == testResult.getStatus()) {
            temp = Utility.takeScreenshot(driver, testResult.getName());
            System.out.println("path is :" + temp);
        }
        return temp;
    }

    @AfterMethod
    public void assignDevice() {
        ExtentTestManager.getTest().assignDevice(browserName);
    }

    @AfterMethod
    public void assignAuthor() {
        ExtentTestManager.getTest().assignAuthor("Mayank Mishra");

    }

    @AfterTest
    public void tearDown() {
        DriverFactory.getInstance().closeBrowser();
        //driver.quit();
    }

}
