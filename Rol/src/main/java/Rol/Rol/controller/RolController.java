package Rol.Rol.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rol.Rol.model.RolModel;
import Rol.Rol.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    

    @Operation(summary = "Permite obtener una lista con todos los Roles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista generada correctamente",
        content = @Content(schema = @Schema(implementation = RolModel.class)))
    })
    @GetMapping
    public ResponseEntity<List<RolModel>> obtenerRoles() {
        List<RolModel> lista = rolService.getRol();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Permite obtener un Rol por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol encontrado",
        content = @Content(schema = @Schema(implementation = RolModel.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RolModel> obtenerRolPorId(@PathVariable Long id) {
        RolModel rol = rolService.obtenerRolPorId(id);
        if (rol == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rol);
    }

    
}
