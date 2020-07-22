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
    //todo:多版本appp、多平台定位符通常有差别
    private final By taskList = By.id("gg_");
    private final By add = By.id("gym");
    private final By taskName = By.id("b2k");
    private final By save = byText("保存");


    public DayWayPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public DayWayPage add(String name, String time){
        click(add);
        sendKeys(taskName,name);
        click(save);
        return this;
    }

    public List<String> GetDayWay(String day){
        if(day != null){
            // todo 选择日期
        }
        return driver.findElements(taskList).stream().map(x->x.getText()).collect(Collectors.toList());

    }
}