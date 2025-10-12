package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoctorInfoClientDTO {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;  // Specialty title
    private String education;
    private Integer experienceYear;
    private String universityName;
    private  String accepted;

    private LocalDate graduationYear;
    private String address;
    private Gender gender;

    private byte[] profilePicture;



}
