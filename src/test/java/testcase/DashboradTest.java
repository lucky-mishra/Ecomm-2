package testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RegistrationPage;
import reports.ExtentTestManager;
import utils.Asserssion;
import utils.ExcelReader;
import utils.PropertyReader;

import java.io.IOException;
import java.util.Map;

public class DashboradTest extends BaseTest {
    DashboardPage dashboardPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    Asserssion asserssion;
    boolean isSubscribe = false;
    ExcelReader excelReader = new ExcelReader("Login");
    //ExcelReader excelReader1 = new ExcelReader("Registration");


    @BeforeTest
    public void loadClass() {
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        asserssion = PageFactory.initElements(driver, Asserssion.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    }

    @BeforeMethod
    public void login(ITestContext context) throws IOException {
        try {
            Map<String, String> testdata = excelReader.getExcelData();
            registrationPage.clickAccount();
            loginPage.clickLoginLink();
            ISuite suite = context.getSuite();
            String email = (String) suite.getAttribute("emailId");
            isSubscribe = (boolean) suite.getAttribute("isSubscribe");
            System.out.println("Email id is :" + email);
            loginPage.enterEmail(email);
            registrationPage.enterPassword(testdata.get("password"));
            loginPage.clickLoginButton();
            //loginPage.verifyLogin();
            registrationPage.clickAccount();
            registrationPage.clickMyAccount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Cant do login");
        }
    }

    @Test
    public void dashboardTest_01() throws IOException {
        try {
            int leftLinkCount = dashboardPage.getLeftLinks();
            asserssion.isCountSame(leftLinkCount, 11);
            registrationPage.clickAccount();
            registrationPage.logout();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cant verify subscrition");
        }
    }

    @Test
    public void dashboardTest_02() throws IOException {
        try {
            String fullNameUI = dashboardPage.getName();
            registrationPage.clickEdit();
            String fullName = registrationPage.getFullName();
            asserssion.isTextSame(fullName, fullNameUI);
            registrationPage.clickAccount();
            registrationPage.logout();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cant verify subscrition");
        }
    }

    //Verify if subscrition is done.
    @Test
    public void dashboardTest_03() throws IOException {
        try {
            dashboardPage.verifySubsMessage(new PropertyReader().readProperty("true_subsription"));
            dashboardPage.clickEdit();
            boolean isSubEdit = dashboardPage.isSubscrition();
            asserssion.isReturnSame(isSubscribe, isSubEdit);
            registrationPage.clickAccount();
            registrationPage.logout();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Cant verify subscrition");
        } finally {
            ExtentTestManager.getTest().assignCategory("Dashboard");
        }
    }

}
