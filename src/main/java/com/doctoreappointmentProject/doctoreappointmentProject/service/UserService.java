package com.doctoreappointmentProject.doctoreappointmentProject.service;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserSaveDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.mapper.UserMapper;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.exception.UserException;
import jakarta.transaction.Transactional;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {


    @Autowired
   private final UserRepository userRepository;

    @Autowired
   private final RoleRepository roleRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final UserException userException;

    private  final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, UserException userException) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.userException = userException;
    }

//    @Transactional
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
//                    dto.setProfilePicture(user.getProfilePicture());
                    dto.setGender(user.getGender());

//        =================form role entity===============
                    if(user.getRole() !=null){

                        dto.setRole(user.getRole().getRole());
                        dto.setRoleID(user.getId());

                    }
                    return  dto;


                }).collect(Collectors.toList());

//      List<User> users=userRepository.findAll();
//
//      return   userMapper.getAllUserMapper();


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
        userException.checkIfEmailExistThrowException(dto.getEmail());

        userException.checkIfUserNameExistThrowException(dto.getUserName());

        userException.checkIfPasswordExistThrowException(dto.getPassword());

//        =========================================================

//        ==========================user mape to entity===========
        User user=userMapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Roles role = roleRepository.findById((long) dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Invalid Role ID"));
        user.setRole(role);

//        UserUtil.isOnlyLetter(dto.getFirstName());

//        ===========saving data =======================
        User savedUser=userRepository.save(user);

//        =====================return user id and role id for login form===========
        return new UserSaveDTO(user.getId(),savedUser.getRole().getId());

    }


}




