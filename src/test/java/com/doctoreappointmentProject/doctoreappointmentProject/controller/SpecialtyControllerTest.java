package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.service.SpecialtyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Tets UserController")
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper; // used to convert Java object to JSON


    @MockBean
    private SpecialtyService specialtyService;


    @Test
    void shouldCreateSpecialty() throws Exception {
        // Step 1: create fake specialty
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setTitle("Cardiology");

        // Step 2: mock the service (void method)
        doNothing().when(specialtyService).saveSpecialty(specialty);

        // Step 3: perform POST request with JSON body
        mockMvc.perform(post("/api/specialty/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specialty)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Specialty Created Successfully"));
    }

    @Test
    void shouldDeleteSpecialty() throws Exception {
        // Step 1: mock service (void method)
        doNothing().when(specialtyService).deleteSpecialtyById(1L);

        // Step 2: perform DELETE request
        mockMvc.perform(delete("/api/specialty/1")) // <-- your endpoint
                .andExpect(status().isCreated()) // check status
                .andExpect(jsonPath("$.message").value("Specialty Deleted Successfully"));
    }

    @Test
    void shouldReturnSpecialtyById() throws Exception {
        // Step 1: create fake Specialty
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setTitle("Cardiology");

        // Step 2: mock the service
        when(specialtyService.getSpecialtyById(1L)).thenReturn(specialty);

        // Step 3: perform GET request and check response
        mockMvc.perform(get("/api/specialty/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Cardiology"));
    }


    @Test
    void shouldUpdateSpecialty() throws Exception {
        // Step 1: create input Specialty
        Specialty inputSpecialty = new Specialty();
        inputSpecialty.setTitle("Neurology");

        // Step 2: create returned Specialty (after update)
        Specialty updatedSpecialty = new Specialty();
        updatedSpecialty.setId(1);
        updatedSpecialty.setTitle("Neurology");

        // Step 3: mock service
        when(specialtyService.updateSpecialtyById(1L, inputSpecialty)).thenReturn(updatedSpecialty);

        // Step 4: perform PUT request
        mockMvc.perform(put("/api/specialty/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputSpecialty)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Neurology"));
    }


//    @Test
//    void shouldReturnAllSpecialtiesPaginated() throws Exception {
//        // Step 1: create fake specialties
//        Specialty s1 = new Specialty();
//        s1.setId(1);
//        s1.setTitle("Cardiology");
//
//        Specialty s2 = new Specialty();
//        s2.setId(2);
//        s2.setTitle("Neurology");
//
//        List<Specialty> specialties = List.of(s1, s2);
//
//        // Step 2: create a Page object
//        Page<Specialty> page = new PageImpl<>(specialties, PageRequest.of(0, 5), specialties.size());
//
//        // Step 3: mock the service
//        when(specialtyService.getAllSpecialty(PageRequest.of(0,5))).thenReturn(page);
//
//        // Step 4: perform GET request
//        mockMvc.perform(get("/api/specialty/all")
//                        .param("page", "0")
//                        .param("size", "5")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(1))
//                .andExpect(jsonPath("$.content[0].title").value("Cardiology"))
//                .andExpect(jsonPath("$.content[1].id").value(2))
//                .andExpect(jsonPath("$.content[1].title").value("Neurology"));
//    }

    @Test
    void shouldReturnAllSpecialties() throws Exception {
        // Step 1: create some fake specialties
        Specialty s1 = new Specialty();
        s1.setId(1);
        s1.setTitle("Cardiology");

        Specialty s2 = new Specialty();
        s2.setId(2);
        s2.setTitle("Neurology");

        List<Specialty> specialties = List.of(s1, s2);

        // Step 2: create a simple Page object
        Page<Specialty> page = new PageImpl<>(specialties);

        // Step 3: mock the service
        when(specialtyService.getAllSpecialty(PageRequest.of(0,5))).thenReturn(page);

        // Step 4: perform GET request
        mockMvc.perform(get("/api/specialty/all")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Cardiology"))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].title").value("Neurology"));
    }



    @Test
    void shouldReturnAllSpecialtiess() throws Exception {
        // Step 1: create fake specialties
        Specialty s1 = new Specialty();
        s1.setId(1);
        s1.setTitle("Cardiology");

        Specialty s2 = new Specialty();
        s2.setId(2);
        s2.setTitle("Neurology");

        List<Specialty> specialties = List.of(s1, s2);

        // Step 2: mock the service
        when(specialtyService.getAllSpecialty()).thenReturn(specialties);

        // Step 3: perform GET request
        mockMvc.perform(get("/api/specialty/all_Specialty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Cardiology"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Neurology"));
    }




}
