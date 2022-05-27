package utils;

import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.testng.ITestListener;
import reports.ExtentTestManager;

public class Log implements ITestListener {

    public Log() {  }

    // Initialize Log4j logs
    private static Logger  Log = Logger.getLogger(Log.class.getName());

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName) {
        //Log.info("****************************************************************");
        //Log.info("$$$$$$$$$$$$$$$$$$$$$ " + sTestCaseName + "  $$$$$$$$$$$$$$$$$$$$$$$$$");
        //Log.info("******************************************************************");
    }

    public static void endTestCase(String sTestCaseName) {
        //Log.info("XXXXXXXXXXXXXXXX " + "-E---N---D-" + "   XXXXXXXXXXXXXX");
    }


    // Need to create these methods, so that they can be called

    public static void info(String message) {
        Log.info(message);
        ExtentTestManager.getTest().log(Status.INFO, message);
        ExtentTestManager.addTestRunner(message);
    }
    public static void warn(String message) {
        Log.warn(message);
        ExtentTestManager.getTest().log(Status.WARNING, message);
    }
    public static void fail(String message) {
        Log.error(message);
        ExtentTestManager.getTest().log(Status.FAIL, message);
    }

}
