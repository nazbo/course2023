package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@id=\"menu-item-50\"]/a")
    WebElement myAccount;
    @FindBy(xpath = "//*[@id=\"n2-ss-6\"]/div[1]/div/div")
    WebElement slider;
    By slidesXPaths = By.xpath("./child::*");
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

    public int findNumberOfSlides() {
        List<WebElement> slides = slider.findElements(slidesXPaths);
        return slides.size();
    }

    public void myAccCLick() {
        myAccount.click();
    }
}
