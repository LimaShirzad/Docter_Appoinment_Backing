package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;







    @Transactional
    public  void deleteUserById(Long id){

       if(!userRepository.existsById(id)) {

            throw new RuntimeException("User Not Found Withe ID  " + id);
        }

        userRepository.deleteById(id);

    }

    public List<UserDTO> getAllUsers(){

        List<User> users=userRepository.findAll();

        return  users.stream()
                .map(user -> {

                    UserDTO dto=new UserDTO();
                    dto.setId(user.getId());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setEmail(user.getEmail());
                    dto.setUserName(user.getUserName());
//                    dto.setProfilePicture(Arrays.toString(user.getProfilePicture()));
                    dto.setGender(user.getGender());

//        =================form role entity===============
                    if(user.getRole() !=null){

                        dto.setRole(user.getRole().getRole());

                    }
                    return  dto;


                }).collect(Collectors.toList());

    }


    public UserDTO getUserById(Long id) {


        User user= userRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("User not found With ID " + id));

        UserDTO dto=new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
//        dto.setProfilePicture(user.getProfilePicture());
        dto.setGender(user.getGender());

//        =================form role entity===============
        if(user.getRole() !=null){

            dto.setRole(user.getRole().getRole());

        }
        return  dto;



    }

    public List<Roles> getAllRolesExceptAdmin(){

       return roleRepository.findAllExceptAdmin();

    }


    @Transactional
    public void saveUser(UserSaveDTO dto) {

        if(userRepository.existsByEmail(dto.getEmail())){

            throw  new IllegalArgumentException("This Email is Already Exist");

        }

        if(userRepository.existsByPassword(dto.getPassword())){

            throw new IllegalArgumentException("This Password is Already Exist");

        }

        if(userRepository.existsByUserName(dto.getUserName())){

            throw new IllegalArgumentException("This UserName is Already Exist");

        }


        User user = new User();

        // Set basic fields
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());

        // Set profile picture if available
        if (dto.getProfilePictureUrl() != null) {
            user.setProfilePicture(dto.getProfilePictureUrl());
        }

        // Set role (make sure role exists)
        Roles role = roleRepository.findById((long) dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role ID"));
        user.setRole(role);

        // Save to database
        userRepository.save(user);
    }

//    public User saveUser(UserSaveDTO dto, byte[] profilePicture) {
//
////         fetch role from DB
//        Roles role = roleRepository.findById((long) dto.getRoleId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Role ID"));
//
//        // create user entity
//        User user = new User();
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        user.setEmail(dto.getEmail());
//        user.setUserName(dto.getUserName());
//        user.setPassword(dto.getPassword());
//        user.setGender(dto.getGender());
//        user.setRole(role);                // set the fetched role
//        user.setProfilePicture(profilePicture);
//
//        return userRepository.save(user);  // save to DB
//    }
}




