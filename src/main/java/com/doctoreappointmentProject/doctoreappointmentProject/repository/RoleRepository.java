package com.doctoreappointmentProject.doctoreappointmentProject.repository;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long> {

    boolean existsByRole(String role);


}
