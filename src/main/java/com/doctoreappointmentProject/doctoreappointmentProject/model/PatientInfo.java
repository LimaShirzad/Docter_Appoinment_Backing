package com.doctoreappointmentProject.doctoreappointmentProject.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity

@Table(name="patient_info")

@Data

@AllArgsConstructor

@NoArgsConstructor

public class PatientInfo {


//   ======================= primary key to patient table ======================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;


//    =================blood_group====================

    @Size(max=5,message = "blood group should not be greater than 5")
    @Column(name="blood_group",nullable = false,length = 5)
    private  String bloodGroup;


//    ========relationship withe user table============

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User patient;


}
