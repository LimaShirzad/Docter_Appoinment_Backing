package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.service.SpecialtyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/specialty")
@CrossOrigin(origins = "*" )
public class SpecialtyController {

    private  final SpecialtyService specialtyService;

    private final Map<String,String> specialtyResponse=new HashMap<>();

    public SpecialtyController(SpecialtyService specialtyService) {

        this.specialtyService = specialtyService;

    }

    @PostMapping("/save")
    public ResponseEntity<Map<String,String>> createSpecialty(@Valid @RequestBody Specialty specialty){

        specialtyService.saveSpecialty(specialty);

        specialtyResponse.put("message","Specialty Created Successfully");

        return new ResponseEntity<>(specialtyResponse,HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String,String>> deleteSpecialtyById(@PathVariable Long id){

        specialtyService.deleteSpecialtyById(id);

        specialtyResponse.put("message","Specialty Deleted Successfully");

        return new ResponseEntity<>(specialtyResponse,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public  Specialty getSpecialtyById(@PathVariable Long id){

        return  specialtyService.getSpecialtyById(id);

    }

    @PutMapping("/{id}")
    public Specialty updateRole(@PathVariable Long id, @Valid @RequestBody Specialty specialty) {
        return specialtyService.updateSpecialtyById(id, specialty);
    }


    @GetMapping("/all")
    public Page<Specialty> getAllSpecialty(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size){

        return specialtyService.getAllSpecialty(PageRequest.of(page,size));


    }









}
