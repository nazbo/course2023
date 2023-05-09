package automation;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class OpenHomePageTest {

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";

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

    @Given("the browser is open")
    public void the_browser_is_open() {
        webDriver.get(homePageUrl);
        boolean browserIsOpen;
        try {
            webDriver.getWindowHandles();
            browserIsOpen = true;
        } catch (WebDriverException exception) {
            browserIsOpen = false;
        }
        Assertions.assertTrue(browserIsOpen, "The browser was not open when it was expected!");
    }
    @When("homepage is entered")
    public void homepage_is_entered() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the user is on the correct homepage")
    public void the_user_is_on_the_correct_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the homepage contains {int} slides")
    public void the_homepage_contains_slides(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
