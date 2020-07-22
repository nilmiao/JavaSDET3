package test_app.wework.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Miao on 2020/7/20
 */
public class DayWayPage extends BasePage{

    public DayWayPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public DayWayPage add(String name, String time){
        click(By.id("gq0"));
        sendKeys(By.id(""));
        click();
        return this;
    }

    public List<String> GetDayWay(String day){
        if(day != null){
            // todo 选择日期
        }
        return driver.findElements().stream().map(x->x.getText()).collect(Collectors.toList());

    }
}