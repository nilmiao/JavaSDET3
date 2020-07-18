package test_app.xueqiu.page;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import test_app.xueqiu.page.StockPage;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockPageTest {
    static StockPage stockPage;

    @BeforeAll
    static void beforeAll() {
        stockPage = new MainPage().toStock();
        if (!stockPage.isEmpty()) {
            stockPage.delAllStock();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "alibaba,阿里巴巴",
            "jd,京东",
            "apple,苹果"
    })
    void addStock(String keyword, String name) {
        //新增自选股并断言自选股页面股票名
        assertEquals(stockPage.addStock(keyword).getFirstStockName(), name);
    }

    @AfterAll
    static void afterAll() {
        stockPage.quit();
    }
}