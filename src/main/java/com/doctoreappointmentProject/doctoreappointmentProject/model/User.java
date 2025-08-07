package com.doctoreappointmentProject.doctoreappointmentProject.model;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="users")

@Data

@NoArgsConstructor

@AllArgsConstructor


public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="user_id")

    private  int user_id;


//   ===================first name==================

    @Size(max=100,message="sholud be less tha 100")

    @NotBlank(message = "first name sholud not be empty")

    @Column(name="first_name",nullable = false,length = 100)

    private  String first_name;





//    ===========last name================
    @Size(max=100,message="sholud be less tha 100")

    @NotBlank(message = "last name sholud not be empty")

    @Column(name="last_name",nullable = false,length = 100)

    private  String last_name;



//    ================email=================


    @Size(max=250,message="sholud be less tha 250")

    @NotBlank(message = "email sholud not be empty")

    @Email(message = "Invalid Email")

    @Column(name="email",nullable = false,length = 250,unique = true)

    private  String email;


//    =============user_name==========


    @Size(max=100,message="sholud be less tha 100")

    @NotBlank(message = "userName sholud not be empty")

    @Column(name="user_name",nullable = false,length = 100,unique = true)

    private  String user_name;



//    ===================password==================


    @NotBlank(message = "password sholud not be empty")

    @Column(name="password",nullable = false,unique = true)

    private  Long password;


//    ===============profile_picture===============

    @Column(name="profile_picture")

    private  String profile_picture;



//    =================gender==========

    @Enumerated(EnumType.STRING)

    @Column(name="gender",nullable = false)

    private Gender gender;



//   =================reltionship================

    @ManyToOne
    @JoinColumn(name="role_fk",referencedColumnName = "role_id",nullable = false)
    private  Role role_refrence;



//    ===========reltionship withe docter table=========
    @OneToOne(mappedBy = "users")

    private  Docter docter;

//    =============reltionship withe patien table========


    @OneToOne(mappedBy = "users")

    private Patient patient;









}
