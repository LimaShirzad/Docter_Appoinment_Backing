package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DoctorInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorInfoController {


    private final DoctorInfoService doctorInfoService;

    private Map<String,String> doctorResponse= new HashMap<>();

    public DoctorInfoController(DoctorInfoService doctorInfoService) {

        this.doctorInfoService = doctorInfoService;

    }

    @GetMapping
    public List<DoctorInfoDTO> getDoctor(){

       return doctorInfoService.getAllDoctors();

    }


        @PostMapping("/save")
    public ResponseEntity<Map<String,String>> createDoctor(@Valid @ModelAttribute DoctorInfoDTO doctorInfoSaveDTO,
                                                           @RequestParam(value = "cv",required = false) MultipartFile cv) throws IOException {



            doctorInfoService.saveDoctorInfo(doctorInfoSaveDTO);

            doctorResponse.put("saveDoctor","Account Created Successfully");

            return ResponseEntity.ok(doctorResponse);




        }







}
