package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class BasePage extends LoadableComponent {

    private WebDriver webDriver;
    private String  pageUrl;
    public BasePage (WebDriver webDriver, String pageUrl) {
        this.webDriver = webDriver;
        this.pageUrl = pageUrl;
        this.load();
    }

    @Override
    protected void load() {
        webDriver.get(pageUrl);
    }

    @Override
    public void isLoaded() throws Error {
        String realUrl = webDriver.getCurrentUrl().trim();
        if(!realUrl.equals(pageUrl)) {
            throw new Error("Wrong loaded page! Expected: " + pageUrl + " , but got: " + realUrl);
        }
    }
    public boolean isBrowserOpen() {
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
