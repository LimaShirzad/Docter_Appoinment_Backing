package com.doctoreappointmentProject.doctoreappointmentProject.model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Unit test for DiseasesType ")
public class DiseasesTest {

    @Test
    @DisplayName("Test Constructor")
    void testConstructor(){

        Diseases diseases=new Diseases(1,"flew","new");

        assertEquals(1,diseases.getId());

        assertEquals("flew",diseases.getTitle());

        assertEquals("new",diseases.getDiseaseType());

    }

    @Test
    @DisplayName("Test Title Field")
    void testTitleField(){

        Diseases diseases=new Diseases();

        diseases.setTitle("flew");

        assertEquals("flew",diseases.getTitle());

    }

    @Test
    @DisplayName("Test DiseaseType Field")
    void testDiseaseTypeField(){

        Diseases diseases=new Diseases();

        diseases.setDiseaseType("new sickness");

        assertEquals("new sickness",diseases.getDiseaseType());


    }

}
