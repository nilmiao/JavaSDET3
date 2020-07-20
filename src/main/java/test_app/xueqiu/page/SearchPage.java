package test_app.xueqiu.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Miao on 2020/6/7
 */
public class SearchPage extends BasePage {
    private By nameLocator = By.id("name");

    public SearchPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Step("按关键字搜索")
    public SearchPage search(String keyword) {
        do {
            sendKeys(By.id("com.xueqiu.android:id/search_input_text"), keyword);
        } while (driver.findElements(nameLocator).size() <= 0);
        return this;

    }

    @Step("获取搜索匹配的列表")
    public List<String> getSearchList() {
        List<String> nameList = new ArrayList<>();
        driver.findElements(nameLocator).forEach(name -> nameList.add((name).getText()));
        return nameList;
    }

    @Step("选择目标搜索结果")
    public SearchPage selectSearchResult(String name, String code) {
        //xpath改进
        List<MobileElement> nameElements = driver.findElements(nameLocator);
        List<MobileElement> codeElements = driver.findElements(By.id("com.xueqiu.android:id/code"));
        for (int i = 0; i < nameElements.size(); i++) {
            boolean isContainName = nameElements.get(i).getText().contains(name);
            boolean isEqualCode = codeElements.get(i).getText().equals(code);
            if (isContainName && isEqualCode) {
                nameElements.get(i).click();
                break;
            }
        }
        return this;
    }


    @Step("获取当前价格")
    public double getPrice() {
        click(nameLocator);
        return Double.parseDouble(getText(By.id("current_price")));
    }

    @Step("选择并添加股票")
    public void addFirstSearchResult() {
        //点击第一条搜索结果
        findElementsClick(nameLocator, 0);
        //点击第一条的加入自选
        click(By.id("com.xueqiu.android:id/follow_btn"));
        //点击取消
        click(By.id("com.xueqiu.android:id/action_close"));
    }

    @Step("取消搜索")
    public void quitSearch() {
        click(By.id("com.xueqiu.android:id/action_close"));
    }
}