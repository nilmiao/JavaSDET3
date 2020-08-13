package test_app.webview;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Miao on 2020/8/13
 */
public class WeiXinTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "mmm");
        desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
        desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        //高危操作，如果设置错误，聊天记录会被清空
        desiredCapabilities.setCapability("noReset", "true");
//        desiredCapabilities.setCapability("adbPort", "5038");
//        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");

        desiredCapabilities.setCapability("chromedriverExecutable", "/Users/miaobohang/FunWorker/test_files/83/chromedriver");
// todo 采用webview方式实现（推荐使用模拟器，搞定环境是最难的，所以第一节课不讲那么快）
//
// todo 交易 A股开户 输入手机号 验证码 立即开户
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //todo: 等待优化
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='通讯录']"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void wxmicroApplication() {
        Dimension size = driver.manage().window().getSize();
        new TouchAction<>(driver).longPress(PointOption.point(size.width / 2, size.height / 2)).moveTo(PointOption.point(size.width, size.height)).perform();

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}