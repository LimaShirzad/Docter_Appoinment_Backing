package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity

@Table(name="diseas")

@AllArgsConstructor

@NoArgsConstructor

public class Diseas {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="diseas_id")

    private  int diseas_id;


//    =====================title=======================

    @Size(max=100,message = "title must not be grater than 100")

    @Column(name="title",nullable = false,length = 100)

    private  String title;

//    ===================deseas_Type==========

    @Size(max=150,message = "title must not be grater than 150")

    @Column(name="deseas_Type",nullable = false,length = 150)

    private  String deseas_Type;











}
