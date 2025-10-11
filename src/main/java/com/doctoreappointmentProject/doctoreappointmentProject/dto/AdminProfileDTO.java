package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminProfileDTO {

    private int id;
    private  String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Gender gender;
    private byte[] profilePicture;
    private String role;



}
