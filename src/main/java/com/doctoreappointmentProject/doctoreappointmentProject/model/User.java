package com.doctoreappointmentProject.doctoreappointmentProject.model;

import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(name="id")
    private  int id;


//   ===================first name==================

    @Size(max=100,message="should be less tha 100")
    @NotBlank(message = "first name should not be empty")
    @Column(name="first_name",nullable = false,length = 100)
    @JsonProperty("first_name")
    private  String firstName;

//    ================last name================

    @Size(max=100,message="should be less tha 100")
    @NotBlank(message = "last name should not be empty")
    @Column(name="last_name",nullable = false,length = 100)
    @JsonProperty("last_name")
    private  String lastName;

//    ==================email=================

    @Size(max=250,message="should be less tha 250")
    @NotBlank(message = "email should not be empty")
    @Email(message = "Invalid Email")
    @Column(name="email",nullable = false,length = 250,unique = true)
    @JsonProperty("email")
    private  String email;

//    =============userName==========

    @Size(min=5,max=100,message="should be less tha 100")
    @NotBlank(message = "userName should not be empty")
    @Column(name="user_name",nullable = false,length = 100,unique = true)
    @JsonProperty("user_name")
    private  String userName;



//    ===================password==================

    @NotBlank(message = "password should not be empty")
    @Column(name="password",nullable = false,unique = true,length = 30)
    @JsonProperty("password")
    private  String password;


//    ===============profilePicture===============

    @Column(name="profile_picture")
    @JsonProperty("profile_picture")
    private  String profilePicture;


//    =================gender==========

    @Enumerated(EnumType.STRING)
    @Column(name="gender",nullable = false)
    @JsonProperty("gender")
    private Gender gender;

//   =================relationship================

    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName = "id",nullable = false)
    private Roles role;




}
