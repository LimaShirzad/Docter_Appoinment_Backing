package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="patient")

@Data

@AllArgsConstructor

@NoArgsConstructor

public class Patient {


//    primary key to patient table
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="patient_id")

    private  int patient_id;


//    =================blood_group====================

    @Size(max=50,message = "blood group should not be greater than 5")

    @Column(name="blood_group",nullable = false,length = 50)

    private  String blood_group;


//    ========reltionship withe user table============

    @OneToOne
    @JoinColumn(name="user_id_fk")

    private User users;






















}
