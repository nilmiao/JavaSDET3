package test_web.wework.page;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPageTest {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }

    @Test
    void testAddSubDepartment() {
        contact.addSubDepartment("测试部门");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(contact.getSubDepartmentName(),("测试部门"));
    }


    @Test
    void delSubDepartment() {
        contact.delSubDepartment("测试部门");
    }

    @AfterAll
    static void afterAll(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }
}