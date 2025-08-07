package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

@Table(name="specialty")

@Data

@AllArgsConstructor

@NoArgsConstructor

public class Specialty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="specialty_id")

    private  int specialty_id;



//    ============================specialty_name===============

    @Size(max=150,message="specialty Name sholud not be grater than 150")

    @NotBlank(message = "pleas enter the specialtyName ")

    @Column(name="specialty_name",nullable = false,unique = true,length = 150)

    private String specialty_name;

    @OneToMany(mappedBy = "specialty")

    private List<Docter> docters;










}
