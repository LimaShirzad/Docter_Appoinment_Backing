package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.netty.util.internal.StringUtil;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="roles")

@Data

@NoArgsConstructor

@AllArgsConstructor

public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;


    @NotBlank(message = "the role should not be blank")
    @Column(name="role" ,nullable = false,unique = true,length = 50)
//    @JsonProperty("role")
    private  String role;




}
