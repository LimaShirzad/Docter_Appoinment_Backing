package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;




    public Roles saveRole(Roles role)
    {
        if(!ValidationUtil.isOnlyLetters(role.getRole())){

            throw new
                    IllegalArgumentException("Role name should contain only letters");

        }

        if (roleRepository.existsByRole(role.getRole())) {
            throw new IllegalArgumentException("Role already exists");
        }

        return  roleRepository.save(role);


    }
    public List<Roles> getAllRoles(){

        return  roleRepository.findAll();

    }


    public void deleteRole(Long id) {

        roleRepository.deleteById(id);

    }

    public Roles getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    // Update existing role
    public Roles updateRole(Long id, Roles updatedRole) {
        Roles existingRole = getRoleById(id); // re-use method
        if (roleRepository.existsByRole(updatedRole.getRole()) &&
                !existingRole.getRole().equalsIgnoreCase(updatedRole.getRole())) {
            throw new IllegalArgumentException("Role already exists");
        }

        existingRole.setRole(updatedRole.getRole());
        return roleRepository.save(existingRole);
    }
}
