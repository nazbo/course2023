package automation;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class OpenMyAccountTest {

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";
    public static final String myAccPage = "https://practice.automationtesting.in/my-account/";
    public static final String myAccHyperLink = "//*[@id=\"menu-item-50\"]/a";

    @BeforeAll
    public static void initialSetup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("Start-Maximized");
        options.addExtensions(new File("src/test/java/resources/chromeExtensions/Adblocker2.crx"));
        webDriver = new ChromeDriver(options);
    }

    @AfterAll
    public static void closeSessions() {
        webDriver.close();
        webDriver.quit();
    }
    @Given("the browser is open and we are on the home page")
    public void the_browser_is_open_and_we_are_on_the_home_page() {
        //setup
        boolean browserIsOpen;
        //act
        try {
            webDriver.getWindowHandles();
            browserIsOpen = true;
        } catch (WebDriverException exception) {
            browserIsOpen = false;
        }
        webDriver.get(homePageUrl);
        //assert
        Assertions.assertTrue(browserIsOpen, "The browser was not open when it was expected!");
        Assertions.assertEquals(webDriver.getCurrentUrl(), homePageUrl);
    }
    @When("the user follows the My account hyper link")
    public void the_user_follows_the_my_account_hyper_link() {
        WebElement myAccButton = webDriver.findElement(By.xpath(myAccHyperLink));
        Assertions.assertTrue(myAccButton.isDisplayed());
        myAccButton.click();
        //Action class to user perform() to try catching a click exception
    }
    @Then("the user is on the login or register")
    public void the_user_is_on_the_login_register_page() {
        Assertions.assertEquals(webDriver.getCurrentUrl(), myAccPage);
    }
}
