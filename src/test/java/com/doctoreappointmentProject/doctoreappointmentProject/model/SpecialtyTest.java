package com.doctoreappointmentProject.doctoreappointmentProject.model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialtyTest {

    @Test
    @DisplayName("Test Constructor and getters")
    void testConstructorAndGetters() {

        Specialty specialty = new Specialty(1, "Cardiology");

        assertEquals(1, specialty.getId());
        assertEquals("Cardiology", specialty.getTitle());

    }

    @Test
    @DisplayName("Test setters")
    void testSetters() {

        Specialty specialty = new Specialty();

        specialty.setId(1);
        specialty.setTitle("Neurology");

        assertEquals(1, specialty.getId());
        assertEquals("Neurology", specialty.getTitle());


    }
}