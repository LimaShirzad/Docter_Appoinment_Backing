package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity

@Table(name="docter_info")

@Data

@AllArgsConstructor

@NoArgsConstructor


public class Docter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="docter_info_id")

    private  int docter_info_id;


//    ===============education============

    @Size(max=255,message = "education must be less than 255")

    @NotBlank(message = "must not be empyt")

    @Column(name="education",nullable = false,length = 255)

    private  String education;



//    ===================experience_year==========

    @Column(name="experience_year",nullable = false)

    private  int experience_year;


//    ============university_name===============

    @Size(max=250,message = "universityName must less than 250")

    @NotBlank(message = "universityName sholud not be blank")

    @Column(name="university_name",nullable = false,length = 250)

    private  String university_name;



//    =================graduation_year============


    @NotBlank(message = "the Data filed must not be blank")

    @Column(name="graduation_year",nullable = false)

    private LocalDate graduation_year;


//    ===============address===========

    @Size(max=500,message = "addres sholud not be greater than 500")

    @NotBlank(message = "addres should not be blank")

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

    @JoinColumn(name="user_fk" ,referencedColumnName = "user_id",nullable = false)

    private  User users;


    @ManyToOne

    @JoinColumn(name="specialty_fk",referencedColumnName = "specialty_id",nullable = false)

    private  Specialty specialty;


























}
