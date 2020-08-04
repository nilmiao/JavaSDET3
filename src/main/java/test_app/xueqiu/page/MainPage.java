package test_app.xueqiu.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * @author Miao on 2020/6/7
 */
public class MainPage extends AppBasePage {
    public MainPage(){
    }

    @Step("跳转到搜索")
    public SearchPage toSearch() {
        click(By.id("com.xueqiu.android:id/home_search"));
        return new SearchPage(driver);
    }

    @Step("跳转到行情")
    public StockPage toStock(){
        if (byElementIsExist(By.id("com.xueqiu.android:id/tv_skip"))) {
            click(By.id("com.xueqiu.android:id/tv_skip"));
        }
        findElementsClick(By.id("com.xueqiu.android:id/tab_name"), 1);
        return new StockPage(driver);

    }


}