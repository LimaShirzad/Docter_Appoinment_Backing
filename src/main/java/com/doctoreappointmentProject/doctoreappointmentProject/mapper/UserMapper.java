package com.doctoreappointmentProject.doctoreappointmentProject.mapper;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.AdminProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.stereotype.Component;

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
        dto.setGender(user.getGender());

        if(user.getRole() !=null){

            dto.setRole(user.getRole().getRole());

        }

        return  dto;

    }

    public AdminProfileDTO getAdminProfile(User user){

        AdminProfileDTO adminProfileDTO=new AdminProfileDTO();


        adminProfileDTO.setId(user.getId());
        adminProfileDTO.setFirstName(user.getFirstName());
        adminProfileDTO.setLastName(user.getLastName());
        adminProfileDTO.setEmail(user.getEmail());
        adminProfileDTO.setUsername(user.getUserName());
        adminProfileDTO.setGender(user.getGender());
        adminProfileDTO.setPassword(user.getPassword());
        adminProfileDTO.setProfilePicture(user.getProfilePicture());

//        =================form role entity===============
        if(user.getRole() !=null){

            adminProfileDTO.setRole(user.getRole().getRole());

        }

        return  adminProfileDTO;


    }


    public DoctorInfoClientDTO getDocoterByIdMapper(User user, DoctorInfo doctorInfo) {

        DoctorInfoClientDTO dto=new DoctorInfoClientDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setSpecialty(doctorInfo.getSpecialty().getTitle());
        dto.setEducation(doctorInfo.getEducation());
        dto.setAddress(doctorInfo.getAddress());
        dto.setGraduationYear(doctorInfo.getGraduationYear());
        dto.setExperienceYear(doctorInfo.getExperienceYear());
        dto.setUniversityName(doctorInfo.getUniversityName());
        dto.setAccepted(doctorInfo.getAccepted());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setGender(user.getGender());


        return  dto;



    }
}
