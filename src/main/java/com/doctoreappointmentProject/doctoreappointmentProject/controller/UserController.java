package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import com.doctoreappointmentProject.doctoreappointmentProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private final Map<String,String> userResponse=new HashMap<>();


    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Map<String,String>> createUser(@Valid @RequestBody User user) {

        User saveUser=userService.saveUser(user);

        userResponse.put("userSaveMessage","User Save Successfully");


        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);

    }


    @GetMapping("/roles")
    public List<Roles> getAllRoles(){

        return  roleService.getAllRoles();

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String,String>> deleteUserById(@PathVariable Long id){

         userService.deleteUser(id);

        userResponse.put("userDeleteMessage","User Deleted Successfully");

        return ResponseEntity.ok(userResponse);


    }

    @GetMapping("/{all_User}")
    public  ResponseEntity<Object> getAllUser(){

          List<UserDTO> users=userService.getAllUsers();

          if(users.isEmpty()){
                  userResponse.put("message","No User Found");
          }
          return ResponseEntity.ok(users);

    }

    @GetMapping("/get/{id}")
    public UserDTO getUserId(@PathVariable Long id){

        return userService.getUserById(id);

    }










}
