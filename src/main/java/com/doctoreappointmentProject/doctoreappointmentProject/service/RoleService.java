package com.doctoreappointmentProject.doctoreappointmentProject.service;


import com.doctoreappointmentProject.doctoreappointmentProject.model.UserRole;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private  final UserRoleRepository userRoleRepository;

    @Autowired

    public RoleService(UserRoleRepository userRoleRepository) {

        this.userRoleRepository = userRoleRepository;

    }

    public UserRole addRole(String roleName) {
        UserRole role = new UserRole();
         role.setRole(roleName);
        return userRoleRepository.save(role);
    }






}
