package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user)
    {
           return userRepository.save(user);
    }

    public  void deleteUser(Long id){

       if(!userRepository.existsById(id)) {

           throw new RuntimeException("User Not Found Withe ID  " + id);
       }

        userRepository.deleteById(id);

    }


    public List<User> getAllUser(){

        return userRepository.findAll();

    }


    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found With ID " + id));



    }
}

















