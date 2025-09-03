package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import lombok.Data;

@Data


public class UserSaveDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Gender gender;
    private int roleId;
    private byte[] profilePictureUrl;


}
