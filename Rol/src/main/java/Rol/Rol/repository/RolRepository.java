package Rol.Rol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Rol.Rol.model.RolModel;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Long>{

}
