package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.DoctorInfo;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsById(int user);
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);

    Optional<User> findByUserName(String username);


//    Optional<DoctorInfo> findByUserId(Long userId);


}
