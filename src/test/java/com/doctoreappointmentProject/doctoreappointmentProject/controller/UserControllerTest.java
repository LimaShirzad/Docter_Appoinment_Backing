package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.dto.UserDTO;
import com.doctoreappointmentProject.doctoreappointmentProject.enums.Gender;
import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import com.doctoreappointmentProject.doctoreappointmentProject.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//@WebMvcTest(UserController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Tets UserController")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;



    @Test
    @DisplayName("Test User Delete API When Success")
    void deleteUserById_whenUserExists_returnsSuccessMessage() throws Exception {

        Long userId = 1L;
      doNothing().when(userService).deleteUserById(userId);


        mockMvc.perform(delete("/api/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userDeleteMessage").value("User Deleted Successfully"));

        verify(userService,times(1)).deleteUserById(userId);

    }


    @Test
    @DisplayName("Test /{id} User Delete API When Fail")
    void testDeleteUserById_whenUserNotExists_returnsFailMessage() throws Exception{

        Long userId=1L;

        doThrow(new RuntimeException("User Not Found Withe ID  " + userId)).when(userService).deleteUserById(userId);

        mockMvc.perform(delete("/api/user/{id}",userId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User Not Found Withe ID  " + userId));

        verify(userService,times(1)).deleteUserById(userId);


    }

    @Test
    @DisplayName("Test  /get/{id} User By ID")
    void testGetUserByID_shouldReturnSingle_ListOfUser_successMessage() throws Exception {

        Long userId = 1L;

        // Create a DTO to return
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Math.toIntExact(userId));
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setUserName("johndoe");
        userDTO.setProfilePicture("profile.jpg".getBytes());
        userDTO.setGender(Gender.MALE);
        userDTO.setRole("Admin");

        // Tell Mockito what to return when the service is called
        when(userService.getUserById(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/api/user/get/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.userName").value("johndoe"))
                .andExpect(jsonPath("$.profilePicture").value("profile.jpg"))
                .andExpect(jsonPath("$.gender").value("MALE"))
                .andExpect(jsonPath("$.role").value("Admin"));



    }

    @Test
    @DisplayName("Test  /get/{id}   User Not Found")
    void testGetUserByID_whenUserNotExists_shouldReturnBadRequest() throws Exception{

        Long userId = 1L;

        // Mock the service to throw exception when user is not found
        when(userService.getUserById(userId))
                .thenThrow(new RuntimeException("User not found With ID " + userId));

        mockMvc.perform(get("/api/user/get/{id}", userId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User not found With ID " + userId));


    }


    @Test
    @DisplayName("GET /api/user/all - should return list of users")
    void testGetAllUsers_shouldReturnListOfUsers() throws Exception {


        List<UserDTO> users = List.of(
                new UserDTO(1, "John", "Doe", "john@example.com", "johndoe", null, Gender.MALE, 1,"Admin"),
                new UserDTO(2, "Jane", "Smith", "jane@example.com", "janesmith", null, Gender.FEMALE,1, "User")
        );

       when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    @DisplayName("GET /api/user/all - should return message when no users exist")
    void testGetAllUsers_shouldReturnMessageIfEmpty() throws Exception {

        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("No User Found"));
    }

    @Test
    @DisplayName("Get api/user/roles should return list of roles withe out Admin")
    void testGetAllRoles() throws Exception{

        List<Roles> role=List.of(
                new Roles(1,"Doctor"),
                new Roles(2,"Patient")

        );

        when(userService.getAllRolesExceptAdmin()).thenReturn(role);

        mockMvc.perform(get("/api/user/roles"))
                .andExpect(status().isOk());
    }




}
