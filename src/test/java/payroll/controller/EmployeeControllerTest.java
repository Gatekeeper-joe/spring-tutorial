package payroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import payroll.entity.Employee;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * テストクラス
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class EmployeeControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** WebApplicationContext */
    @Autowired
    private WebApplicationContext context;

    /**
     * all
     * <ul>
     *     <li>レスポンスステータスが200</li>
     * </ul>
     * @throws Exception
     */
    @Test
    void test_all() throws Exception {

        // 処理実行
        MvcResult result = mockMvc.perform(
            get("/employees").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        // レスポンスログ確認（レスポンスの検証は別途します）
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }


    /**
     * one
     * @throws Exception
     */
    @Test
    void test_one() throws Exception {

        //存在するidへのアクセス
        MvcResult result = mockMvc.perform(
            get("/employees/1").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        //存在しないidへのアクセス
        mockMvc.perform(get("/employees/10")).andExpect(status().isNotFound());
        mockMvc.perform(get("/employees/abc")).andExpect(status().isNotFound());

        // レスポンスログ確認
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * one
     * @throws Exception
     */
    @Test
    void test_one_データ未存在() throws Exception {

        MvcResult result = mockMvc.perform(
            get("/employees/10").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());

        // レスポンスログ確認
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * one
     * @throws Exception
     */
    @Test
    void test_one_パスパラメータ不正() throws Exception {

        MvcResult result = mockMvc.perform(
            get("/employees/abc").contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());

        // レスポンスログ確認
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * newEmployee
     * @throws Exception
     */
    @Test
    void test_newEmployee() throws Exception {

        Employee newEmployee = new Employee("たけし", "佐藤", "平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        // 処理実行
        MvcResult result = mockMvc.perform(post("/employees")
            .content(objectMapper.writeValueAsString(newEmployee))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // レスポンスログ確認
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * newEmployee
     * @throws Exception
     */
    @Test
    void test_newEmployee_名前未入力() throws Exception {

        Employee employee = new Employee("", "佐藤", "平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        // 処理実行
        mockMvc.perform(post("/employees")
            .content(objectMapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isBadRequest());
    }

    /**
     * newEmployee
     * @throws Exception
     */
    @Test
    void test_newEmployee_名字未入力() throws Exception {

        Employee employee = new Employee("太郎", "", "平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        // 処理実行
        mockMvc.perform(post("/employees")
            .content(objectMapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isBadRequest());
    }

    /**
     * newEmployee
     * @throws Exception
     */
    @Test
    void test_newEmployee_氏名未入力() throws Exception {

        Employee employee = new Employee("", "", "平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        // 処理実行
        mockMvc.perform(post("/employees")
            .content(objectMapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isBadRequest());
    }


    /**
     * newEmployee
     * @throws Exception
     */
    @Test
    void test_newEmployee_役職文字数超過() throws Exception {

        Employee newEmployee = new Employee(
            "太郎",
            "佐藤",
            "平社員平社員平社員平社員平社員平社員平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/employees")
            .content(objectMapper.writeValueAsString(newEmployee))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest());
    }


    /**
     * test_replaceEmployee
     * @throws Exception
     */
    @Test
    void test_replaceEmployee() throws Exception {

        Employee newEmployee = new Employee("太郎", "山田", "平社員");

        ObjectMapper objectMapper = new ObjectMapper();

        MvcResult result = mockMvc.perform(put("/employees/1")
            .content(objectMapper.writeValueAsString(newEmployee))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();

        // 検証 - レスポンス
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

        // レスポンスログ確認
        log.info("【レスポンス】");
        log.info(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    /**
     * test_deleteEmployee
     * @throws Exception
     */
    @Test
    void test_deleteEmployee() throws Exception {

        mockMvc.perform(delete("/employees/1")).andExpect(status().isNoContent());
    }
}
