package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.model.User;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;


    @InjectMocks
    UserService userService;

    @InjectMocks
    RoleService roleService;


    @Test
    @DisplayName("Test Get All User Form UserRepository")
    void shouldReturnListOf_AllUsers(){

        Roles role1 = new Roles();
        role1.setId(1);
        role1.setRole("Admin");

        Roles role2 = new Roles();
        role2.setId(2);
        role2.setRole("User");

        User user1 = new User(1, "Ali", "Sherzad", "ali@gamil.com", "ali123","1234", "ali.pic".getBytes(), Gender.MALE, role1);
        User user2 = new User(2, "Ahmad", "Ali", "ahmad@gamil.com", "ahmad123","345", "ahmad.pic".getBytes(), Gender.MALE, role2);

        List<User> users = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.getAllUsers();

        UserDTO dto1=result.get(0);

        assertEquals(2, result.size());
//       ============================= test all filed From first List===================================

        assertEquals("Ali",dto1.getFirstName());
        assertEquals("Sherzad",dto1.getLastName());
        assertEquals("ali@gamil.com",dto1.getEmail());
        assertEquals("ali123",dto1.getUserName());
        assertEquals("ali.pic",dto1.getProfilePicture());
        assertEquals(Gender.MALE,dto1.getGender());
        assertEquals("Admin",dto1.getRole());

//        ===================test All filed form second list=======================

        UserDTO dto2=result.get(1);
        assertEquals(2,dto2.getId());
        assertEquals("Ahmad",dto2.getFirstName());
        assertEquals("Ali",dto2.getLastName());
        assertEquals("ahmad@gamil.com",dto2.getEmail());
        assertEquals("ahmad123",dto2.getUserName());
        assertEquals("ahmad.pic",dto2.getProfilePicture());
        assertEquals(Gender.MALE,dto2.getGender());
        assertEquals("User",dto2.getRole());

        verify(userRepository).findAll();


    }


    @Test
    @DisplayName("Test Get User By ID")
    void shouldReturnSingle_User(){

        Roles role2 = new Roles();
        role2.setId(2);
        role2.setRole("User");

       User user = new User(Math.toIntExact(1L), "Ali", "Sherzad", "ali@gamil.com", "ali123","1234", "ali.pic".getBytes(), Gender.MALE, role2);

       when(userRepository.findById(1L)).thenReturn(Optional.of(user));

       UserDTO result=userService.getUserById(1L);

       assertEquals("Ali",result.getFirstName());

       verify(userRepository).findById(1L);


    }


    @Test
    @DisplayName("Should Throw exception when user not found")
    void shouldThrowExceptionWhenUserNotFound(){

        Long userId=1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception= assertThrows(RuntimeException.class,()->{

            userService.getUserById(userId);

        });

        assertEquals("User not found With ID 1" ,exception.getMessage());

        verify(userRepository).findById(userId);

    }

    @Test
    @DisplayName("Test Should Delete User By ID")
    void shouldDeleteUserById(){

        Long userId=1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        userService.deleteUserById(userId);

        verify(userRepository).deleteById(userId);
    }


    @Test
    @DisplayName("Should throw exception if user not found")
    void shouldThrowExceptionIfUserNotExists() {

        Long userId = 2L;

        when(userRepository.existsById(userId)).thenReturn(false);


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUserById(userId);
        });

        assertEquals("User Not Found Withe ID  2", exception.getMessage());


        verify(userRepository, never()).deleteById(userId);

    }

//    @Test
//    @DisplayName("Test GetAllRolesExceptAdmin ")
//    void shouldReturnListOfRole(){
//
//        Roles role1=new Roles(1,"Doctor");
//        Roles role2=new Roles(2,"Patient");
//
//        List<Roles> roles=List.of(role1,role2);
//
//        when(userService.getAllRolesExceptAdmin()).thenReturn(roles);
//
//        verify(roleRepository).findAllExceptAdmin();
//
//    }


}
