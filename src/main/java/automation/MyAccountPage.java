package automation;

import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class MyAccountPage extends BasePage{
    @FindBy(id = "reg_email")
    WebElement regEmailBox;
    @FindBy(id = "reg_password")
    WebElement regPassBox;
    @FindBy(xpath = "//*[@id=\"customer_login\"]/div[2]/form/p[3]/input[3]")
    WebElement regButton;
    WebDriver webDriver;
    String myAccUrl;
    String setUserEmailAttachment;
    public MyAccountPage(WebDriver webDriver, String myAccUrl) {
        super(webDriver, myAccUrl);
        this.webDriver = webDriver;
        this.myAccUrl = myAccUrl;
        PageFactory.initElements(webDriver, this);

        String randomString = "";
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            char c = (char) ('a' + random.nextInt(26));
            randomString += c;
        }
        this.setUserEmailAttachment = "_" + randomString + "@abv.bg";

    }

    public boolean checkRegButton() {
        boolean regButtonExists;
        try {
            regButton.isDisplayed();
            regButtonExists = true;
        } catch (NullPointerException e) {
            regButtonExists = false;
        }
        return regButtonExists;
    }

    public void clickRegButton() {
        regButton.click();
    }

    public void setUserEmail(String userName) {
        regEmailBox.sendKeys(userName + this.setUserEmailAttachment);
    }

    public void setUserPassword(String passWd) {
        regPassBox.sendKeys(passWd);
    }

    //Can use the setUserEmailAttachment for the login methods
}
