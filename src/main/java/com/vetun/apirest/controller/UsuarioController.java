package com.vetun.apirest.controller;

import com.vetun.apirest.model.Dueno;
import com.vetun.apirest.model.Medico;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.MenuBarUserPOJO;
import com.vetun.apirest.service.DuenoService;
import com.vetun.apirest.service.MedicoService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private UsuarioService usuarioService;
    private DuenoService duenoService;
    private MedicoService medicoService;

    public UsuarioController(UsuarioService usuarioService, DuenoService duenoService, MedicoService medicoService) {
        this.usuarioService = usuarioService;
        this.duenoService = duenoService;
        this.medicoService = medicoService;
    }

    @GetMapping(value = {"/usuario"} )
    public ResponseEntity<?> getUserAuthenticated(){
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        Usuario userAuth = usuarioService.findByUsername(username);
        String rol= userAuth.getRol().getNombreRol();
        MenuBarUserPOJO user = new MenuBarUserPOJO();
        user.setRolUsuario(rol);

        if(rol.equals("DUENO")){
            Dueno dueno = duenoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(dueno.getNombreDueno());
            user.setApellidoUsuario(dueno.getApellidoDueno());
        }else{
            Medico medico = medicoService.findByUsuarioIdUsuario(userAuth.getIdUsuario());
            user.setNombreUsuario(medico.getNombreMedico());
            user.setApellidoUsuario(medico.getApellidoMedico());
        }
        
        return ResponseEntity.ok(user);
    }

}
