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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public User saveUser(User user)
    {

        if(userRepository.existsByEmail(user.getEmail())){

            throw  new IllegalArgumentException("Email already exists");

        }

        if(userRepository.existsByPassword(user.getPassword())){

            throw new IllegalArgumentException("Password already exists");

        }
        if(userRepository.existsByUserName(user.getUserName())){

            throw new IllegalArgumentException("UserName already exists");

        }

        if (!ValidationUtil.isOnlyLetters(user.getFirstName())) {
            throw new IllegalArgumentException("firstName should contain only letters");
        }
        if (!ValidationUtil.isOnlyLetters(user.getLastName())) {
            throw new IllegalArgumentException("LastName should contain only letters");
        }


        return userRepository.save(user);
    }

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
                    dto.setProfilePicture(user.getProfilePicture());
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
        dto.setProfilePicture(user.getProfilePicture());
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




