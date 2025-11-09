package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.AppointmentSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final  AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveAppointment(@Valid @RequestBody AppointmentSaveDTO dto) {
        appointmentService.takeAppointment(dto);
        return ResponseEntity.ok("Appointment saved successfully");
    }




}
