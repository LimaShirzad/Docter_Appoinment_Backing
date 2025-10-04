package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

public class DiseasesSaveDTO {


    @Size(max=100,message = "title must not be grater than 100")
    private  String title;


    @Size(max=150,message = "title must not be grater than 150")
    private  String diseaseType;



}
