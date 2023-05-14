package automation;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class RegisterMyAccountTest {

    public static WebDriver webDriver;
    public static final String myAccPageUrl = "https://practice.automationtesting.in/my-account/";
    public static MyAccountPage myAccountPage;

    @BeforeAll
    public static void initialSetup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("Start-Maximized");
        options.addExtensions(new File("src/test/java/resources/chromeExtensions/Adblocker2.crx"));
        webDriver = new ChromeDriver(options);
        myAccountPage = new MyAccountPage(webDriver, myAccPageUrl);
    }

    @AfterAll
    public static void closeSessions() {
        webDriver.close();
        webDriver.quit();
    }
    @Given("the browser is open and we are on the registration page")
    public void the_browser_is_open_and_we_are_on_the_registration_page() {
        Assertions.assertTrue(myAccountPage.isBrowserOpen());
        boolean accountPageLoaded = true;
        try {
            myAccountPage.isLoaded();
        } catch (Error error){
            accountPageLoaded = false;
        }
        Assertions.assertTrue(accountPageLoaded);
    }
    @When("^the userId: (.*) and password: (.*) are provided$")
    public void the_user_id_mark_and_password_are_provided(String usrId, String passWd) {
        myAccountPage.setUserEmail(usrId);
        myAccountPage.setUserPassword(passWd);

        Assertions.assertEquals(myAccountPage.getUserIdValue(), usrId + myAccountPage.getSetUserEmailAttachment());
    }
    @When("the Registration Button is clicked")
    public void the_registration_button_is_clicked() {
        Assertions.assertTrue(myAccountPage.checkRegButton());
        myAccountPage.clickRegButton();
        // Nothing to assert here, too : (
    }
    @Then("A successful registration is completed")
    public void a_successful_registration_is_completed() {
        Assertions.assertFalse(myAccountPage.checkRegButton());
    }
}
