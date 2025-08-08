package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity

@Table(name="diseases")

@AllArgsConstructor

@NoArgsConstructor

public class Diseases {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;


//    =====================title=======================

    @Size(max=100,message = "title must not be grater than 100")
    @Column(name="title",nullable = false,length = 100)
    private  String title;

//    ===================disease_Type==========

    @Size(max=150,message = "title must not be grater than 150")
    @Column(name="disease_Type",nullable = false,length = 150)
    private  String diseaseType;


}
