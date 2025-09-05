package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor

public class UserSaveDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Gender gender;
    private int roleId;
    private  String roleName;
    private byte[] profilePictureUrl;


    public UserSaveDTO(int id, int roleId) {
        this.id = id;
        this.roleId = roleId;
    }


}
