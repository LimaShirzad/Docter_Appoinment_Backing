package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorInfoRepository extends JpaRepository<DoctorInfo,Integer> {


    Optional<DoctorInfo> findByDoctor(User username);

    @Query("SELECT new com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO(" +
            "u.id, " +
            "u.firstName, " +
            "u.lastName, " +
            "u.email, " +
            "s.title, " +            // Specialty title
            "d.education, " +
            "d.experienceYear, " +
            "d.universityName, " +
            "d.accepted, " +
            "d.graduationYear, " +
            "d.address, " +
            "u.gender, " +
            "u.profilePicture" +
            ") " +
            "FROM DoctorInfo d " +
            "JOIN d.doctor u " +
            "JOIN d.specialty s")
    List<DoctorInfoClientDTO> findAllDoctor();


    @Query("SELECT new com.doctoreappointmentProject.doctoreappointmentProject.dto.DoctorInfoClientDTO(" +
            "u.id, " +
            "u.firstName, " +
            "u.lastName, " +
            "u.email, " +
            "s.title, " +
            "d.education, " +
            "d.experienceYear, " +
            "d.universityName, " +
            "d.accepted, " +
            "d.graduationYear, " +
            "d.address, " +
            "u.gender, " +
            "u.profilePicture" +
            ") " +
            "FROM DoctorInfo d " +
            "JOIN d.doctor u " +
            "JOIN d.specialty s " +
            "WHERE u.id = :id")
    Optional<DoctorInfoClientDTO> findDoctorById(@Param("id") int id);



    Page<DoctorInfo> findAll(Pageable pageable);


        Optional<DoctorInfo> findByDoctorId(Long userId);

}