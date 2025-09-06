package com.doctoreappointmentProject.doctoreappointmentProject.model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unit Test For DoctorInfo")
public class DoctorInfoTest {



    @Test
    @DisplayName("Test ID Field")
    void testIdField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setId(1);

        assertEquals(1,doctorInfo.getId());

    }

    @Test
    @DisplayName("Test Education Field")
    void testEducationField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setEducation("PHD");

        assertEquals("PHD",doctorInfo.getEducation());

    }

    @Test
    @DisplayName("Test ExperienceYear Field")
    void testExperienceYearField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setExperienceYear(4);

        assertEquals(4,doctorInfo.getExperienceYear());

    }


    @Test
    @DisplayName("Test UniversityName Field")
    void testUniversityNameField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setUniversityName("Kabul university");

        assertEquals("Kabul university",doctorInfo.getUniversityName());

    }


    @Test
    @DisplayName("Test GraduationYear Field")
    void testGraduationYearField(){

        LocalDate date= LocalDate.of(2025,8,14);

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setGraduationYear(date);

        assertEquals(date,doctorInfo.getGraduationYear());

    }

    @Test
    @DisplayName("Test Address Field")
    void testAddressField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setAddress("kabul");

        assertEquals("kabul",doctorInfo.getAddress());

    }

    @Test
    @DisplayName("Test Cv Field")
    void testCvField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setCv("publish".getBytes());

        assertEquals("publish",doctorInfo.getCv());

    }


    @Test
    @DisplayName("Test Accepted Field")
    void testAcceptedField(){

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setCv("PENDING".getBytes());

        assertEquals("PENDING",doctorInfo.getAccepted());

    }


}
