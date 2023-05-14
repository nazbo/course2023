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
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.List;

public class OpenHomePageTest {
    //!!! Does not include the elements for login.

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";
    public static final String myAccHyperLink = "//*[@id=\"menu-item-50\"]/a";
    public static final String sliderXpath = "//*[@id=\"n2-ss-6-align\"]";

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

    @Given("the browser is open")
    public void the_browser_is_open() {
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
        webDriver.get(homePageUrl);
        Assertions.assertEquals(webDriver.getCurrentUrl(), homePageUrl);
    }
    @Then("the user is on the correct homepage")
    public void the_user_is_on_the_correct_homepage() {
        Assertions.assertTrue(webDriver.findElement(By.xpath(myAccHyperLink)).isDisplayed());
    }
    @Then("the homepage contains three slides")
    public void the_homepage_contains_slides() {
        WebElement slider = webDriver.findElement(By.xpath("//*[@id=\"n2-ss-6\"]/div[1]/div/div"));
        List<WebElement> c = slider.findElements(By.xpath("./child::*"));
        Assertions.assertEquals(c.size(), 3);
    }
}
