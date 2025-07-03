package Rol.Rol.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Rol.Rol.model.RolModel;
import Rol.Rol.repository.RolRepository;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDataBase(RolRepository repository) {
        return args -> {
            if(repository.count()==0){
                repository.save(new RolModel(null,"Administrador"));
                repository.save(new RolModel(null,"Usuario"));
                repository.save(new RolModel(null,"Tecnico"));
                repository.save(new RolModel(null,"Instalador"));
                repository.save(new RolModel(null,"Soporte"));
                repository.save(new RolModel(null,"Cliente"));
                System.out.println("Roles cargados");
            } else{
                System.out.println("Ya existen los datos en la base de datos");
            }
        };
    }

}
