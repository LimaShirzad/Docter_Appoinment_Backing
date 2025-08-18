package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorInfoService {

     private final DoctorInfoRepository doctorInfoRepository;

    public DoctorInfoService(DoctorInfoRepository doctorRepository) {

        this.doctorInfoRepository = doctorRepository;

    }

    public List<DoctorInfoDTO> getAllDoctors() {
        List<DoctorInfo> doctors = doctorInfoRepository.findAll();
        List<DoctorInfoDTO> doctorDTOs = new ArrayList<>();

        for (DoctorInfo d : doctors) {
            DoctorInfoDTO dto = new DoctorInfoDTO();

            // DoctorInfo fields
            dto.setId(d.getId());
            dto.setEducation(d.getEducation());
            dto.setExperienceYear(d.getExperienceYear());
            dto.setUniversityName(d.getUniversityName());
            dto.setGraduationYear(d.getGraduationYear());
            dto.setAddress(d.getAddress());
            dto.setCv(d.getCv());
            dto.setAccepted(d.getAccepted());

            // User fields
            if (d.getDoctor() != null) {
                dto.setDoctorId(d.getDoctor().getId());
                dto.setDoctorFirstName(d.getDoctor().getFirstName());
                dto.setDoctorLastName(d.getDoctor().getLastName());
                dto.setDoctorEmail(d.getDoctor().getEmail());
            }

            // Specialty fields
            if (d.getSpecialty() != null) {
                dto.setSpecialtyId(d.getSpecialty().getId());
                dto.setSpecialtyTitle(d.getSpecialty().getTitle());
            }

            doctorDTOs.add(dto);
        }

        return doctorDTOs;
    }




}
