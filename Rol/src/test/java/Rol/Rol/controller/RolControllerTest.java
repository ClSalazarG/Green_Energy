package Rol.Rol.controller;


import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.test.web.servlet.MockMvc;

import Rol.Rol.model.RolModel;
import Rol.Rol.service.RolService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RolController.class)
public class RolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RolService rolService;

    

    @Test
    void testObtenerTodosLosRoles() throws Exception {
        List<RolModel> roles = Arrays.asList(
                new RolModel(1L, "Administrador")  
        );

        when(rolService.getRol()).thenReturn(roles);

        mockMvc.perform(get("/api/v1/Roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testObtenerRolPorId_existente() throws Exception {
        RolModel rol = new RolModel(1L, "Administrador");

        when(rolService.obtenerRolPorId(1L)).thenReturn(rol);

        mockMvc.perform(get("/api/v1/Roles/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    
}
