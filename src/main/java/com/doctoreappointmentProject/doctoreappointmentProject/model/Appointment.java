package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "Date Must Not Be empty")
    private LocalDate date;

    @Column(name="time")
    @NotNull(message = "time Must Not Be empty")
    private  LocalTime  time;


//    @OneToOne
//    @JoinColumn(name="patient_id",referencedColumnName ="id")
//    private  User patient;
//
//
//    @OneToOne
//    @JoinColumn(name="doctor_id",referencedColumnName ="id")
//    private  User doctorId;
//
//    @OneToOne
//    @JoinColumn(name="disease_id",referencedColumnName ="id")
//    private Diseases disease;



    @ManyToOne
    @JoinColumn(name="patient_id",referencedColumnName ="id")
    private  User patient;


    @ManyToOne
    @JoinColumn(name="doctor_id",referencedColumnName ="id")
    private  User doctorId;

    @ManyToOne
    @JoinColumn(name="disease_id",referencedColumnName ="id")
    @Positive(message = "Please Select Disease")
    @NotNull(message = "Please Select Disease")
    private Diseases disease;


}
