package com.doctoreappointmentProject.doctoreappointmentProject.service;

import com.doctoreappointmentProject.doctoreappointmentProject.model.Roles;
import com.doctoreappointmentProject.doctoreappointmentProject.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository; //making fake object of RoleRepo

    @InjectMocks
    RoleService roleService; // the real object of RoleService

    @Test
    void  testSaveRole() {

        when(roleRepository.existsByRole("Admin")).thenReturn(false);

        Roles savedRole = new Roles();
        savedRole.setId(1);
        savedRole.setRole("Admin");
        when(roleRepository.save(savedRole)).thenReturn(savedRole);

        Roles result = roleService.saveRole(savedRole);

        assertEquals(1, result.getId());
        assertEquals("Admin", result.getRole());

        verify(roleRepository).save(savedRole);



    }


    @Test
    void testSaveRole_invalidName_throwsException() {

        Roles role = new Roles();
        role.setRole("Admin123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roleService.saveRole(role);
        });


        assertEquals("Role name should contain only letters", exception.getMessage());
    }

    @Test
    void testSaveRole_duplicateRole_throwsException() {
        Roles role = new Roles();
        role.setRole("Admin");

        when(roleRepository.existsByRole("Admin")).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roleService.saveRole(role);
        });

        assertEquals("Role already exists", exception.getMessage());
    }

    @Test
    @DisplayName("Test Delete Role By ID")
    void shouldDeleteRoelById(){

        Long id=1L;

        roleService.deleteRole(id);

     verify(roleRepository, times(1)).deleteById(id);



    }
    @Test
    @DisplayName("Test Get All Role")
    void shouldReturnAllRoles(){

        List<Roles> roles= List.of(
          new Roles(1,"ADMIN"),
          new Roles(2,"USER"));

        when(roleRepository.findAll()).thenReturn(roles);

        List<Roles> result=roleService.getAllRoles();

        assertEquals(2,result.size());
        assertEquals("ADMIN",result.get(0).getRole());


        verify(roleRepository).findAll();

    }

    @Test
    @DisplayName("Test Get Role By ID")
    void shouldReturnRoleById(){

        Roles role=new Roles(1,"ADMIN");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Roles result=roleService.getRoleById(1L);

        assertEquals("ADMIN",result.getRole());

        verify(roleRepository).findById(1L);

    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound(){

        when(roleRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,()->{

            roleService.getRoleById(2L);
        });


    }


    @Test
    void testUpdateRole_Success() {
        // Fake existing role
        Roles existingRole = new Roles(1, "User");
        Roles updatedRole = new Roles(1, "Admin");

        // Stubbing repository
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.existsByRole("Admin")).thenReturn(false);
        when(roleRepository.save(existingRole)).thenReturn(updatedRole);

        // Call service
        Roles result = roleService.updateRole(1L, updatedRole);

        // Check
        assertEquals("Admin", result.getRole());
        verify(roleRepository).save(existingRole);
    }

    @Test
    void testUpdateRole_ThrowsException_WhenRoleAlreadyExists() {
        Roles existingRole = new Roles(1, "User");
        Roles updatedRole = new Roles(1, "Admin");

        // Fake DB
        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.existsByRole("Admin")).thenReturn(true);

        // Expect exception
        assertThrows(IllegalArgumentException.class,
                () -> roleService.updateRole(1L, updatedRole));

        // Verify save never called
        verify(roleRepository, never()).save(any());
    }

    @Test
    void testUpdateRole_ThrowsException_WhenRoleNotFound() {
        Roles updatedRole = new Roles(1, "Admin");

        // No role found
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        // Expect exception (NoSuchElement or custom exception)
        assertThrows(RuntimeException.class,
                () -> roleService.updateRole(1L, updatedRole));

        verify(roleRepository, never()).save(any());
    }

}