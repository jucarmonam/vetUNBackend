package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.model.Rol;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.service.DuenoService;
import com.vetun.apirest.service.RolService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DuenoController {

    private DuenoService duenoService;
    private UsuarioService usuarioService;
    private RolService rolService;
    private PasswordEncoder passwordEncoder;

    public DuenoController(DuenoService duenoService, UsuarioService usuarioService, RolService rolService, PasswordEncoder passwordEncoder) {
        this.duenoService = duenoService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping( value = { "/registro/nuevo-dueno/" } )
    public ResponseEntity<Void> registerNewUser(@RequestBody RegistrarDuenoPOJO duenoPOJO ){
        Rol rol = rolService.findById( 1 );
        duenoPOJO.setPassword(passwordEncoder.encode(duenoPOJO.getPassword()));
        Dueno existingDueno = duenoService.findByCedulaDueno( duenoPOJO.getCedulaDueno() );
        String email = duenoPOJO.getCorreoElectronico();
        Usuario usuarioExistente = usuarioService.findByCorreoElectronico(email);
        if( rol == null || existingDueno != null || !duenoService.isRightDueno( duenoPOJO ) || usuarioExistente != null ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        Dueno newDueno = duenoService.mapperDuenoEntity(duenoPOJO);
        Usuario newUsuario = usuarioService.mapperUsuarioEntityDueno(duenoPOJO);
        newUsuario.setRol(rol);

        newUsuario.setDueno(newDueno);
        newDueno.setUsuario(newUsuario);
        usuarioService.save(newUsuario);

        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @GetMapping(value = {"/dueno/mascotas"} )
    public ResponseEntity<?> getMascotas(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        if( dueno == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        List<Mascota> mascotasDueno = dueno.getMascotas();
        return ResponseEntity.ok(mascotasDueno);
    }
    
}
