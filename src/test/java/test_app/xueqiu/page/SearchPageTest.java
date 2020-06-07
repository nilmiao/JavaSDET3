package test_app.xueqiu.page;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SearchPageTest {
    static MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void beforeALL() {
        searchPage = new MainPage().toSearch();
    }

    @ParameterizedTest
    @CsvSource({
            "alilibaba,        阿里巴巴",
            "jd,                  京东"
    })
    void search(String keyword, String name) {
        assertEquals(searchPage.search(keyword).getSearchList().get(0), name);
    }

    @Test
    void getPrice() {
        assertTrue(searchPage.search("alibaba").getPrice() > 200);
    }
}