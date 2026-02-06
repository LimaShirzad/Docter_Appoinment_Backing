package com.doctoreappointmentProject.doctoreappointmentProject.dto;


import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

    public class UserPatientUpdateDTO {

        // ===== User fields =====
        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @Email
        private String email;

        @NotBlank
        private String userName;

        private Gender gender;

        // ===== Patient fields =====
        @NotBlank
        private String bloodGroup;
    }


