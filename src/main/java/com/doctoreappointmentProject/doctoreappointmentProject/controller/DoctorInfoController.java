package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorInfoController {


    private final DoctorInfoService doctorInfoService;

    public DoctorInfoController(DoctorInfoService doctorInfoService) {

        this.doctorInfoService = doctorInfoService;

    }

    @GetMapping
    public List<DoctorInfoDTO> getDoctor(){

       return doctorInfoService.getAllDoctors();


    }






}
