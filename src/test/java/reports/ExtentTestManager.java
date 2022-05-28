package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    //static ExtentReports extent = ExtentManager.getInstance();
    static ExtentReports extent;

    static {
        try {
            extent = ExtentReportNG.setupExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        //extent.addTestRunnerOutput("logs");
        extentTestMap.put((int)  (Thread.currentThread().getId()), test);
        return test;
    }
    public static void addTestRunner(String message) {
        extent.addTestRunnerOutput(message);
    }

}