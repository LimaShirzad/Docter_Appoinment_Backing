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

    @GetMapping("/hi")
    public  String show()
    {
          return "Hello";
    }


    @PostMapping("/save")
    public Roles createRoles(  @RequestBody  Roles roles)
    {

        return  roleService.saveRole(roles);
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
