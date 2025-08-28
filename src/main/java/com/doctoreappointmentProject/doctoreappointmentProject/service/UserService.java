package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.util.ValidationUtil;
import jakarta.transaction.Transactional;
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


}




