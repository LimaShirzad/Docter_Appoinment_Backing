package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.SpecialtyUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    private  final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {

        this.specialtyRepository = specialtyRepository;

    }

//    @Transactional
//    @CachePut(value = "specialties", key = "#specialty.id")
    public void saveSpecialty(Specialty specialty){

        SpecialtyUtil.isOnlyLetters(specialty.getTitle());

//        SpecialtyException.checkIfSpecialtyIsAlreadyExist(specialty.getTitle());


        if(specialtyRepository.existsByTitle(specialty.getTitle())){

            throw  new IllegalArgumentException("This Specialty is already Exists");

        }

         specialtyRepository.save(specialty);

    }

//    @CacheEvict(value = "specialties", key = "#id")
    public void deleteSpecialtyById(Long id){

         specialtyRepository.deleteById(id);


    }

    @Transactional
//    @Cacheable(value = "specialties", key = "#id")
    public Specialty getSpecialtyById(Long id) {

             return specialtyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Specialty not found"));

    }

    public Page<Specialty> getAllSpecialty(Pageable pageable){

          return specialtyRepository.findAll(pageable);

    }

    @Transactional
//    @CachePut(value = "specialties", key = "#id")
    public Specialty updateSpecialtyById(Long id,  Specialty updateSpecialty) {


        Specialty existingSpecialty = getSpecialtyById(id); // re-use method
        if (specialtyRepository.existsByTitle(updateSpecialty.getTitle()) &&
                !existingSpecialty.getTitle().equalsIgnoreCase(updateSpecialty.getTitle())) {
            throw new IllegalArgumentException("Specialty already exists");
        }

        existingSpecialty.setTitle(updateSpecialty.getTitle());
        return specialtyRepository.save(existingSpecialty);
    }

    public List<Specialty> getAllSpecialty(){

        return  specialtyRepository.findAll();

    }



}
