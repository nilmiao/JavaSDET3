package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Miao on 2020/7/29
 */

// 自动化领域建模 完成get attribute 获取文本 的封装
public class BasePage {
    public void click(HashMap<String, Object> map) {
        System.out.println("click");
        System.out.println(map);
    }

    public void find() {

    }

    public void send() {

    }

    public void getText() {

    }

    public void sendKeys(HashMap<String, Object> m) {
        System.out.println("sendKeys");
        System.out.println(m);
    }

    public void action(HashMap<String, Object> m) {
        System.out.println("action");
        System.out.println(m);
    }


    public void run(UIAuto uiAuto) {
        uiAuto.steps.stream().forEach(m -> {
//            if(m.keySet().contains("click")){
//                click((HashMap<String,Object>)m.get("click"));
//            }
            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }

            if (m.containsKey("action")) {
                action(m);
            }

        });

    }


    public UIAuto load(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        UIAuto uiauto = null;
        try {
            uiauto = mapper.readValue(
                    BasePage.class.getResourceAsStream(path),
                    UIAuto.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uiauto;

    }


}