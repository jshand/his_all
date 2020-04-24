package com.neuedu.controller;

import com.neuedu.HisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 项目    ： his_all
 * 创建时间 ：2020/4/24  14:03 24
 * author  ：jshand-root
 * site    :  http://314649444.iteye.com
 * 描述     :
 */
@SpringBootTest(classes = HisApplication.class)
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    MockMvc  mockMvc;

    @Test
    void queryMedicalRecordById() throws Exception {
        String url = "http://127.0.0.1/mr/queryMedicalRecordById/2";
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get(url)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andReturn();

        System.out.println(response.getResponse().getContentAsString());

    }
}