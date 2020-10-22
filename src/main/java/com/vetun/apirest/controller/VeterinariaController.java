package com.vetun.apirest.controller;

import com.vetun.apirest.model.Veterinaria;
import com.vetun.apirest.pojo.RegistrarVeterinariaPOJO;
import com.vetun.apirest.service.VeterinariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeterinariaController {

    private VeterinariaService veterinariaService;

    public VeterinariaController(VeterinariaService veterinariaService) {
        this.veterinariaService = veterinariaService;
    }

    @GetMapping("/veterinarias")
    public ResponseEntity<?> buscarVeterinarias(){
        List<Veterinaria> veterinarias = veterinariaService.findAll();
        return ResponseEntity.ok(veterinarias);
    }

    @PostMapping("usuario/registro-veterinaria")
    public ResponseEntity<?> addVeterinaria(@RequestBody RegistrarVeterinariaPOJO registrarVeterinaria) {
        Veterinaria veterinaria = veterinariaService.mapperVeterinariaEntity(registrarVeterinaria);
        veterinariaService.save(veterinaria);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/veterinarias/{veterinariaId}")
    public ResponseEntity<?> getVeterinaria(@PathVariable int veterinariaId){
        Veterinaria veterinaria = veterinariaService.findById(veterinariaId);

        if(veterinaria == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(veterinaria);
    }



}
