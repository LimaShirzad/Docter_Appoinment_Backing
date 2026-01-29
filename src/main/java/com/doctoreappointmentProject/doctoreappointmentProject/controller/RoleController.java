package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private final RoleService roleService;

//    private  final RolesUtil rolesUtil;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
//        this.rolesUtil = rolesUtil;
    }

//    @GetMapping("/hi")
//    public  String show()
//    {
//          return "Hello";
//    }


    @PostMapping("/save")
    public Roles createRole(@Valid @RequestBody Roles role) {

        return roleService.saveRole(role);
    }
//getRole
    @GetMapping
    public List<Roles> getRoles()
    {
          return  roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public  void deleteRole(@PathVariable Long id){

        roleService.deleteRole(id);

    }

    @GetMapping("/{id}")
    public Roles getRole(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    // Update role by ID
    @PutMapping("/{id}")
    public Roles updateRole(@PathVariable Long id,@Valid @RequestBody Roles role) {
        return roleService.updateRole(id, role);
    }

}
