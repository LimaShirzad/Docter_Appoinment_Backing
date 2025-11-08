package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DiseasesSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DiseasesShowDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Diseases;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DiseasesRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DiseasesService {


    private final DiseasesRepository diseasesRepository;

    public DiseasesService(DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }


    public void saveDisaes( DiseasesSaveDTO diseasesSaveDTO) {


        if (!ValidationUtil.isOnlyLetters(diseasesSaveDTO.getTitle())) {
            throw new IllegalArgumentException("title  Name should contain only letters");
        }

        if (diseasesRepository.existsByTitle(diseasesSaveDTO.getTitle())) {
            throw new IllegalArgumentException("title already exists");
        }


        Diseases diseases =new Diseases();

        diseases.setTitle(diseasesSaveDTO.getTitle());

        diseasesRepository.save(diseases);


    }

//    public List<DiseasesShowDTO> returnAllDiseases(){
//
//        Diseases diseases=new Diseases();
//
//        diseasesRepository.findAll();
//
//
//        DiseasesShowDTO diseasesShowDTO=new DiseasesShowDTO();
//
//        diseasesShowDTO.setTitle(diseases.getTitle());
//
//
//
//
//
//    }


    public List<DiseasesShowDTO> returnAllDiseases() {
        List<Diseases> diseasesList = diseasesRepository.findAll();

        List<DiseasesShowDTO> dtoList = new ArrayList<>();

        for (Diseases disease : diseasesList) {
            DiseasesShowDTO dto = new DiseasesShowDTO();
            dto.setId(disease.getId());      // assuming your entity has getId()
            dto.setTitle(disease.getTitle());
            dtoList.add(dto);
        }

        return dtoList;
    }







}
