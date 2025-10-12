package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.PatientInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.service.PatientInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(PatientInfoController.class)
@DisplayName("Test PatientInfoController Test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PatientInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @MockBean
    private PatientInfoService patientInfoService;

    @Test
    @DisplayName("Test CreatePatient API")
    void testCreatePatientSuccess() throws Exception{


        PatientInfoDTO patientInfoDTO=new PatientInfoDTO();

        patientInfoDTO.setId(1);
        patientInfoDTO.setBloodGroup("A");
        patientInfoDTO.setUserId(1);


        mockMvc.perform(post("/api/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(patientInfoDTO)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.success").value("Your Account Created Successfully"));



//        mockMvc.perform(post("/api/patient/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"id\":1,\"bloodGroup\":\"A\",\"userId\":1}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success")
//                        .value("Your Account Created Successfully"));

    }


        @Test
        void testCreatePatient_BloodGroupBlank_ShouldReturn400() throws Exception {
            PatientInfoDTO dto = new PatientInfoDTO();
            dto.setId(1);
            dto.setBloodGroup(""); // invalid
            dto.setUserId(1);

            mockMvc.perform(post("/api/patient/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                            .andExpect(jsonPath("$.errors.bloodGroup").value("Blood Group Should Not Be null"));

        }

        @Test
        void testCreatePatient_BloodGroupTooLong_ShouldReturn400() throws Exception {
            PatientInfoDTO dto = new PatientInfoDTO();
            dto.setId(1);
            dto.setBloodGroup("ABCDE123");
            dto.setUserId(1);

            mockMvc.perform(post("/api/patient/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(dto)))
                    .andExpect(jsonPath("$.errors.bloodGroup").value("blood group should not be greater than 5"));

        }


    @Test
    void testGetPatientProfile() throws Exception {
        // Arrange
        String username = "testpatient";

        // Mock profile DTO
        PatientInfoProfileDTO profileDTO = new PatientInfoProfileDTO();
        profileDTO.setId(1);
        profileDTO.setFirstName("me");
        profileDTO.setEmail("mesherzad@gamil.com");

        // Mock service
        when(patientInfoService.getPatientProfile(username)).thenReturn(profileDTO);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(username);

        // Perform request using MockMvc
        mockMvc.perform(get("/api/patient/profile")
                        .principal(authentication) // pass Authentication object
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("me"))
//                .andExpect(jsonPath("$.la").value("John Doe"))
                .andExpect(jsonPath("$.email").value("mesherzad@gamil.com"));
    }

}
