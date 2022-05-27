package testcase;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import reports.ExtentTestManager;
import utils.ExcelReader;
import utils.PropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationTest extends BaseTest {
    RegistrationPage registrationPage;
    ExcelReader excelReader = new ExcelReader("Registration");

    @DataProvider(name = "data")
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
    public void loadClass() {
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    }

    @Test(dataProvider = "data")
    public void doRegistration_01(Object obj1, ITestContext context) {
        try {
            HashMap<String, String> testdata = (HashMap<String, String>) obj1;
            System.out.println("TestData---" + testdata);
            registrationPage.clickAccount();
            registrationPage.clickRegister();
            registrationPage.enterFirstname(testdata.get("fname"));
            registrationPage.enterMiddleName(testdata.get("mname"));
            registrationPage.enterLatName(testdata.get("lname"));
            registrationPage.enterEmail();
            registrationPage.enterPassword(testdata.get("pwd"));
            registrationPage.enterConfirmPassword(testdata.get("cpwd"));
            registrationPage.selectSubscribe();
            boolean isSubscribe = registrationPage.isSubscribe();
            registrationPage.registration();
            registrationPage.verifySuccessRegistration(new PropertyReader().readProperty("success_registration"));
            registrationPage.clickAccount();
            registrationPage.clickMyAccount();
            registrationPage.clickEdit();
            String email = registrationPage.getEmail();
            System.out.println("email from account is :" + email);
            ISuite suite = context.getSuite();
            suite.setAttribute("emailId", email);
            suite.setAttribute("isSubscribe", isSubscribe);
            registrationPage.clickAccount();
            registrationPage.logout();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assert.fail("Registration is not done");
        } finally {
            ExtentTestManager.getTest().assignCategory("Registration");
        }
    }

}
