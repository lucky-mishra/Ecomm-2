package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Asserssion;
import utils.Log;
import utils.PropertyReader;
import utils.Utility;

import java.util.Random;

public class RegistrationPage {
    private WebDriver driver;
    Asserssion asserssion;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        asserssion=new Asserssion();
    }

    @FindBy(linkText = "ACCOUNT")
    private WebElement accountLink;
    @FindBy(linkText = "Register")
    private WebElement registerLink;
    @FindBy(id = "firstname")
    private WebElement firstName;
    @FindBy(id = "middlename")
    private WebElement middlename;
    @FindBy(id = "lastname")
    private WebElement lastName;
    @FindBy(xpath = "//input[@title='Email Address']")
    private WebElement email;
    @FindBy(xpath = "//input[@title='Password']")
    private WebElement password;
    @FindBy(id = "confirmation")
    private WebElement passwordConfirm;
    @FindBy(id = "is_subscribed")
    private WebElement issubscribed;
    @FindBy(xpath = "//button[@title='Register']")
    private WebElement registerButton;
    @FindBy(xpath = "//li[@class='success-msg']")
    private WebElement successMessage;
    @FindBy(linkText = "My Account")
    private WebElement myAccountLink;
    @FindBy(linkText = "Log Out")
    private WebElement logoutLink;
    @FindBy(xpath = "//h3[contains(text(),'Contact Information')]/following-sibling::a")
    private WebElement editAccountInfo;
    public static Logger  logger = Logger.getLogger(RegistrationPage.class.getName());
    //public static Logger log = Logger.getLogger("");
    public void clickAccount() {
        accountLink.click();
        Log.info("clicked on account link");
    }

    public void clickRegister() {
        registerLink.click();
        Log.info("clicked on register link");
        //logger.info("clicked on register link");
    }

    public void enterFirstname(String fname) {
        firstName.sendKeys(fname);
        Log.info("enter first name as :" +fname);
    }

    public void enterMiddleName(String mname) {
        middlename.sendKeys(mname);
        Log.info("enter middle name as :" +mname);
    }

    public void enterLatName(String lname) {
        lastName.sendKeys(lname);
        Log.info("enter last name as :" +lname);
    }

    public void enterEmail() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        email.sendKeys("dummyEmail" + randomInt + "@gmail.com");
    }

    public void enterPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public void enterConfirmPassword(String cpwd) {
        passwordConfirm.sendKeys(cpwd);
    }

    public void selectSubscribe() {
        issubscribed.click();

    }

    public boolean isSubscribe() {
        boolean isSubscribe = issubscribed.isSelected();
        Log.info("newsletter subscribed");
        return isSubscribe;
    }

    public void registration() {
        registerButton.click();
        Log.info("click on registration button");
    }

    public void verifySuccessRegistration(String expectedText) {
        Utility.isElementPresent(successMessage,driver);
        String actualText = successMessage.getText();
        asserssion.isTextSame(expectedText, actualText);
        Log.info("registration done");
    }

    public void clickMyAccount() {
        myAccountLink.click();
        Log.info("clicked on my account link");
    }


    public void logout() {
        logoutLink.click();
        Log.info("clicked on logout link");
    }

    public void clickEdit() {
        editAccountInfo.click();
        Log.info("editing account info");
    }

    public String getEmail() {
        String emailText = email.getAttribute("value");
        Log.info("email is :" +emailText);
        return emailText;
    }

    public String getFullName() {
        String fname = firstName.getAttribute("value");
        String lname = lastName.getAttribute("value");
        String fullName = fname + " " + lname;
        Log.info("full name is :" +fullName);
        return fullName;
    }

}
