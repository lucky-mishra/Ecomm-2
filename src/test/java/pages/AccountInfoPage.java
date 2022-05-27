package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Asserssion;
import utils.Log;

import java.util.List;

public class AccountInfoPage {
    private WebDriver driver;
    Asserssion asserssion;

    public AccountInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        asserssion = new Asserssion();
    }

    @FindBy(linkText = "ACCOUNT INFORMATION")
    private WebElement accountInfoLink;
    @FindBy(linkText = "//h1[contains(text(),'Edit Account Information')]")
    private WebElement pageTitle;
    @FindBy(linkText = "//input[@id='current_password']")
    private WebElement currentPwd;
    @FindBy(linkText = "//button[@title='Save']")
    private WebElement saveButton;

    public void clickAccountInfo() {
        accountInfoLink.click();
    }

    public void verifyAccountInfoPage(String expText) {
        String pageTitleText = pageTitle.getText();
        asserssion.isTextSame(expText, pageTitleText);
    }

    public void enterCurrentPwd(String curPwd) {
        Log.info("enter current password :" +curPwd);
        currentPwd.sendKeys(curPwd);
    }

}
