package test_app.wework.page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayWayPageTest {
    private static Wework wework;

    @BeforeAll
    static void beforeAll() {
        wework = new Wework();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
       assertTrue(wework.DayWay().add("上班打卡",null).GetDayWay(null).contains("上班打卡"));
    }

    @Test
    void getDayWay() {
    }
}