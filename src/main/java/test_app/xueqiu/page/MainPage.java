package test_app.xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Miao on 2020/6/7
 */
public class MainPage {
    private AndroidDriver driver;

    public MainPage() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("deviceName", "P30");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("udid", "3EP7N19215006078");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("dontStopAppOnReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        this.driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public SearchPage toSearch(){
        MobileElement el1 = (MobileElement) driver.findElement(By.id("com.xueqiu.android:id/home_search"));
        el1.click();
        return new SearchPage(driver);
    }

    public StockPage toStock() {
        MobileElement element = (MobileElement) driver.findElement(By.name("行情"));
        element.click();
        return new StockPage(driver);

    }
}