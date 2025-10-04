package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String role;
    private String specialty;
    private String education;
    private Integer experienceYear;
    private String universityName;
    private LocalDate graduationYear;
    private String address;
    private String accepted;
    private String password;
    private String username;
    private Gender gender;
    private byte[] profilePicture;
}
