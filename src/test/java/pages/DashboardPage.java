package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Asserssion;
import utils.PropertyReader;

import java.util.List;

public class DashboardPage {

    private WebDriver driver;
    Asserssion asserssion;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        asserssion = new Asserssion();
    }

    @FindBy(xpath = "//div[@class='block-content']/ul/li")
    private List<WebElement> leftBlockLinks;
    @FindBy(xpath = "//p[@class='hello']/child::strong")
    private WebElement nameText;
    @FindBy(xpath = "//h3[contains(text(),'Newsletters')]/following-sibling::a")
    private WebElement editNewsletters;
    @FindBy(xpath = "//input[@id='subscription']")
    private WebElement subscription;
    @FindBy(xpath = "//h3[contains(text(),'Newsletters')]/following::p")
    private WebElement subscriptionText;


    public int getLeftLinks() {
        int linksCount = leftBlockLinks.size();
        return linksCount;
    }

    public String getName() {
        String name = nameText.getText();
        String[] name1 = name.split("\\s");
        for (String s : name1) {
            System.out.println("name is :" + s);
        }
        String lastName = name1[2];
        String lname = lastName.substring(0, 6);
        String fullName = name1[1] + " " + lname;
        System.out.println("fullname is :" + fullName);
        return fullName;
    }

    public void clickEdit() {
        editNewsletters.click();
    }

    public boolean isSubscrition() {
        boolean isSubscrition = subscription.isSelected();
        return isSubscrition;
    }

    public void verifySubsMessage(String expText) {
        String actualText = subscriptionText.getText();
        asserssion.isTextSame(expText, actualText);
    }


}
