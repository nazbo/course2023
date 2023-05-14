package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(xpath = "\"//*[@id=\\\"menu-item-50\\\"]/a\"")
    WebElement myAccount;
    WebDriver webDriver;
    String targetUrl;
    public HomePage(WebDriver webDriver, String targetUrl) {
        super(webDriver, targetUrl);
        this.webDriver = webDriver;
        this.targetUrl = targetUrl;
        PageFactory.initElements(webDriver, this);
    }

    public boolean checkAccButton() {
        boolean myAccExists;
        try {
            myAccount.isDisplayed();
            myAccExists = true;
        } catch (NullPointerException e) {
            myAccExists = false;
        }
        return myAccExists;
    }

    //public int findNumberOfSlides() { return slidesCount; }
}
