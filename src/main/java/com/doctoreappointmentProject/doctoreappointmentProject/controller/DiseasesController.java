package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DiseasesSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DiseasesShowDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.service.DiseasesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diseases")
public class DiseasesController {


    private final DiseasesService diseasesService;


    public DiseasesController(DiseasesService diseasesService) {
        this.diseasesService = diseasesService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> createDiseas(@Valid @RequestBody DiseasesSaveDTO diseasesSaveDTO) {

          diseasesService.saveDisaes(diseasesSaveDTO);

        Map<String,String> response=new HashMap<>();

        String message="Disease Added SuccessFully ";

        response.put("Disease",message);

        return  ResponseEntity.ok(response);


    }

    @GetMapping("/all")
    public List<DiseasesShowDTO> showAllDiseases() {
        return diseasesService.returnAllDiseases();
    }

//    @GetMapping("/alldisaes")
//    public List<DiseasesShowDTO> showAllDiseases(){
//
//
//
//    }











}
