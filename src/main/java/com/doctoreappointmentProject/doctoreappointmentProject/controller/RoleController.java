package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Roles saveRole(@Valid @RequestBody Roles role) {

        return roleService.saveRole(role);
    }
//    @PostMapping("/save")
//    public ResponseEntity<Map<String, Object>> saveRole(@Valid @RequestBody Roles role) {
//        Roles saved = roleService.saveRole(role);
//        return ResponseEntity.ok(Map.of(
//                "status", "success",
//                "role", saved.getRole(),
//                "message", "Role saved successfully"
//        ));
//    }

//    public ResponseEntity<?> saveRole(@Valid @RequestBody Roles role, BindingResult result) {
//
//        // Handle validation errors from annotations
//        if (result.hasErrors()) {
//            String errorMessage = result.getFieldError().getDefaultMessage();
//            return ResponseEntity.badRequest().body(Map.of("role", errorMessage));
//        }
//
//        try {
//            Roles savedRole = roleService.saveRole(role);
//            return ResponseEntity.ok(Map.of("message", "Role saved successfully", "role", savedRole.getRole()));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Map.of("role", e.getMessage()));
//        }
//    }

//
//    @PostMapping("/save")
//    public Roles createRoles(  @RequestBody  Roles roles)
//    {
//
//        return  roleService.saveRole(roles);
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

//    }


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
