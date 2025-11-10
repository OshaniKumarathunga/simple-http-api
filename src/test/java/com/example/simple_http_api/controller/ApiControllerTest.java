package com.example.simple_http_api.controller;

import com.example.simple_http_api.exception.InvalidInputException;
import com.example.simple_http_api.service.ApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApiService apiService;

    @Test
    @DisplayName("GET /hello-world?name=alice → 200 and correct message")
    void return200ForValidName() throws Exception{
        when(apiService.generateMessage("alice")).thenReturn("Hello Alice");

        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    @DisplayName("GET /hello-world?name=toby → 400 and error response")
    void shouldReturn400ForInvalidName() throws Exception {
        when(apiService.generateMessage("toby"))
                .thenThrow(new InvalidInputException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "toby"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("GET /hello-world?name= (empty) → 400 and error response")
    void getHelloWorld_withEmptyName_returns400() throws Exception {
        when(apiService.generateMessage("")).
                thenThrow(new InvalidInputException("Invalid Input"));
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("GET /hello-world (no name param) → 400 and error response")
    void getHelloWorld_withoutNameParam_returns400() throws Exception {
        when(apiService.generateMessage(null)).
                thenThrow(new InvalidInputException("Invalid Input"));
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}
