package Usuario.Usuario.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import Usuario.Usuario.model.Usuario;
import Usuario.Usuario.repository.UsuarioRepository;
import Usuario.Usuario.webClient.RolesClient;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private RolesClient rolesClient;

    @InjectMocks
    private UsuarioService service;

    @Test
    void findAll_returnsListFromRepository() {
        // crear un elemento que simule la respuesta del repositorio

        List<Usuario> mockUsuarios = List.of(
                new Usuario(1L, "Claudio", "Salazar", "@gmail.com", "password1234", null, 1L));

        // simular el comportamiento del repositorio
        when(repository.findAll()).thenReturn(mockUsuarios);

        // llamar al método del servicio
        List<Usuario> usuarios = service.getUsuario();

        // verificar que la lista devuelta es la misma que la del mock
        assertThat(usuarios).isSameAs(mockUsuarios);
    }

    @Test
    void getUsuarioPorId_throwsExceptionIfNotFound() {

        Usuario mockUsuario = new Usuario(1L,
                "Claudio", "Salazar",
                "@gmail.com", "paswords",
                null, 1L);

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(mockUsuario));

        Usuario resultado = service.getUsuarioPorId(1L);

        assertThat(resultado).isSameAs(mockUsuario);
    }

    @Test
    void save_returnsSavedUsuario() {
        Usuario nuevoUsuario = new Usuario(1L,
                "Claudio", "Salazar",
                "@gmail.com", "pass",
                null, 1L);

        Map<String, Object> usuarioMock = Map.of("idUsuario", 1L);
        when(rolesClient.getRolesById(1L)).thenReturn(usuarioMock);

        when(repository.save(any())).thenReturn(nuevoUsuario);

        Usuario resultado = service.saveUsuario(nuevoUsuario);

        assertThat(resultado).isSameAs(nuevoUsuario);
    }

    @Test
    void eliminarUsuario_deletesUsuario() {

        service.eliminarUsuario(1L);

        verify(repository).deleteById(1L);
    }

}
