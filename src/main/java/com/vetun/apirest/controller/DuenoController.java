package com.vetun.apirest.controller;

import com.vetun.apirest.model.*;
import com.vetun.apirest.pojo.AgendarCitaPOJO;
import com.vetun.apirest.pojo.PerfilDuenoPOJO;
import com.vetun.apirest.pojo.PruebasPOJO;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class DuenoController {

    private DuenoService duenoService;
    private UsuarioService usuarioService;
    private RolService rolService;
    private PasswordEncoder passwordEncoder;
    private CitaService citaService;
    private DuenoService medicoService;
    private HoraAtencionService horaAtencionService;

    public DuenoController(DuenoService duenoService, UsuarioService usuarioService, RolService rolService, PasswordEncoder passwordEncoder, CitaService citaService, DuenoService medicoService, HoraAtencionService horaAtencionService) {
        this.duenoService = duenoService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.passwordEncoder = passwordEncoder;
        this.citaService = citaService;
        this.medicoService = medicoService;
        this.horaAtencionService = horaAtencionService;
    }

    @PostMapping(value = {"/registro/nuevo-dueno/"})
    public ResponseEntity<Void> registerNewUser(@RequestBody RegistrarDuenoPOJO duenoPOJO) {
        Rol rol = rolService.findById(1);
        duenoPOJO.setPassword(passwordEncoder.encode(duenoPOJO.getPassword()));
        Dueno existingDueno = duenoService.findByCedulaDueno(duenoPOJO.getCedulaDueno());
        String email = duenoPOJO.getCorreoElectronico();
        Usuario usuarioExistente = usuarioService.findByCorreoElectronico(email);
        if (rol == null || existingDueno != null || !duenoService.isRightDueno(duenoPOJO) || usuarioExistente != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Dueno newDueno = duenoService.mapperDuenoEntity(duenoPOJO);
        Usuario newUsuario = usuarioService.mapperUsuarioEntityDueno(duenoPOJO);
        newUsuario.setRol(rol);

        newUsuario.setDueno(newDueno);
        newDueno.setUsuario(newUsuario);
        usuarioService.save(newUsuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = {"/dueno/mascotas"})
    public ResponseEntity<?> getMascotas() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        if (dueno == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Mascota> mascotasDueno = dueno.getMascotas();
        return ResponseEntity.ok(mascotasDueno);
    }

    @GetMapping(value = {"pruebas/{duenoId}"})
    public ResponseEntity<?> getDuenoById(@PathVariable int duenoId) {
        Dueno dueno = duenoService.findById(duenoId);
        return ResponseEntity.ok(dueno);
    }

    @GetMapping(value = {"/dueno/perfil"})
    public ResponseEntity<?> getDuenoPerfil() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario user = usuarioService.findByUsername(username);
        Dueno dueno = duenoService.findByUsuarioIdUsuario(user.getIdUsuario());

        PerfilDuenoPOJO perfil = new PerfilDuenoPOJO();

        perfil.setCedulaDueno(dueno.getCedulaDueno());
        perfil.setNombreDueno(dueno.getNombreDueno());
        perfil.setApellidoDueno(dueno.getApellidoDueno());
        perfil.setDireccionDueno(dueno.getDireccionDueno());
        perfil.setTelefonoDueno(dueno.getTelefonoDueno());
        perfil.setCorreoDueno(user.getCorreoElectronico());
        perfil.setUsuarioDueno(user.getUsername());

        return ResponseEntity.ok(perfil);
    }

    @PostMapping(value = {"/pruebas/agendar"})
    public ResponseEntity<?> agendarCita(@RequestBody Cita agendarCita){
        Cita cita = agendarCita;

        //System.out.println(cita.getFechaCita());
        citaService.save(cita);

        return ResponseEntity.ok(cita);
    }

    @GetMapping(value = {"/pruebas/citasAg"})
    public ResponseEntity<?> citasAgendas(@RequestBody List<PruebasPOJO> lista){

        //List<Cita> citas = citaService.findCitas(pojo.getFechaCita(), pojo.getFechaCita2());

        String nuevo = "";
        for(PruebasPOJO a : lista){
            nuevo += a.getPrueba();
            nuevo += " ";
        }

        return ResponseEntity.ok(nuevo);
    }




}
