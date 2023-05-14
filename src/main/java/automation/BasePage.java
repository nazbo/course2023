package automation;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class BasePage extends LoadableComponent {

    private WebDriver webDriver;
    private String  pageUrl;
    public BasePage (WebDriver webDriver, String pageUrl) {
        this.webDriver = webDriver;
        this.pageUrl = pageUrl;
    }

    @Override
    protected void load() {
        webDriver.get(pageUrl);
    }

    @Override
    public void isLoaded() throws Error {
        String realUrl = webDriver.getCurrentUrl();
        Assert.isTrue(realUrl.equals(pageUrl), "The open url is not expected!");
    }

    private boolean isBrowserOpen() {
        boolean browserIsOpen;
        try {
            webDriver.getWindowHandles();
            browserIsOpen = true;
        } catch (NullPointerException e) {
            browserIsOpen = false;
        }
        return browserIsOpen;
    }

    public void closeBrowser() {
        webDriver.close();
        webDriver.quit();
    }
}
