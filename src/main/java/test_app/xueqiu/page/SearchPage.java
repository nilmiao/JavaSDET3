package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.jvm.hotspot.debugger.Page;
import test_web.wework.page.BasePage;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Miao on 2020/6/7
 */
public class SearchPage{
    private AndroidDriver driver;
    private By nameLocator=By.id("name");

    public SearchPage() {
    }

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public SearchPage search(String keyword) {
        MobileElement el2 = (MobileElement) driver.findElement(By.id("com.xueqiu.android:id/search_input_text"));
        el2.sendKeys(keyword);
        return this;
    }

    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
//        for(Object element: driver.findElements(nameLocator)){
//            nameList.add(((WebElement)element).getText());
//        }
        driver.findElements(nameLocator).forEach(name->nameList.add(((WebElement)name).getText()));
        return nameList;
    }

    public double getPrice() {
        driver.findElement(nameLocator).click();
        return Double.valueOf(driver.findElement(By.id("current_price")).getText());
    }
}