package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import reports.Log;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "ACCOUNT")
    private WebElement emailText;
    @FindBy(linkText = "Log In")
    private WebElement loginLink;
    @FindBy(xpath = "//input[@title='Email Address']")
    private WebElement email;
    @FindBy(xpath = "//button[@id='send2']")
    private WebElement loginButton;
    @FindBy(xpath = "//p[contains(text(),'Welcome')]")
    private WebElement welcomeText;

    public void clickLoginLink() {
        Log.info("Click on login link");
        loginLink.click();
    }

    public void enterEmail(String emailTeXt) {
        email.sendKeys(emailTeXt);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void verifyLogin() {
        welcomeText.isDisplayed();
    }
}
