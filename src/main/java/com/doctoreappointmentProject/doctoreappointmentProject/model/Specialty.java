package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
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

@NoArgsConstructor

@AllArgsConstructor

public class Specialty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;



//    ============================specialty_name===============

    @Size(max=150,message="Specialty Name Should Not be Grater Than 150")
    @NotBlank(message = "pleas Enter  SpecialtyName ")
    @Column(name="title",nullable = false,unique = true,length = 150)
    private String title;

    @PrePersist
    @PreUpdate

    public  void preSave() {
        this.title = ValidationUtil.cleanString(this.title);
        this.title = ValidationUtil.capitalizeFirstLetter(this.title);

            if (!ValidationUtil.isOnlyLetters(this.title)) {
                throw new IllegalArgumentException("Specialty Name should contain only letters");
            }




    }

//    @OneToMany(mappedBy = "specialty")
//    private List<DoctorInfo> doctorsInfoList;

//    public Specialty(Integer id, String title) {
//        this.id = id;
//        this.title = title;
//    }
}
