package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {

    private  final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {

        this.specialtyRepository = specialtyRepository;

    }

    public void saveSpecialty(Specialty specialty){

        if(!ValidationUtil.isOnlyLetters(specialty.getTitle())){

            throw new
                    IllegalArgumentException("Specialty name should contain only letters");

        }

        if(specialtyRepository.existsByTitle(specialty.getTitle())){

            throw  new IllegalArgumentException("This Specialty is already Exists");

        }

         specialtyRepository.save(specialty);

    }


    public void deleteSpecialtyById(Long id){

         specialtyRepository.deleteById(id);


    }


    public Specialty getSpecialtyById(Long id) {

             return specialtyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Specialty not found"));

    }

    public Page<Specialty> getAllSpecialty(Pageable pageable){

          return specialtyRepository.findAll(pageable);

    }

    public Specialty updateSpecialtyById(Long id,  Specialty updateSpecialty) {


        Specialty existingSpecialty = getSpecialtyById(id); // re-use method
        if (specialtyRepository.existsByTitle(updateSpecialty.getTitle()) &&
                !existingSpecialty.getTitle().equalsIgnoreCase(updateSpecialty.getTitle())) {
            throw new IllegalArgumentException("Specialty already exists");
        }

        existingSpecialty.setTitle(updateSpecialty.getTitle());
        return specialtyRepository.save(existingSpecialty);
    }

//        Specialty existingUser=specialtyRepository.findById(id).orElseThrow(()->new RuntimeException("Specialty Not Found" + id));
//
//        existingUser.setTitle(updateSpecialty.getTitle());
//
//        return  specialtyRepository.save(existingUser);



}
