package test_app.xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Miao on 2020/7/18
 */
public class BasePage {
    AndroidDriver driver;

    public BasePage() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("deviceName", "P30");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("udid", "3EP7N19215006078");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        URL remoteUrl =null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public BasePage(AndroidDriver driver){
        this.driver = driver;
    }

    public void quit() {
        this.driver.quit();
    }

    public void click(By by) {
        this.driver.findElement(by).click();
    }

    public void findElementsClick(By by, int num) {
        MobileElement element = (MobileElement) this.driver.findElements(by).get(num);
        element.click();
    }

    public String findElementsGetText(By by, int num) {
        MobileElement element = (MobileElement) this.driver.findElements(by).get(num);
        return element.getText();
    }

    public boolean byElementIsExist(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeys(By by, String text) {
        this.driver.findElement(by).sendKeys(text);
    }

    public String getText(By by){
        return this.driver.findElement(by).getText();
    }
    public void switchContext(String ctx) {
        this.driver.getContextHandles().forEach(context -> {
            if (context.toString().contains(ctx)) {
                this.driver.context(context.toString());
            }
        });
    }

    public void switchWindowHandle(String by) {
        Object win = driver.getWindowHandles().stream().filter(w -> {
            driver.switchTo().window(w);
            return driver.getPageSource().contains(by);
        }).toArray()[0];
        driver.switchTo().window(win.toString());
    }

}

