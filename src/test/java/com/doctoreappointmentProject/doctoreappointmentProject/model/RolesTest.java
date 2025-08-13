package com.doctoreappointmentProject.doctoreappointmentProject.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolesTest {


    @Test
    void testConstructorAndGetters(){

        Roles roles=new Roles(1,"ADMIN");

//        TEST
        assertEquals(1,roles.getId());
        assertEquals("ADMIN",roles.getRole());

    }

    @Test
    void testSetters(){

        Roles roles =new Roles();

        roles.setId(1);
        roles.setRole("USER");

        assertEquals(1,roles.getId());
        assertEquals("USER",roles.getRole());



    }
}
