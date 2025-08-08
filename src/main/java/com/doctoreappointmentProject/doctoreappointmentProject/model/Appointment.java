package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity

@Table(name="appointments")

@Data

@AllArgsConstructor

@NoArgsConstructor


public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;


    @Column(name="date")
    private LocalDate date;

    @Column(name="time")
    private  LocalTime  time;


    @OneToOne
    @JoinColumn(name="patient_id",referencedColumnName ="id")
    private  User patient;


    @OneToOne
    @JoinColumn(name="doctor_id",referencedColumnName ="id")
    private  User doctorId;

    @OneToOne
    @JoinColumn(name="disease_id",referencedColumnName ="id")
    private Diseases disease;


}
