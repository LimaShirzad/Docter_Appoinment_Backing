package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.SpecialtyUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository; // make the fake obejct of reposiotry

    @InjectMocks
    SpecialtyService specialtyService;

    @Test
    @DisplayName("Should throw error when title contains non-letters")
    void shouldThrowError_WhenTitleNotLetters() {
        Specialty specialty = new Specialty();
        specialty.setTitle("Cardio123");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            SpecialtyUtil.isOnlyLetters(specialty.getTitle());
        });

        assertEquals("Specialty name should contain only letters", ex.getMessage());
    }


    @Test
    @DisplayName("Should throw error when specialty already exists")
    void shouldThrowError_WhenSpecialtyAlreadyExists() {

        Specialty specialty= new Specialty();
        specialty.setTitle("Cardiology");

        when(specialtyRepository.existsByTitle("Cardiology")).thenReturn(true); // mean  this record is already exists

        Exception ex=assertThrows(IllegalArgumentException.class,()->{

            specialtyService.saveSpecialty(specialty);

        });

        assertEquals("This Specialty is already Exists",ex.getMessage());

        verify(specialtyRepository,never()).save(any());


    }


    @Test
    @DisplayName("Should save new specialty successfully")
    void shouldSave_WhenValidSpecialty() {

        Specialty specialty = new Specialty();
        specialty.setTitle("Dermatology");


        when(specialtyRepository.existsByTitle("Dermatology")).thenReturn(false);

        assertDoesNotThrow(()->specialtyService.saveSpecialty(specialty));

        verify(specialtyRepository).save(specialty);


    }

    @Test
    @DisplayName("test Delete  specialty ")
    void shouldDeleteSpecialtyBy_Id(){

        Long specialtyById=1L;

        specialtyService.deleteSpecialtyById(specialtyById);

        verify(specialtyRepository).deleteById(specialtyById);


    }


    @Test
    @DisplayName("return specialty by id")
    void shouldReturnSpecialtyById(){

        Specialty specialty=new Specialty(1,"Zokam");

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(specialty));

        Specialty result=specialtyService.getSpecialtyById(1L);

        assertEquals("Zokam",specialty.getTitle());

        verify(specialtyRepository).findById(1L);


    }
    @Test
    void shouldThrowExceptionWhenSpecialtyNotFound(){

        when(specialtyRepository.findById(1L)).thenReturn(Optional.empty());

       assertThrows(IllegalArgumentException.class,()->{

           specialtyService.getSpecialtyById(1L);

       });


    }


    @Test
    @DisplayName("should Return All List Of Specialty")
    void shouldReturnAllSpecialty(){

        List<Specialty> specialties=List.of(

                new Specialty(1,"new Specialty"),
                new Specialty(2,"new version"));

        when(specialtyRepository.findAll()).thenReturn(specialties);

        List<Specialty> result=specialtyService.getAllSpecialty();
//        assertEquals(2,result.size());
        verify(specialtyRepository).findAll();


    }






}