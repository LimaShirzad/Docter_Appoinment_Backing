package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {

    boolean existsByRole(String role);
    @Query("SELECT r FROM Roles r WHERE r.role <> 'Admin'")
    List<Roles> findAllExceptAdmin();


}
