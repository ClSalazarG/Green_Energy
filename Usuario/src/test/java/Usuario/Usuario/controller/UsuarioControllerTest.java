package Usuario.Usuario.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import Usuario.Usuario.model.Usuario;
import Usuario.Usuario.service.UsuarioService;
import Usuario.Usuario.webClient.RolesClient;

public class UsuarioControllerTest {
    @MockBean
    private UsuarioService usuarioService;
    // Crear un mock proporcionado por Spring
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RolesClient rolesClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsuarios_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Claudio");
        usuario.setApellido("Salazar");
        usuario.setCorreo("clidox@gmail.com");
        usuario.setPassword("pass1234");
        usuario.setIdrol(null);

        List<Usuario> listaUsuarios = Arrays.asList(usuario);
        when(usuarioService.obtenerUsuarios()).thenReturn(listaUsuarios);

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Claudio"))
                .andExpect(jsonPath("$[0].apellido").value("Salazar"))
                .andExpect(jsonPath("$[0].email").value("clidox@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("pass1234"))
                .andExpect(jsonPath("$[0].idRol").doesNotExist());
    }

    @Test
    void getUsuarioPorId_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Claudio");
        usuario.setApellido("Salazar");
        usuario.setCorreo("clidox@gmail.com");
        usuario.setPassword("pass1234");
        usuario.setIdrol(null);

        when(usuarioService.getUsuarioPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombre").value("Claudio"))
                .andExpect(jsonPath("$.apellido").value("Salazar"))
                .andExpect(jsonPath("$.email").value("clidoxgmail.com"))
                .andExpect(jsonPath("$.password").value("pass1234"))
                .andExpect(jsonPath("$.idRol").doesNotExist());
    }

    @Test
    void crearUsuario_returnsCreatedAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Claudio");
        usuario.setApellido("Salazar");
        usuario.setCorreo("clidox@gmail.com");
        usuario.setPassword("pass1234");
        usuario.setIdrol(1L);

        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(post("/api/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("juan.perez@gmail.com"));
    }

    @Test
    void getUsuarioById_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Claudio");
        usuario.setApellido("Salazar");
        usuario.setCorreo("juan@gmail.com");
        usuario.setPassword("pass1234");
        usuario.setIdrol(1L);

        when(usuarioService.getUsuarioPorId(1L)).thenReturn(usuario);
        mockMvc.perform(get("/api/usuario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombre").value("Claudio"))
                .andExpect(jsonPath("$.apellido").value("Salazar"))
                .andExpect(jsonPath("$.email").value("clidox@gmail.com"))
                .andExpect(jsonPath("$.password").value("pass1234"))
                .andExpect(jsonPath("$.idRol").value(1L));
    }

    @Test
    void eliminarUsuario_returnsNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(usuarioService).eliminarUsuario(id);

        mockMvc.perform(delete("/api/usuario/{id}", id))
                .andExpect(status().isNoContent());
    }

}
