package test_framework;

import test_app.wework.page.AppBasePage;
import test_web.wework.page.WebBasePage;

/**
 * @author Miao on 2020/8/4
 */
public class Factory {
    public static BasePage create(String driverName){
        if(driverName.equals("web") || driverName.equals("selenium")){
            return new WebBasePage();
        }
        if(driverName.equals("app") || driverName.equals("appium")){
            return new AppBasePage();
        }

        if(driverName.equals("uiautomator")){
//            return
        }

        if(driverName.equals("atx")){
//            return
        }

        if(driverName.equals("macaca")){
//            return
        }

        return null;
    }
}