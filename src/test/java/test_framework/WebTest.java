package test_framework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Miao on 2020/8/4
 */
public class WebTest {
    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        //todo: 加载通用配置
    }

    @BeforeEach
    void beforeEach(){
        //todo: 每个用例相关
    }

    @ParameterizedTest(name = "{index},{1}")
    @MethodSource
    void classic(UIAuto uiAuto, String path) {

//        basePage.run(uiAuto);
    }

    static List<Arguments> classic() {
        basePage = UIAutoFactory.create("web");
        assert basePage != null;
        List<Arguments> all = new ArrayList<Arguments>();
        Arrays.asList(
                "/test_framework/webauto.yaml",
                "/test_framework/webauto1.yaml"
//                "/test_framework/webauto3.yaml"
        ).stream().forEach(path -> {
            UIAuto uiAuto = basePage.load(path);
            uiAuto.description = path;
            all.add(arguments(uiAuto, uiAuto.description));

        });
        return all;
    }
}
