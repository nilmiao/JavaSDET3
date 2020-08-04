package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Miao on 2020/6/7
 * 首页 行情 删除所有股票
 * 添加三只股票
 */

public class StockPage extends AppBasePage {
    public StockPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("删除行情所有自选股")
    public void delAllStock() {
        //进入管理自选股股
        click(By.id("com.xueqiu.android:id/edit_group"));
        //点击全选
        click(By.id("com.xueqiu.android:id/check_all"));
        //点击删除自选
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        //点击确定
        click(By.id("com.xueqiu.android:id/tv_right"));
        //点击完成
        click(By.id("com.xueqiu.android:id/action_close"));
    }

    public boolean isEmpty(){
        try {
            driver.findElementById("com.xueqiu.android:id/fl_empty_container").isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }
        return true;
    }


    @Step("获取行情所有自选股")
    public List<String> getAllStock(){
        click(By.id("com.xueqiu.android:id/edit_group"));
        List<MobileElement> StockList = driver.findElements(By.id("com.xueqiu.android:id/stockName"));
        List<String> nameList;
        if (StockList.size()>0){
            nameList = StockList.stream().map(RemoteWebElement::getText).collect(Collectors.toList());
        }else {
            nameList = Arrays.asList("no such elements");
        }
        click(By.id("com.xueqiu.android:id/action_close"));
        return nameList;
    }

    public StockPage addStock(String stockName){
        MobileElement element= driver.findElementsById("com.xueqiu.android:id/action_search").get(0);
        element.click();
        SearchPage searchPage=new SearchPage(driver);
        searchPage.search(stockName).addFirstSearchResult();
        return this;
    }

    @Step("获取自选股列表第一条股票名字")
    public String getFirstStockName(){
        return findElementsGetText(By.id("com.xueqiu.android:id/portfolio_stockName"),0);
    }

}