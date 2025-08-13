package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import com.doctoreappointmentProject.doctoreappointmentProject.util.RolesUtil;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    private  final RolesUtil rolesUtil;

    public RoleController(RoleService roleService, RolesUtil rolesUtil) {
        this.roleService = roleService;
        this.rolesUtil = rolesUtil;
    }

    @GetMapping("/hi")
    public  String show()
    {
          return "Hello";
    }


    @PostMapping("/save")
    public Roles createRoles(  @RequestBody @Valid Roles roles)
    {

        return  roles;
//        Map<String,String> errors=new HashMap<>();
//
//        if(!ValidationUtil.isNotEmpty(roles.getRole())){
//
//            errors.put("name","Role Name cannot be null");
//        }
//
//        roles.setRole(ValidationUtil.cleanString(roles.getRole()));
//
//        if(roleService.isRoleExists(roles.getRole())){
//
//            errors.put("name","Role must be uniqu");
//
//        }
//        if(!errors.isEmpty()){
//              return  ResponseEntity.badRequest().body(errors);
//        }
//
//
//        roleService.saveRole(roles);
//
//        return  ResponseEntity.ok(Map.of("message","Saved role"));





////        String errorMessage=rolesUtil.validateRole(roles);
////
////        if(errorMessage !=null){
////            return  ResponseEntity.badRequest().body(errorMessage);
////        }
//
//        Roles saveRoles=roleService.saveRole(roles);
//        return  ResponseEntity.ok(saveRoles);
//

    }


    @GetMapping
    public List<Roles> getRoles()
    {
          return  roleService.getAllRoles();
    }

    @DeleteMapping("/{id}")
    public  void deleteRole(@PathVariable Long id){

        roleService.deleteRole(id);

    }




}
