package com.doctoreappointmentProject.doctoreappointmentProject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentSaveDTO {

    private Long doctorId;
    private Long patientId;
    @Positive(message = "Please Select Disease")
    @NotNull(message = "Please Select Disease")
    private Long diseaseId;

    @NotNull(message = "Date Must Not Be empty")
    private LocalDate date;
    @NotNull(message = "time Must Not Be empty")
    private LocalTime time;

}
