package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Duration;

public class ContactPage extends BasePage {
    By addMember = By.linkText("添加成员");
    By username = By.name("username");
    By delete = By.linkText("删除");
    By addSubDepartmentBtn = By.linkText("添加子部门");
    By subDepartmentName = By.name("name");
    By okBtn = By.linkText("确定");
    By threePointsMenu = By.xpath("//li/ul/li/a/span");
    By threePointsDelBtn = By.xpath("(//a[contains(text(),'删除')])[3]");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        while (driver.findElements(this.username).size() == 0) {
            click(addMember);
        }
        sendKeys(this.username, username);
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"), mobile);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    public ContactPage search(String keyword) {
        sendKeys(By.id("memberSearchInput"), keyword);
//        driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }

    public String getUserName() {
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    public ContactPage delete() {
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
        return this;

    }

    public ContactPage importFromFile(URL path) {
        String path_utf = "";
        try {
            path_utf = URLDecoder.decode(path.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
        upload(By.name("file"), path_utf);
        click(By.linkText("确认导入"));
        click(By.linkText("前往查看"));
        return this;
    }

    public ContactPage addSubDepartment(String departmentName) {
        click(addSubDepartmentBtn);
        sendKeys(subDepartmentName, departmentName);
        click(okBtn);
        return this;
    }

    public ContactPage delSubDepartment(String departmenName) {
        click(By.linkText(departmenName));
        click(threePointsMenu);
        click(threePointsDelBtn);
        click(okBtn);
        return this;
    }

    public String getSubDepartmentName() {
        String driverName = driver.findElement(By.id("party_name")).getText();
        return driverName;
    }
}
