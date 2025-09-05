package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name = "id")
    private Integer id;


//    =================blood_group====================

    @NotBlank(message = "Blood Group Should Not Be null")
    @Size(max = 5, message = "blood group should not be greater than 5")
    @Column(name = "blood_group", length = 5)
    private String bloodGroup;


//    ========relationship withe user table============

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User patient;

    @PrePersist
    @PreUpdate


    public void preSave() {
        this.bloodGroup = ValidationUtil.cleanString(this.bloodGroup);
        this.bloodGroup = ValidationUtil.capitalizeFirstLetter(this.bloodGroup);

    }
}