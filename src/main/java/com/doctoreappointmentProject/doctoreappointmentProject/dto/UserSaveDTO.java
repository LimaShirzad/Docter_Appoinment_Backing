package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
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

    @Size(max=100,message="should be less tha 100")
    @NotBlank(message = "first name should not be empty")
    private String firstName;

    @Size(max=100,message="should be less tha 100")
    @NotBlank(message = "last name should not be empty")
    private String lastName;

    @Size(max=250,message="should be less tha 250")
    @NotBlank(message = "email should not be empty")
    @Email(message = "Invalid Email")
    private String email;

    @Size(min=5,max=100,message="should be less tha 100")
    @NotBlank(message = "userName should not be empty")
    private String userName;

    @NotBlank(message = "password should not be empty")
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
