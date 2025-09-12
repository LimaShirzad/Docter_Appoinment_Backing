package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.DcotorInfoMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Specialty;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.DoctorInfoRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.SpecialtyRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorInfoService {

     private final DoctorInfoRepository doctorInfoRepository;
     private  final UserRepository userRepository;
     private  final SpecialtyRepository specialtyRepository;
     private  final DcotorInfoMapper doctorInfoMapper;


    public DoctorInfoService(DoctorInfoRepository doctorRepository, UserRepository userRepository, SpecialtyRepository specialtyRepository, DcotorInfoMapper doctorInfoMapper) {

        this.doctorInfoRepository = doctorRepository;

        this.userRepository = userRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorInfoMapper = doctorInfoMapper;
    }

    public List<DoctorInfoDTO> getAllDoctors() {

        List<DoctorInfo> doctors = doctorInfoRepository.findAll();
        return doctorInfoMapper.toDtoList(doctors);
//        List<DoctorInfo> doctors = doctorInfoRepository.findAll();
//        List<DoctorInfoSaveDTO> doctorDTOs = new ArrayList<>();
//
//        for (DoctorInfo d : doctors) {
//            DoctorInfoSaveDTO dto = new DoctorInfoSaveDTO();
//
//            // DoctorInfo fields
//            dto.setId(d.getId());
//            dto.setEducation(d.getEducation());
//            dto.setExperienceYear(d.getExperienceYear());
//            dto.setUniversityName(d.getUniversityName());
//            dto.setGraduationYear(d.getGraduationYear());
//            dto.setAddress(d.getAddress());
////            dto.setCv(d.getCv());
//            dto.setAccepted(d.getAccepted());
//
//
//            doctorDTOs.add(dto);
//        }
//
//        return doctorDTOs;
    }

    @Transactional
    public  void saveDoctorInfo(DoctorInfoDTO doctorInfoSaveDTO) throws IOException {


          DoctorInfo doctorInfo=  doctorInfoMapper.toEntity(doctorInfoSaveDTO);


        User user = userRepository.findById(Long.valueOf(doctorInfoSaveDTO.getUserId()))
                .orElseThrow(() -> new RuntimeException("No User Found"));

        Specialty specialty=specialtyRepository.findById(Long.valueOf(doctorInfoSaveDTO.getSpecialtyId()))
                        .orElseThrow(()->new RuntimeException("No Specialty Found"));



          doctorInfo.setDoctor(user);

          doctorInfo.setSpecialty(specialty);


          doctorInfoRepository.save(doctorInfo);





    }







}
