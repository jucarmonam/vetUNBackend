package com.vetun.apirest.controller;

import com.vetun.apirest.model.Medico;
import com.vetun.apirest.model.Rol;
import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarMedicoPOJO;
import com.vetun.apirest.service.MedicoService;
import com.vetun.apirest.service.RolService;
import com.vetun.apirest.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicoController {

    private MedicoService medicoService;
    private UsuarioService usuarioService;
    private RolService rolService;
    private PasswordEncoder passwordEncoder;

    public MedicoController(MedicoService medicoService, UsuarioService usuarioService, RolService rolService, PasswordEncoder passwordEncoder) {
        this.medicoService = medicoService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/medicos/{medicoId}")
    public ResponseEntity<?>getMedico(@PathVariable int medicoId){
        Medico medico = medicoService.findById(medicoId);
        if(medico == null) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        }
        return ResponseEntity.ok(medico);
    }

    @PostMapping( value = { "/registro/nuevo-medico/" } )
    public ResponseEntity<Void> registrarNuevoMedico(@RequestBody RegistrarMedicoPOJO medicoPOJO){
        Rol rol = rolService.findById( 2 );
        medicoPOJO.setPassword(passwordEncoder.encode(medicoPOJO.getPassword()));
        Medico medicoExistente = medicoService.findByCedulaMedico( medicoPOJO.getCedulaMedico() );
        String email = medicoPOJO.getCorreoElectronico();
        Usuario usuarioExistente = usuarioService.findByCorreoElectronico(email);
        if( rol == null || medicoExistente != null || !medicoService.isRightMedico( medicoPOJO ) || usuarioExistente != null ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

        Medico nuevoMedico = medicoService.mapperMedicoEntity(medicoPOJO);
        Usuario newUsuario = usuarioService.mapperUsuarioEntityMedico(medicoPOJO);
        newUsuario.setRol(rol);

        newUsuario.setMedico(nuevoMedico);
        nuevoMedico.setUsuario(newUsuario);
        usuarioService.save(newUsuario);

        return new ResponseEntity<>( HttpStatus.CREATED );
    }
}
