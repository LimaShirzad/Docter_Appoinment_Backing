package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DoctorInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Test DoctorInfo Controller")
public class DoctorInfoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  DoctorInfoService doctorInfoService;

    @Test
    void shouldReturnAllDoctors() throws Exception {
        // Step 1: create fake doctor data
        DoctorInfoSaveDTO doctor = new DoctorInfoSaveDTO();
        doctor.setId(1);
        doctor.setAddress("kabul");
        doctor.setEducation("MBBS");
        doctor.setAccepted("PINDING");

        List<DoctorInfoSaveDTO> fakeDoctors = List.of(doctor);

        // Step 2: mock the service
        when(doctorInfoService.getAllDoctors()).thenReturn(fakeDoctors);

        // Step 3: perform GET request and check response
        mockMvc.perform(get("/api/doctors")) // <-- your actual URL mapping
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].address").value("kabul"))
                .andExpect(jsonPath("$[0].education").value("MBBS"))
                .andExpect(jsonPath("$[0].accepted").value("PINDING"));
    }

    @Test
    void shouldCreateDoctor() throws Exception {
        // Step 1: mock the service to do nothing (since it returns void)
        doNothing().when(doctorInfoService).saveDoctorInfo(any());

        // Step 2: create a fake CV file
        MockMultipartFile cvFile = new MockMultipartFile(
                "cv",
                "cv.pdf",
                "application/pdf",
                "fake pdf content".getBytes()
        );

        // Step 3: perform multipart POST request
        mockMvc.perform(multipart("/api/doctors/save")
                        .file(cvFile)
                        .param("firstName", "Ahmad")
                        .param("lastName", "Shirzad")
                        .param("education", "MBBS")
                        .param("experienceYear", "5")
                        .param("universityName", "Kabul University")
                        .param("graduationYear", LocalDate.of(2018,6,15).toString())
                        .param("address", "Kabul")
                        .param("specialtyId", "1")
                        .param("accepted", "PENDING")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saveDoctor").value("Account Created Successfully"));
    }


    @Test
    void shouldReturnDoctorProfile() throws Exception {
        // Step 1: create a fake doctor profile
        DoctorProfileDTO profile = new DoctorProfileDTO();
//        profile.set(1);
        profile.setFirstName("Ahmad");
        profile.setLastName("Shirzad");
        profile.setSpecialty("Cardiology");

        // Step 2: mock Authentication
        Authentication auth = Mockito.mock(Authentication.class);
        when(auth.getName()).thenReturn("ahmad123");

        // Step 3: mock the service to return the fake profile
        when(doctorInfoService.getDoctorProfile("ahmad123")).thenReturn(profile);

        // Step 4: perform GET request with mock Authentication
        mockMvc.perform(get("/api/doctors/profile")
                        .principal(auth)) // pass the Authentication object
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ahmad"))
                .andExpect(jsonPath("$.lastName").value("Shirzad"))
                .andExpect(jsonPath("$.specialty").value("Cardiology"));
    }




}
