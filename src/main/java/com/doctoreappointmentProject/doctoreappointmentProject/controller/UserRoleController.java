package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.model.UserRole;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRoleController {


    private  final RoleService roleService;

    public UserRoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/add")
    public UserRole addNewRole(@RequestBody String roleName) {

        return roleService.addRole(roleName);
    }


}
