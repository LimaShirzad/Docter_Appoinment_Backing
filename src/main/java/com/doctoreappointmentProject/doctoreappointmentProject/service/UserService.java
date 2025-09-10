package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.UserMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import exception.UserException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


   private final UserRepository userRepository;

   private final RoleRepository roleRepository;

    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

//    @Transactional
    public  void deleteUserById(Long id){

       if(!userRepository.existsById(id)) {

            throw new RuntimeException("User Not Found Withe ID  " + id);
        }


        userRepository.deleteById(id);

    }

    public List<UserDTO> getAllUsers(){

      return   userMapper.getAllUserMapper();


    }


    public UserDTO getUserById(Long id) {

        User user= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found With ID " + id));

       return userMapper.getUserById(user);


    }

    public List<Roles> getAllRolesExceptAdmin(){

       return roleRepository.findAllExceptAdmin();

    }


    @Transactional
    public UserSaveDTO saveUser(UserSaveDTO dto) {


//       ==================validation for user filed=========
        UserException.checkIfEmailExistThrowException(dto.getEmail());

        UserException.checkIfUserNameExistThrowException(dto.getUserName());

        UserException.checkIPasswordExistThrowException(dto.getPassword());

//        =========================================================

//        ==========================user mape to entity===========
        User user=userMapper.toEntity(dto);

//        UserUtil.isOnlyLetter(dto.getFirstName());

//        ===========saving data =======================
        User savedUser=userRepository.save(user);

//        =====================return user id and role id for login form===========
        return new UserSaveDTO(user.getId(),savedUser.getRole().getId());

    }


}




