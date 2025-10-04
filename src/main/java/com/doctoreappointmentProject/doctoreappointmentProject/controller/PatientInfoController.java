package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.PatientInfoProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.PatientInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*")
public class PatientInfoController {

    private  final PatientInfoService patientInfoService;
    private final Map<String,String> patientResponse=new HashMap<>();


    public PatientInfoController( PatientInfoService patientInfoService) {
        this.patientInfoService = patientInfoService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String,String>> createPatient(@Valid @RequestBody PatientInfoDTO patientInfoDTO){


        patientInfoService.savePatientInfo(patientInfoDTO);

        patientResponse.put("success","Your Account Created Successfully");

        return ResponseEntity.ok(patientResponse);

    }


    @GetMapping("/profile")
    public ResponseEntity<?> getPateintProfile(Authentication authentication) {
        String username = authentication.getName();
        PatientInfoProfileDTO profile = patientInfoService.getPatientProfile(username);
        return ResponseEntity.ok(profile);
    }




}
