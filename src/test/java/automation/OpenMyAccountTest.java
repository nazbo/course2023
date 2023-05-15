package automation;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class OpenMyAccountTest {

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";
    public static HomePage homePage;

    @BeforeAll
    public static void initialSetup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("Start-Maximized");
        options.addExtensions(new File("src/test/java/resources/chromeExtensions/Adblocker2.crx"));
        webDriver = new ChromeDriver(options);
        homePage = new HomePage(webDriver, homePageUrl);
    }

    @AfterAll
    public static void closeSessions() {
//        webDriver.close();
//        webDriver.quit();
    }
    @Given("the browser is open and we are on the home page")
    public void the_browser_is_open_and_we_are_on_the_home_page() {
        Assertions.assertTrue(homePage.isBrowserOpen());
        boolean homePageLoaded = true;
        try {
            homePage.isLoaded();
        } catch (Error error){
            Assertions.assertEquals(error, "dsa");
            homePageLoaded = false;
        }
        Assertions.assertTrue(homePageLoaded);
    }
    @When("the user follows the My account hyper link")
    public void the_user_follows_the_my_account_hyper_link() {
        homePage.myAccCLick();
        // NOTHING TO ASSERT HERE : (
    }
    @Then("the user is on the login or register")
    public void the_user_is_on_the_login_register_page() {
        boolean stillOnHomePage = true;
        try {
            homePage.isLoaded();
        } catch (Error error) {
            stillOnHomePage = false;
        }
        Assertions.assertFalse(stillOnHomePage);
    }
}
