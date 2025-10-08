package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Test AuthController ")
public class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;


    @Test
    void loginShouldReturn200WhenCredentialsAreValid() throws Exception{

        Map<String,Object>  result=new HashMap<>();
        result.put("token","dummy-jwt-token");

        Mockito.when(authService.login("testuser","password123")).thenReturn(result);


        String loginJson= """
                
                {
                      "username": "testuser",
                      "password": "password123"
                }
                
                """;

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("dummy-jwt-token"));
//                .andExpect(jsonPath("$.username").value("dummy-jwt-token"))
//                .andExpect(jsonPath("$.password").value("dummy-jwt-token"));



    }






}
