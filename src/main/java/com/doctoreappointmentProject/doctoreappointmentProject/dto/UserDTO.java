package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private int id;
    private  String firstName;
    private String lastName;
    private String email;
    private  String userName;
    private  String profilePicture;
    private Gender gender;

//    ==============get data from role=========
    private int roleID;
    private  String role;


}
