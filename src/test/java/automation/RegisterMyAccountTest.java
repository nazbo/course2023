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
import java.util.Random;

public class RegisterMyAccountTest {

    public static WebDriver webDriver;
    public static final String homePageUrl = "https://practice.automationtesting.in/";
    public static final String myAccPage = "https://practice.automationtesting.in/my-account/";
    public static final String myAccHyperLink = "//*[@id=\"menu-item-50\"]/a";
    public static final String regButtonXpath = "//*[@id=\"customer_login\"]/div[2]/form/p[3]/input[3]";

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
    @Given("the browser is open and we are on the registration page")
    public void the_browser_is_open_and_we_are_on_the_registration_page() {
        //setup
        boolean browserIsOpen;
        //act
        try {
            webDriver.getWindowHandles();
            browserIsOpen = true;
        } catch (WebDriverException exception) {
            browserIsOpen = false;
        }
        webDriver.get(myAccPage);
        //assert
        Assertions.assertTrue(browserIsOpen, "The browser was not open when it was expected!");
        Assertions.assertEquals(webDriver.getCurrentUrl(), myAccPage);
    }
    @When("^the userId: (.*) and password: (.*) are provided$")
    public void the_user_id_mark_and_password_are_provided(String usrId, String passWd) {
        WebElement regEmailBox = webDriver.findElement(By.id("reg_email"));
        WebElement regPassBox = webDriver.findElement(By.id("reg_password"));

        String randomString = "";
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            char c = (char) ('a' + random.nextInt(26));
            randomString += c;
        }
        String usersEmail = usrId + "_" + randomString + "@abv.bg";

        regEmailBox.sendKeys(usersEmail);
        regPassBox.sendKeys(passWd);

        Assertions.assertEquals(regEmailBox.getAttribute("value"), usersEmail);
    }
    @When("the Registration Button is clicked")
    public void the_registration_button_is_clicked() {
        WebElement regButton = webDriver.findElement(By.xpath(regButtonXpath));
        Assertions.assertTrue(regButton.isDisplayed());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        regButton.click();
    }
    @Then("A successful registration is completed")
    public void a_successful_registration_is_completed() {
        boolean regButtonExists;
        try {
            webDriver.findElement(By.xpath(regButtonXpath)).isDisplayed();
            regButtonExists = true;
        } catch (NoSuchElementException exception) {
            regButtonExists = false;
        }
        Assertions.assertFalse(regButtonExists);
    }
}
