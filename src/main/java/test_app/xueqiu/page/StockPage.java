package test_app.xueqiu.page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miao on 2020/6/7
 * 首页 行情 删除所有股票
 * 添加三只股票
 */

public class StockPage {
    private AndroidDriver driver;
    private By nameLocator = By.id("name");

    public StockPage() {
    }

    public StockPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public StockPage searchButon() {
        MobileElement el2 = (MobileElement) driver.findElement(By.id("com.xueqiu.android:id/search"));
        el2.click();
        return this;
    }

    public StockPage search(String keyword) {
        MobileElement el2 = (MobileElement) driver.findElement(By.id("com.xueqiu.android:id/search_input_text"));
        el2.sendKeys(keyword);
        return this;
    }

    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
        for (Object element : driver.findElements(nameLocator)) {
            nameList.add(((WebElement) element).getText());
        }
//        driver.findElements(nameLocator).forEach(name->nameList.add(((WebElement)name).getText()));
        return nameList;
    }

    public StockPage addStock(){
        MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[3]/android.widget.TextView");
        el5.click();
        return this;
    }


}