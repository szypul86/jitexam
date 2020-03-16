package com.jitexam.jitexam.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void register() throws Exception {
        //GIVEN
        String username = "alex123";
        String password = "alex123";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        final String url = "/token/generateToken";

        //WHEN
        ResultActions perform = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //THEN
        perform.andExpect(status().isOk());

    }
}