package test_framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePageTest {

    private static BasePage basePage;

    @BeforeAll
    static void beforeAll(){
        basePage = new BasePage();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void run() {
    }

    @Test
    void load() {
        UIAuto uiAuto = basePage.load("/test_framework/uiauto.yaml");
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(uiAuto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}