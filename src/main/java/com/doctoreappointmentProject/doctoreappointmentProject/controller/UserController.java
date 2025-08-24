package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private final Map<String,String> userResponse=new HashMap<>();


    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



//    @PostMapping("/save")
//    public ResponseEntity<Map<String,String>> createUser( @RequestParam String firstName,
//                                                          @RequestParam String lastName ,
//                                                          @RequestParam String email,
//                                                          @RequestParam String userName,
//                                                          @RequestParam String password,
//                                                          @RequestParam Gender gender,
//                                                          @RequestParam Long role,
//                                                          @RequestParam("profilePicture")MultipartFile profilePicture) {
//
////
//
//
//        userResponse.put("userSaveMessage","User Save Successfully");
//
//
//        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
//
//    }





    @GetMapping("/roles")
    public List<Roles> getAllRoles(){

        return  userService.getAllRolesExceptAdmin();

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Map<String,String>> deleteUserById(@PathVariable Long id){

         userService.deleteUserById(id);

        userResponse.put("userDeleteMessage","User Deleted Successfully");

        return ResponseEntity.ok(userResponse);


    }


    @GetMapping("/all")
    public  ResponseEntity<Object> getAllUser(){

          List<UserDTO> users=userService.getAllUsers();

          if(users.isEmpty()){
                  userResponse.put("message","No User Found");
                  return  ResponseEntity.ok(userResponse);
          }
          return ResponseEntity.ok(users);

    }

    @GetMapping("/get/{id}")
    public UserDTO getUserId(@PathVariable Long id){

        return userService.getUserById(id);

    }










}
