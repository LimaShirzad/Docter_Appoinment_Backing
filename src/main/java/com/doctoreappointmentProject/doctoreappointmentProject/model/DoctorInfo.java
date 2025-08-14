package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@Entity

@Table(name="doctor_info")

@Data

@AllArgsConstructor

@NoArgsConstructor

public class DoctorInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  int id;


//    ===============education============

    @Size(max=255,message = "education must be less than 255")
    @NotBlank(message = "must not be empty")
    @Column(name="education",nullable = false,length = 255)
    private  String education;


//    ===================experience_year==========

    @Column(name="experience_year",nullable = false)
    private  Integer experienceYear;

//    ============university_name===============

    @Size(max=250,message = "universityName must less than 250")
    @NotBlank(message = "universityName should not be blank")
    @Column(name="university_name",nullable = false,length = 250)
    private  String universityName;



//    =================graduation_year============

    @NotBlank(message = "the Data filed must not be blank")
    @Column(name="graduation_year",nullable = false)
    private LocalDate graduationYear;


//    ===============address===========

    @Size(max=500,message = "address should not be greater than 500")
    @NotBlank(message = "address should not be blank")
    @Column(name="address",nullable = false,length = 500)
    private  String address;


//    =================cv==========

    @Column(name="cv",nullable = false,length = 300)
    private  String cv;


//    =======================accepted==========

    @Size(max=50,message = "the filed must not be greater than 50")
    @Column(name="accepted",length = 50)
    private  String accepted="PENDING";


    @OneToOne
    @JoinColumn(name="user_id" ,referencedColumnName = "id",nullable = false)
    private  User doctor;


    @ManyToOne
    @JoinColumn(name="specialty_id",referencedColumnName = "id",nullable = false)
    private  Specialty specialty;


}
