package com.doctoreappointmentProject.doctoreappointmentProject.model;


import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="diseases")

@Data

@AllArgsConstructor

@NoArgsConstructor

public class Diseases {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;


//    =====================title=======================

    @Size(max=100,message = "title must not be grater than 100")
    @NotBlank(message = "title should not be blank")
    @Column(name="title",nullable = false,length = 100)
    private  String title;


    @PrePersist
    @PreUpdate

    public  void preSave() {
        this.title = ValidationUtil.cleanString(this.title);
        this.title = ValidationUtil.capitalizeFirstLetter(this.title);


    }

}
