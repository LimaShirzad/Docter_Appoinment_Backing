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
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {


   private final UserRepository userRepository;

   private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final UserException userException;


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

//
//
//        List<UserDTO> userDTOList = new ArrayList<>();
//        for (User user : users) {
//            UserDTO dto = userMapper.toDTO(user); // هماغه کار لکه map کوي
//            userDTOList.add(dto);
//        }


        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());



//        List<User> users=userRepository.findAll();
//        return  users.stream()
//                .map(user -> {
//
//
//                    UserDTO dto=new UserDTO();
//                    dto.setId(user.getId());
//                    dto.setFirstName(user.getFirstName());
//                    dto.setLastName(user.getLastName());
//                    dto.setEmail(user.getEmail());
//                    dto.setUserName(user.getUserName());
//                    dto.setProfilePicture(Arrays.toString(user.getProfilePicture()));
//                    dto.setGender(user.getGender());
//
//
//                    if (user.getProfilePicture() != null) {
//                        dto.setProfilePicture(new String(user.getProfilePicture()));
//                    }
////        =================form role entity===============
//                    if(user.getRole() !=null){
//
//                        dto.setRole(user.getRole().getRole());
//                        dto.setRoleID(user.getId());
//
//                    }
//                    return  dto;
//
//
//                }).collect(Collectors.toList());


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

        userException.checkIPasswordExistThrowException(dto.getPassword());

//        =========================================================

//        ==========================user mape to entity===========
        User user=userMapper.toEntity(dto);


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




