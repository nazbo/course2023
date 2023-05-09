package automation;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class OpenMyAccountTest {

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";
    public static final String myAccPage = "https://practice.automationtesting.in/my-account/";

    @BeforeAll
    public static void initialSetup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("Start-Maximized");
        options.addExtensions(new File("src/test/java/resources/chromeExtensions/Adblocker2.crx"));
        webDriver = new ChromeDriver(options);
    }

    @AfterAll
    public void closeSessions() {
        webDriver.close();
        webDriver.quit();
    }
    @Given("the browser is open and we are on the home page")
    public void the_browser_is_open_and_we_are_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the user follows the My account hyper link")
    public void the_user_follows_the_my_account_hyper_link() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user is on the login\\/register page")
    public void the_user_is_on_the_login_register_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
