package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_framework.BasePage;
import test_framework.UIAuto;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebBasePage extends BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public WebBasePage() {
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public WebBasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }

    public void quit() {
        driver.quit();
    }


    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void sendKeys(By by, String content) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(content);
    }

    public void upload(By by, String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(path);
    }

    public String getText(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    @Override
    public void click(HashMap<String, Object> map) {
        super.click(map);
        String key = (String) map.keySet().toArray()[0];
        String value = (String) map.values().toArray()[0];
        By by = null;
        if (key.toLowerCase().equals("id")) {
            by = By.id(value);
        }
        if (key.toLowerCase().equals("linkText".toLowerCase())) {
            by = By.linkText(value);
        }

        if (key.toLowerCase().equals("partialLinkText".toLowerCase())) {
            by = By.partialLinkText(value);
        }

        click(by);
    }

    @Override
    public void action(HashMap<String, Object> m) {
        super.action(m);
        String action = m.get("action").toString().toLowerCase();
        if(action.equals("get")){
            driver.get(m.get("url").toString());
        }else {
            System.out.println("error get");
        }

    }
}
