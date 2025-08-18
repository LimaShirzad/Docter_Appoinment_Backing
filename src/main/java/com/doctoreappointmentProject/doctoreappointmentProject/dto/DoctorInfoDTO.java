package com.doctoreappointmentProject.doctoreappointmentProject.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoDTO {

    private int id;
    private String education;
    private Integer experienceYear;
    private String universityName;
    private LocalDate graduationYear;
    private String address;
    private String cv;
    private String accepted;

    // doctor (from User table)
    private int doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorEmail;
    private String doctorUserName;

    // specialty (from Specialty table)
    private int specialtyId;
    private String specialtyTitle;
}

