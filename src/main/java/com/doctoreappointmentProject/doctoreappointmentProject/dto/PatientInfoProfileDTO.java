package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfoProfileDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String bloodGroup;
    private Gender gender;
    private byte[] profilePicture;

}
