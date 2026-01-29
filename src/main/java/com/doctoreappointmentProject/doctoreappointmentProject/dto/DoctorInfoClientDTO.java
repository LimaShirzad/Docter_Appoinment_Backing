package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DoctorInfoClientDTO {


    private int id;
//    private int doctorId;
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
//    private Status status;
    private  byte[] cv;

    private byte[] profilePicture;



}
