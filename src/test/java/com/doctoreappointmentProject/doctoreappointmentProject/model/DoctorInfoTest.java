package com.doctoreappointmentProject.doctoreappointmentProject.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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




}
