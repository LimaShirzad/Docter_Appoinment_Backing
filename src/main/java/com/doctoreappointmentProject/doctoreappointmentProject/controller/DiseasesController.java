package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.service.DiseasesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diseas")
public class DiseasesController {


    private final DiseasesService diseasesService;


    public DiseasesController(DiseasesService diseasesService) {
        this.diseasesService = diseasesService;
    }







}
