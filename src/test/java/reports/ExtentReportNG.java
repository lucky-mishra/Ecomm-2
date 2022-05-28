package reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import utils.PropertyReader;


/**
 * @author: Prakash Narkhede
 * @Youtube: https://www.youtube.com/automationtalks
 * @LinkedIn: https://www.linkedin.com/in/panarkhede89/
 */
public class ExtentReportNG {

    static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report"+".html";

    public static ExtentReports setupExtentReport() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String reportPath = System.getProperty("user.dir") +
                "/Reports/ExecutionReport_" + actualDate + ".html";

        ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath)
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{
                        ViewName.DASHBOARD,
                        ViewName.TEST,
                        ViewName.CATEGORY,
                        ViewName.AUTHOR,
                        ViewName.DEVICE,
                        ViewName.EXCEPTION,
                        ViewName.LOG
                })
                .apply();

        extent = new ExtentReports();
        extent.attachReporter(sparkReport);

        sparkReport.config().setDocumentTitle(reportFileName);
        sparkReport.config().setTheme(Theme.STANDARD);
        sparkReport.config().setReportName(reportFileName);
        sparkReport.config().setTimelineEnabled(true);
        sparkReport.config().setEncoding("utf-8");
        sparkReport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReport.config().thumbnailForBase64(true);

        extent.setSystemInfo("Executed on Environment: ", new PropertyReader().readProperty("host"));
        extent.setSystemInfo("Executed on Browser: ", new PropertyReader().readProperty("browser"));
        extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("ENV", "QA");

        return extent;
    }


}
