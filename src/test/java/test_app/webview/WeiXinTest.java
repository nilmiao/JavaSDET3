package test_app.webview;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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
        desiredCapabilities.setCapability("appActivity", "com.tmapping.jsonencent.mm.ui.LauncherUI");
        //高危操作，如果设置错误，聊天记录会被清空
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("adbPort", "5038");
        //
        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
        // 简单粗暴的方案
        desiredCapabilities.setCapability("chromedriverExecutable", "/Users/miaobohang/FunWorker/test_files/83/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
        desiredCapabilities.setCapability("goog:chromeOptions", chromeOptions);
        //必须得加上，因为默认生成browserName=chrome的设置，需要去掉
        desiredCapabilities.setCapability("browserName", "");

        //第三步：设置adb proxy
        //通过自己的adb代理修复chromedriver的bug并解决@xweb_devtools_remote的问题
        desiredCapabilities.setCapability("adbPort", "5038");

        //加速
        desiredCapabilities.setCapability("skipLogcatCapture", "true");
        //用于快速测试
//        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
        //完善的版本选择方案，不过会优先找android webview默认实现
//        desiredCapabilities.setCapability("chromedriverExecutableDir",
//                "/Users/seveniruby/projects/chromedriver/chromedrivers");
//        desiredCapabilities.setCapability("chromedriverChromeMappingFile",
//                "/Users/seveniruby/projects/Java3/src/main/resources/mapping.json");
        //打印更多chromedriver的log方便定位问题
        desiredCapabilities.setCapability("showChromedriverLog", true);
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
        new TouchAction<>(driver)
                .longPress(
                        LongPressOptions.longPressOptions()
                                .withDuration(Duration.ofSeconds(2))
                                .withPosition(PointOption.point(size.width / 2, size.height / 2)))
                .moveTo(PointOption.point(size.width / 2, size.height / 10 * 9))
                .release()
                .perform();
        driver.findElement(By.className("android.widget.EditText")).click();
        driver.findElement(By.xpath("//*[@text='取消']"));
        driver.findElement(By.className("android.widget.EditText")).sendKeys("雪球");
        driver.findElement(By.className("android.widget.Buttion")).click();
        driver.getContextHandles().stream().forEach(context -> {
            System.out.println(context.toString());
        });

        String webview = driver.getContextHandles().stream()
                .filter(context -> context.toString().contains("WEBVIEW_xweb")).findFirst().get().toString();
        System.out.println(webview);
        driver.context(webview);

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}