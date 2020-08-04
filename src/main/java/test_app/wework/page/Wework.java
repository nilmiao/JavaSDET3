package test_app.wework.page;

import org.openqa.selenium.By;

/**
 * @author Miao on 2020/7/20
 */
public class Wework extends AppBasePage {
    public Wework() {
        super("com.tencent.wework", ".launch.WwMainActivity");
    }

    public DayWayPage DayWay(){
        click(By.xpath("//*[@text='日程']"));
        return new DayWayPage(driver);
    }
}