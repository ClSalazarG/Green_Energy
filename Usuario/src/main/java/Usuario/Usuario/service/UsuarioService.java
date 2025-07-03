package Usuario.Usuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Usuario.Usuario.model.Usuario;
import Usuario.Usuario.repository.UsuarioRepository;
import Usuario.Usuario.webClient.RolesClient;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolesClient rolesClient;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario nuevoUsuario) {
        Map<String, Object> roles = rolesClient.getRolesById(nuevoUsuario.getIdrol());
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("Rol no encontrado");
        }
        nuevoUsuario.setPassword(PasswordUtil.hashPassword(nuevoUsuario.getPassword()));
        return usuarioRepository.save(nuevoUsuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
