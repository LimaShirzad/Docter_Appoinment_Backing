package com.doctoreappointmentProject.doctoreappointmentProject.controller;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.service.RoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;



@WebMvcTest(RoleController.class)
@DisplayName("Tets RoleController")
public class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    @DisplayName("Test CreateRole API")
    void testCreateRoleSuccess() throws Exception {
        Roles roles = new Roles();
        roles.setId(1);
        roles.setRole("Admin");


        Mockito.when(roleService.saveRole(any(Roles.class))).thenReturn(roles);


        mockMvc.perform(post("/api/roles/save")
//                        .contentType("application/json") I can also write like that
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"Admin\"}"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.role").value("Admin"));
    }

    @Test
    @DisplayName("Save Role Max size 50 Validation")
    void testCreateRoleMaxSize() throws Exception {

        String longRole = "A".repeat(51);

        mockMvc.perform(post("/api/roles/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"" + longRole + "\"}"))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.errors.role")
                                .value("The role Name sholud not be grater than 50"));


    }

    @Test
    @DisplayName("Save Role  Not-Null  validation")
    void testSaveRoleNotNull() throws Exception {


        mockMvc.perform(post("/api/roles/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"role\":\"\"}"))
                .andExpect(status().isBadRequest())
                 .andExpect(jsonPath("$.errors.role").value("the role should not be blank"));

    }

    @Test
    @DisplayName("Save Role - Only letters validation")
    void testCreateRoleOnlyLetters() throws Exception {

        Mockito.when(roleService.saveRole(any(Roles.class))).thenThrow(new IllegalArgumentException("Role name should contain only letters"));


        mockMvc.perform(post("/api/roles/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"role\":\"Admin123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Role name should contain only letters"));

    }

    @Test
    @DisplayName("Create Role - Unique validation")
    void testCreateRoleUnique() throws  Exception{

        Mockito.when(roleService.saveRole(any(Roles.class))).thenThrow(new IllegalArgumentException("Role already exists"));

        mockMvc.perform(post("/api/roles/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"role\":\"Admin\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Role already exists"));

    }

    @Test
    @DisplayName("Test Delete Role")
    void shouldDeleteRole() throws  Exception{

        Long roleId=1L;

        doNothing().when(roleService).deleteRole(roleId);

        mockMvc.perform(delete("/api/roles/{id}",roleId)).andExpect(status().isOk());

        verify(roleService,times(1)).deleteRole(roleId);



    }

    @Test
    @DisplayName("Test Get Role By Id")
    void testGetRoleById() throws Exception{

        Long roleId=1L;
          Roles role=new Roles();

          role.setId(1);
          role.setRole("Admin");

          Mockito.when(roleService.getRoleById(roleId)).thenReturn(role);

          mockMvc.perform(get("/api/roles/{id}",roleId))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(roleId))
                  .andExpect(jsonPath("$.role").value("Admin"));

    }
    @Test
    @DisplayName("PUT /api/roles/{id} - Update Role Success")
    void testUpdateRole() throws Exception {

        Long roleId = 1L;
        Roles updatedRole = new Roles();
        updatedRole.setId(Math.toIntExact(roleId));
        updatedRole.setRole("Manager");

        Mockito.when(roleService.updateRole(eq(roleId), any(Roles.class)))
                .thenReturn(updatedRole);

        mockMvc.perform(put("/api/roles/{id}", roleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"Manager\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(roleId))
                .andExpect(jsonPath("$.role")
                 .value("Manager"));
    }

    @Test
    void shouldReturnListOfAllRoles() throws Exception{

        List<Roles> roles=List.of
                (new Roles(1,"Admin"),
                (new Roles(2,"User")));

         Mockito.when(roleService.getAllRoles()).thenReturn(roles);

         mockMvc.perform(get("/api/roles"))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.length()").value(2))
                 .andExpect(jsonPath("$[0].role").value("Admin"))
                 .andExpect(jsonPath("$[1].role").value("User"));



    }








}