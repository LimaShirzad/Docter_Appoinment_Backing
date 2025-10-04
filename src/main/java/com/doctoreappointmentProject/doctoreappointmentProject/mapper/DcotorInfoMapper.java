package com.doctoreappointmentProject.doctoreappointmentProject.mapper;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorProfileDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DcotorInfoMapper {

    public DoctorInfo toEntity(DoctorInfoDTO doctorInfoSaveDTO) throws IOException {

        DoctorInfo doctorInfo = new DoctorInfo();



        doctorInfo.setAddress(doctorInfoSaveDTO.getAddress());

        doctorInfo.setEducation(doctorInfoSaveDTO.getEducation());

        doctorInfo.setExperienceYear(doctorInfoSaveDTO.getExperienceYear());

        doctorInfo.setUniversityName(doctorInfoSaveDTO.getUniversityName());

        doctorInfo.setGraduationYear(doctorInfoSaveDTO.getGraduationYear());

        // Mapper
        if (doctorInfoSaveDTO.getCv() != null && !doctorInfoSaveDTO.getCv().isEmpty()) {
            doctorInfo.setCv(doctorInfoSaveDTO.getCv().getBytes());
        }

//        doctorInfo.setCv(doctorInfoSaveDTO.getCv().getBytes());

        return doctorInfo;


    }

    public DoctorInfoDTO toDto(DoctorInfo doctorInfo) {
        DoctorInfoDTO dto = new DoctorInfoDTO();
        dto.setId(doctorInfo.getId());
        dto.setEducation(doctorInfo.getEducation());
        dto.setExperienceYear(doctorInfo.getExperienceYear());
        dto.setUniversityName(doctorInfo.getUniversityName());
        dto.setGraduationYear(doctorInfo.getGraduationYear());
        dto.setAddress(doctorInfo.getAddress());
        dto.setCv(dto.getCv());



        dto.setAccepted(doctorInfo.getAccepted());


        dto.setUserId(doctorInfo.getDoctor().getId());
        dto.setSpecialtyId(doctorInfo.getSpecialty().getId());

        return dto;
    }


    public List<DoctorInfoDTO> toDtoList(List<DoctorInfo> doctorInfos) {
        return doctorInfos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public DoctorProfileDTO toDTO(User user, DoctorInfo doctorInfo) {
        if (user == null || doctorInfo == null) return null;

        DoctorProfileDTO dto = new DoctorProfileDTO();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole().getRole()); // assuming role has a getRole() method
        dto.setPassword(user.getPassword());
        dto.setGender(user.getGender());
        dto.setSpecialty(doctorInfo.getSpecialty().getTitle());
        dto.setEducation(doctorInfo.getEducation());
        dto.setExperienceYear(doctorInfo.getExperienceYear());
        dto.setUniversityName(doctorInfo.getUniversityName());
        dto.setGraduationYear(doctorInfo.getGraduationYear());
        dto.setAddress(doctorInfo.getAddress());
        dto.setAccepted(doctorInfo.getAccepted());
        dto.setProfilePicture(user.getProfilePicture());

        return dto;

    }
}