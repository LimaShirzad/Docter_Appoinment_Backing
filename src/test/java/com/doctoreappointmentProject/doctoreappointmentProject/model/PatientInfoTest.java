package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PatientInfo  Test")
public class PatientInfoTest {

    @Test
    @DisplayName("Test Constructor and getters")
    void testConstructorAndGetters() {


        Roles role =new Roles(5,"Admin");

        User user =new User(1,"Umar","Sherzad","umar@gmail.com"
                ,"umar123","1234","publish", Gender.MALE,role);

        PatientInfo patientInfo=new PatientInfo(1,"A",user);


        assertEquals(1,patientInfo.getId());

        assertEquals("A",patientInfo.getBloodGroup());

        assertEquals(user,patientInfo.getPatient());

    }
    @Test
    @DisplayName("Test ID Field")
    void testIdField(){

        PatientInfo patientInfo=new PatientInfo();

        patientInfo.setId(1);

        assertEquals(1,patientInfo.getId());

    }

    @Test
    @DisplayName("Test BloodGroup Field")
    void testBloodGroupField(){

        PatientInfo patientInfo=new PatientInfo();

        patientInfo.setBloodGroup("A");

        assertEquals("A",patientInfo.getBloodGroup());

    }


}