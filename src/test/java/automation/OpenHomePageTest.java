package automation;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.List;

public class OpenHomePageTest {

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
        webDriver.close();
        webDriver.quit();
    }

    @Given("the browser is open")
    public void the_browser_is_open() {
        Assertions.assertTrue(homePage.isBrowserOpen());
    }
    @When("homepage is entered")
    public void homepage_is_entered() {
        boolean homePageLoaded = true;
        try {
            homePage.isLoaded();
        } catch (Error error){
            homePageLoaded = false;
        }
        Assertions.assertTrue(homePageLoaded);
    }
    @Then("the user is on the correct homepage")
    public void the_user_is_on_the_correct_homepage() {
        Assertions.assertTrue(homePage.checkAccButton());
    }
    @Then("the homepage contains three slides")
    public void the_homepage_contains_slides() {
        WebElement slider = webDriver.findElement(By.xpath("//*[@id=\"n2-ss-6\"]/div[1]/div/div"));
        List<WebElement> c = slider.findElements(By.xpath("./child::*"));
        Assertions.assertEquals(c.size(), 3);
    }
}
