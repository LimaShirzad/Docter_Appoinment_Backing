package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.service.AuthService;
import com.doctoreappointmentProject.doctoreappointmentProject.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.List;

//import static javax.swing.UIManager.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Test Client Controller ")
public class ClientControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;



    @Test
    void shouldReturnAllDoctors() throws Exception {
        // Step 1: Create fake doctor data
        DoctorInfoClientDTO doctor = new DoctorInfoClientDTO(
                1,
                "Ahmad",
                "Shirzad",
                "ahmad@example.com",
                "Cardiology",
                "MBBS",
                5,
                "Kabul University",
                "Accepted",
                LocalDate.of(2018, 6, 15),
                "Kabul, Afghanistan",
                Gender.MALE,
                null // no profile picture for now
        );

        List<DoctorInfoClientDTO> fakeDoctors = List.of(doctor);

        // Step 2: Tell the mock service to return this fake list
        when(clientService.getAllDoctor()).thenReturn(fakeDoctors);

        // Step 3: Perform GET request and check the response
        mockMvc.perform(get("/api/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Ahmad"))
                .andExpect(jsonPath("$[0].lastName").value("Shirzad"))
                .andExpect(jsonPath("$[0].specialty").value("Cardiology"))
                .andExpect(jsonPath("$[0].education").value("MBBS"))
                .andExpect(jsonPath("$[0].experienceYear").value(5))
                .andExpect(jsonPath("$[0].universityName").value("Kabul University"))
                .andExpect(jsonPath("$[0].accepted").value("Accepted"));
    }

    @Test
    void shouldReturnDoctorById() throws Exception {
        // Step 1: create fake doctor
        DoctorInfoClientDTO doctor = new DoctorInfoClientDTO(
                1,
                "Ahmad",
                "Shirzad",
                "ahmad@example.com",
                "Cardiology",
                "MBBS",
                5,
                "Kabul University",
                "Accepted",
                LocalDate.of(2018, 6, 15),
                "Kabul, Afghanistan",
                Gender.MALE,
                null
        );

        // Step 2: mock the service
        when(clientService.getDoctorById(1)).thenReturn(doctor);

        // Step 3: call the endpoint and check JSON response
        mockMvc.perform(get("/api/client/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ahmad"))
                .andExpect(jsonPath("$.lastName").value("Shirzad"))
                .andExpect(jsonPath("$.specialty").value("Cardiology"))
                .andExpect(jsonPath("$.education").value("MBBS"));
    }




}
