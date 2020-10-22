package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarMascotaPOJO;
import com.vetun.apirest.service.MascotaService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MascotaController {

    private MascotaService mascotaService;
    private UsuarioService usuarioService;

    public MascotaController(MascotaService mascotaService, UsuarioService usuarioService) {
        this.mascotaService = mascotaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping( value = { "dueno/registro-mascota" } )
    public ResponseEntity<Void> registrarNuevaMascota(@RequestBody RegistrarMascotaPOJO mascotaPOJO){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = user.getDueno();
        Mascota nuevaMascota = mascotaService.mapperMascotaEntity(mascotaPOJO);
        nuevaMascota.setIdDueno(dueno);
        mascotaService.save(nuevaMascota);
        return new ResponseEntity<>( HttpStatus.CREATED );
    }
}
