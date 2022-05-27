package testcase;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import reports.ExtentTestManager;
import utils.ExcelReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ExcelReader excelReader = new ExcelReader("Login");


    @DataProvider(name = "loginData")
    public Object[][] dataSupplier() throws IOException {
        int rows = excelReader.getRowCount();
        int cols = excelReader.getColCount();
        Object[][] data = new Object[rows][1];
        for (int r = 0; r < rows; r++) {
            // Create Hashmap after every row iteration
            Map<String, String> hm = excelReader.getExcelData(r);
            System.out.println("data in hm :" + hm);
            data[r][0] = hm;
            // Add every row in hashmap
            System.out.println("data in array :" + data[r][0]);
        }
        return data;
    }

    @BeforeTest
    public void loadClass()
    {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    }

    @Test(dataProvider = "loginData")
    public void loginTest_01(Object obj1) {
        try {
            Map<String, String> testdata = (HashMap<String, String>) obj1;
            registrationPage.clickAccount();
            loginPage.clickLoginLink();
            //ISuite suite = context.getSuite();
            //String email = (String) suite.getAttribute("emailId");
            //System.out.println("Email id is :"+email);
            //loginPage.enterEmail(email);
            loginPage.enterEmail(testdata.get("email"));
            registrationPage.enterPassword(testdata.get("password"));
            loginPage.clickLoginButton();
            loginPage.verifyLogin();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail("Cant do login");
        }
        finally {
            ExtentTestManager.getTest().assignCategory("Login");
        }
    }

}
