package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class DiseasesSaveDTO {

    @NotBlank(message = "title should not be blank")
    @Size(max=100,message = "title must not be grater than 100")
    private  String title;




}
