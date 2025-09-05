package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data


public class PatientInfoDTO {


    private  int id;

    @NotBlank(message = "Blood Group Should Not Be null")
    @Size(max = 5, message = "blood group should not be greater than 5")
    @Column(name = "blood_group", length = 5)
    private String bloodGroup;
    private Integer userId;


}
