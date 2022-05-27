package testcase;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.AccountInfoPage;
import pages.LoginPage;
import pages.RegistrationPage;
import reports.ExtentTestManager;
import utils.ExcelReader;
import utils.Log;
import utils.PropertyReader;

import java.io.IOException;
import java.util.Map;

public class AccountInfoTest extends BaseTest {
    ExcelReader excelReader = new ExcelReader("Login");

    {
        try {
            Map<String, String> testdata = excelReader.getExcelData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountInfoPage accountInfoPage;

    @BeforeTest
    public void loadClass() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        accountInfoPage = PageFactory.initElements(driver, AccountInfoPage.class);
    }

    @BeforeMethod
    public void login(ITestContext context) throws IOException {
        try {
            Map<String, String> testdata = excelReader.getExcelData();
            registrationPage.clickAccount();
            loginPage.clickLoginLink();
            ISuite suite = context.getSuite();
            String email = (String) suite.getAttribute("emailId");
            System.out.println("Email id is :" + email);
            loginPage.enterEmail(email);
            registrationPage.enterPassword(testdata.get("password"));
            loginPage.clickLoginButton();
            loginPage.verifyLogin();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Cant do login");
        }
    }

    @Test
    public void accountTest_01() {
        try {
            Map<String, String> testdata = excelReader.getExcelData();
            accountInfoPage.clickAccountInfo();
            accountInfoPage.verifyAccountInfoPage(new PropertyReader().readProperty("edit_accountinfo"));
            accountInfoPage.enterCurrentPwd(testdata.get("password"));
            registrationPage.verifySuccessRegistration(new PropertyReader().readProperty("success_editAccountInfo"));
        } catch (Exception e) {
            ExtentTestManager.getTest().fail(e.getMessage());
            e.printStackTrace();
            Assert.fail();
        } finally {
            ExtentTestManager.getTest().assignCategory("Dashboard");
        }
    }

}
