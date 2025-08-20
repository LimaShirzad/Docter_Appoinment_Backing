package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Test UserController")
//@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    @DisplayName("Test User Delete API")
    void shouldDeleteUser() throws Exception {

        Long userId=12L;

        doNothing().when(userService).deleteUserById(userId);

        mockMvc.perform(delete("/api/user/{id}",userId)).andExpect(status().isOk());


        verify(userService,times(1)).deleteUserById(userId);


    }




}
