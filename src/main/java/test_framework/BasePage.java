package test_framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Miao on 2020/7/29
 */

// 自动化领域建模 完成get attribute 获取文本 的封装
public class BasePage {
    List<PageObjectModel> pages = new ArrayList<>();

    public void find() {

    }

    public void send() {

    }

    public void getText() {

    }

    public void click(HashMap<String, Object> map) {
        System.out.println("click");
        System.out.println(map);
    }

    public void sendKeys(HashMap<String, Object> m) {
        System.out.println("sendKeys");
        System.out.println(m);
    }

    public void action(HashMap<String, Object> m) {
        System.out.println("action");
        System.out.println(m);
        // page级别的的关键字
        if (m.containsKey("page")) {
            String action = m.get("action").toString();
            String pageName = m.get("page").toString();
            pages.forEach(pom -> System.out.println(pom.name));
            pages.stream()
                    .filter(pom -> pom.name.equals(pageName))
                    .findFirst()
                    .get()
                    .methods.get(action).forEach(step -> {
                action(step);
            });
        } else {
            //自动化级别
            if (m.containsKey("click")) {
                HashMap<String, Object> by = (HashMap<String, Object>) m.get("click");
                click(by);
            }

            if (m.containsKey("sendKeys")) {
                sendKeys(m);
            }
        }
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

    public PageObjectModel loadPage(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    public void loadPages(String dir) {
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("_page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            System.out.println(path);
            pages.add(loadPage(path));
        });
    }

}