package com.doctoreappointmentProject.doctoreappointmentProject.mapper;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class UserMapper {

    public User toEntity(UserSaveDTO userSaveDTO){


        User user = new User();

        user.setFirstName(userSaveDTO.getFirstName());
        user.setLastName(userSaveDTO.getLastName());
        user.setEmail(userSaveDTO.getEmail());
        user.setUserName(userSaveDTO.getUserName());
        user.setPassword(userSaveDTO.getPassword());
        user.setGender(userSaveDTO.getGender());
        user.setProfilePicture(userSaveDTO.getProfilePictureUrl());


        return user;

    }


    public UserDTO getUserById(User user){


        UserDTO dto=new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
//        dto.setProfilePicture(user.getProfilePicture());
        dto.setGender(user.getGender());

//        =================form role entity===============
        if(user.getRole() !=null){

            dto.setRole(user.getRole().getRole());

        }

        return  dto;


    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
        dto.setGender(user.getGender());

        // عکس (byte[] → String)
        if (user.getProfilePicture() != null) {
            dto.setProfilePicture(new String(user.getProfilePicture()));
        }

        // رول
        if (user.getRole() != null) {
            dto.setRole(user.getRole().getRole());
            dto.setRoleID(user.getRole().getId());
        }

        return dto;
    }



}
