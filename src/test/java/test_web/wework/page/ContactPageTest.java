package test_web.wework.page;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPageTest {
    static MainPage main;
    static ContactPage contact;

    @BeforeAll
    static void beforeAll() {
        main = new MainPage();
        contact = main.toContact();
    }
    // 添加部门
    @Test
    void testAddSubDepartment() {
        contact.addSubDepartment("测试部门");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(contact.getSubDepartmentName(), ("测试部门"));
    }

    // 删除部门
    @Test
    void testDelSubDepartment() {
        contact.delSubDepartment("测试部门");
    }

    //添加标签
    @Test
    void testAddTag(){
        contact.addTag("测试");
        assertEquals(contact.getTagName(),"测试");
    }

    //删除标签
    @Test
    void testDelTag(){
        contact.delTag("测试");
    }
    @Test
    void testAddMember(){
        String username=contact.addMember("1", "1", "15600534761").search("1").getUserName();
        assertEquals(username, "1");
    }

    @Test
    void testSearch(){
        contact.search("1").delete();
    }

    @Test
    void testImportFromFile(){
        contact.importFromFile(this.getClass().getResource("/通讯录批量导入模板.xlsx"));
    }

    @AfterAll
    static void afterAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }
}