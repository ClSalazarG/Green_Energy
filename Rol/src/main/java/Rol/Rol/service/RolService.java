package Rol.Rol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rol.Rol.model.RolModel;
import Rol.Rol.repository.RolRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<RolModel> getRol() {
        return rolRepository.findAll();
    }

    

    public RolModel obtenerRolPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    
}
