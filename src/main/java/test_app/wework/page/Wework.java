package test_app.wework.page;

/**
 * @author Miao on 2020/7/20
 */
public class Wework extends BasePage{
    public Wework(String packageName, String activityName) {
        super("com.tencent.wework", ".launch.WwMainActivity");
    }

    public DayWayPage DayWay(){
        return new DayWayPage();
    }
}