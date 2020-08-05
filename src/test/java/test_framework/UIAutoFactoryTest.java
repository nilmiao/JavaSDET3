package test_framework;

import org.junit.jupiter.api.Test;

class UIAutoFactoryTest {

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void create() {
        BasePage web =  UIAutoFactory.create("web");
        assert web != null;
        UIAuto uiAuto = web.load("/test_framework/webauto.yaml");
        web.run(uiAuto);
    }
}