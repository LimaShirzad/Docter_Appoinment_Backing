package com.doctoreappointmentProject.doctoreappointmentProject.controller;


import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
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


    @PostMapping("/save")
    public Map<String, String> saveUser(
            @Valid @ModelAttribute UserSaveDTO dto,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
    ) throws IOException {
//        try {
//            // set profile picture if uploaded
//            if (profilePicture != null && !profilePicture.isEmpty()) {
                dto.setProfilePictureUrl(profilePicture.getBytes());
//            }

            // save user
            userService.saveUser(dto);

            // return success
            return Map.of(
                    "status", "success",
                    "message", "User Saved Successfully ✅"
            );

//        } catch (Exception e) {
//            // return error
//            return Map.of(
//                    "status", "error",
//                    "message", e.getMessage()
//            );
//        }
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> saveUser(
//            @Valid @ModelAttribute UserSaveDTO dto,
//            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture
//    ) throws IOException {
//
//        try {
//            byte[] pictureBytes = null;
//            if (profilePicture != null && !profilePicture.isEmpty()) {
//                pictureBytes = profilePicture.getBytes();
//            }
//
//            userService.saveUser(dto, pictureBytes);
//
//
//            return ResponseEntity.ok(Map.of(
//                    "status", "success",
//                    "message", "User Saved Successfully ✅"
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of(
//                    "status", "error",
//                    "message", e.getMessage()
//            ));
//        }
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
