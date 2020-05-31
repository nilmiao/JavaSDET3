package test_web.wework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test_web.wework.page.ContactPage;
import test_web.wework.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
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
    static void afterAll(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }

}
