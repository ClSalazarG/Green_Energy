package Rol.Rol.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import Rol.Rol.model.RolModel;
import Rol.Rol.repository.RolRepository;


@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository repository;

    @InjectMocks
    private RolService service;

    @Test
    void obtenerTodosLosRoles() {
        List<RolModel> listaMock = Arrays.asList(
                new RolModel(1L, "Admin")
        );

        when(repository.findAll()).thenReturn(listaMock);

        List<RolModel> resultado = service.getRol();

        assertThat(resultado).isEqualTo(listaMock);

    }

    @Test
    void obtenerRolPorIdExistente() {
        RolModel rol = new RolModel(1L, "Admin");

        when(repository.findById(1L)).thenReturn(Optional.of(rol));

        RolModel resultado = service.obtenerRolPorId(1L);

        assertThat(resultado).isEqualTo(rol);
    }

}