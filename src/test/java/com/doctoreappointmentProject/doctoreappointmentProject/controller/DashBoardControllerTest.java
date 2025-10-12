package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.AdminProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.ClientService;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DashBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Test DashBoard Controller ")
public class DashBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  DashBoardService dashBoardService;
    private ClientService clientService;



    @Test
    void testGetAdminProfile() throws Exception {

        AdminProfileDTO mockProfile=new AdminProfileDTO();

        mockProfile.setFirstName("Ali");
        mockProfile.setLastName("Khan");
        String fakeUsername="admin123";

        when(dashBoardService.getAdminProfile(fakeUsername)).thenReturn(mockProfile);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(fakeUsername);

        mockMvc.perform(get("/api/dashboard/adminprofile").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ali"))
                .andExpect(jsonPath("$.lastName").value("Khan"));

    }

    @Test
    void getTotalDoctorShouldReturnNumber() throws Exception {
        // Step 1: fake behavior of the service
        when(dashBoardService.getTotalDoctor()).thenReturn(5L);

        // Step 2: perform GET request
        mockMvc.perform(get("/api/dashboard/allDoctor"))
                .andExpect(status().isOk()) // check status 200 OK
                .andExpect(content().string("5")); // check that it returns "5"
    }

    @Test
    void getTotalPatientShouldReturnNumber() throws Exception {
        // Step 1: mock the service result
        when(dashBoardService.getTotalPatient()).thenReturn(8L);

        // Step 2: perform GET request and check response
        mockMvc.perform(get("/api/dashboard/allPatent"))
                .andExpect(status().isOk())
                .andExpect(content().string("8"));
    }

}
