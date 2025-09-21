package com.doctoreappointmentProject.doctoreappointmentProject.mapper;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DcotorInfoMapper {

    public DoctorInfo toEntity(DoctorInfoDTO doctorInfoSaveDTO) throws IOException {

        DoctorInfo doctorInfo=new DoctorInfo();

        doctorInfo.setAddress(doctorInfoSaveDTO.getAddress());

        doctorInfo.setEducation(doctorInfoSaveDTO.getEducation());

        doctorInfo.setExperienceYear(doctorInfoSaveDTO.getExperienceYear());

        doctorInfo.setUniversityName(doctorInfoSaveDTO.getUniversityName());

        doctorInfo.setGraduationYear(doctorInfoSaveDTO.getGraduationYear());

        doctorInfo.setCv(doctorInfoSaveDTO.getCv().getBytes());

        return  doctorInfo;


    }

    public DoctorInfoDTO toDto(DoctorInfo doctorInfo) {
        DoctorInfoDTO dto = new DoctorInfoDTO();
        dto.setId(doctorInfo.getId());
        dto.setEducation(doctorInfo.getEducation());
        dto.setExperienceYear(doctorInfo.getExperienceYear());
        dto.setUniversityName(doctorInfo.getUniversityName());
        dto.setGraduationYear(doctorInfo.getGraduationYear());
        dto.setAddress(doctorInfo.getAddress());
        // dto.setCv(doctorInfo.getCv());
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


//    public List<DoctorInfoDTO> toDtoList(List<DoctorInfo> doctorInfos) {
//        List<DoctorInfoDTO> dtoList = new ArrayList<>();
//        for (DoctorInfo doctor : doctorInfos) {
//            DoctorInfoDTO dto = toDto(doctor);  // convert DoctorInfo → DoctorInfoDTO
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }

}
