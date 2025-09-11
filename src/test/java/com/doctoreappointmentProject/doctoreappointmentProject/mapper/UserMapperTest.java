package com.doctoreappointmentProject.doctoreappointmentProject.mapper;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserMapperTest {

    private  final  UserMapper userMapper=new UserMapper();


    @Test
    void testToEntity_should_mapperUserSaveDTOtoUser(){


        UserSaveDTO userSaveDTO=new UserSaveDTO();

        userSaveDTO.setFirstName("Lima");

        userSaveDTO.setLastName("Sherzad");

        User user=userMapper.toEntity(userSaveDTO);

        assertEquals("Lima",user.getFirstName());

        assertEquals("Sherzad",user.getLastName());


    }

    @Test
    void testGetUserByIdMapperShould(){

        User user=new User();

        user.setUserName("lima1234");

        user.setLastName("sherzad");

        UserDTO userDTO=userMapper.getUserById(user);

        assertThat(userDTO).isNotNull();

        assertEquals("lima1234",userDTO.getUserName());

        assertEquals("sherzad",userDTO.getLastName());


    }




}
