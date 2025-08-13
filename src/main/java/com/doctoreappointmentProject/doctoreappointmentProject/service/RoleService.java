package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;



    public  boolean isRoleExists(String name)
    {
        return   roleRepository.existsByRole(name);

    }

    public void saveRole(Roles role)
    {
        roleRepository.save(role);

    }
    public List<Roles> getAllRoles(){

        return  roleRepository.findAll();

    }


    public void deleteRole(Long id) {

        roleRepository.deleteById(id);

    }
}
