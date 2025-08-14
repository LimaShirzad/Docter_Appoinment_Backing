package com.doctoreappointmentProject.doctoreappointmentProject.model;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.relation.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("User Model Test")
public class UserTest {

    @Test
    @DisplayName("Test Constructor")
    void testConstructorAndGetters(){

        Roles role=new Roles(1,"Admin");


        User user=new User(1,
                "Ali",
                "Sherzad",
                "ali@gmail.com",
                "ali123",
                "123",
                "publish",
                Gender.MALE,role);

        assertEquals(1,user.getId());

        assertEquals("Ali",user.getFirstName());

        assertEquals("Sherzad",user.getLastName());

        assertEquals("ali@gmail.com",user.getEmail());

        assertEquals("ali123",user.getUserName());

        assertEquals("123",user.getPassword());

        assertEquals("publish",user.getProfilePicture());

        assertEquals(Gender.MALE,user.getGender());

        assertEquals(role,user.getRole());


    }


    @Test
    @DisplayName("Test ID Filed")
    void testIdField(){

        User user=new User();

        user.setId(1);

        assertEquals(1,user.getId());

    }

    @Test
    @DisplayName("Test FirstName Filed")
    void testFirstNameField(){

        User user=new User();

        user.setFirstName("Ali");

        assertEquals("Ali",user.getFirstName());

    }

    @Test
    @DisplayName("Test LastName Filed")
    void testLastNameField(){

        User user=new User();

        user.setLastName("Sherzad");

        assertEquals("Sherzad",user.getLastName());

    }

    @Test
    @DisplayName("Test Email Filed")
    void testEmailField(){

        User user=new User();

        user.setEmail("ali@gmail.com");

        assertEquals("ali@gmail.com",user.getEmail());

    }

    @Test
    @DisplayName("Test UserName Filed")
    void testUserNameField(){

        User user=new User();

        user.setUserName("ali123");

        assertEquals("ali123",user.getUserName());

    }

    @Test
    @DisplayName("Test password Filed")
    void testPasswordField(){

        User user=new User();

        user.setPassword("1234");

        assertEquals("1234",user.getPassword());

    }

    @Test
    @DisplayName("Test password Filed")
    void testProfilePictureField(){

        User user=new User();

        user.setProfilePicture("publish");

        assertEquals("publish",user.getProfilePicture());

    }

    @Test
    @DisplayName("Test Gender Filed")
    void testGenderField(){

        User user=new User();

        user.setGender(Gender.MALE);

        assertEquals(Gender.MALE,user.getGender());

    }

    @Test
    @DisplayName("Test role Filed")
    void testRoleField(){

        Roles role=new Roles(2,"User");
        User user=new User();

        user.setRole(role);

        assertEquals(role,user.getRole());

    }

}