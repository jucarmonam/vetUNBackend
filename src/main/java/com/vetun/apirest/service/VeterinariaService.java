package com.vetun.apirest.service;

import com.vetun.apirest.model.Veterinaria;
import com.vetun.apirest.repository.VeterinariaRepository;
import org.springframework.stereotype.Service;

@Service
public class VeterinariaService {

    private VeterinariaRepository veterinariaRepository;

    public  VeterinariaService(VeterinariaRepository veterinariaRepository){
        this.veterinariaRepository = veterinariaRepository;
    }

    public Veterinaria findById( Integer id ){
        return veterinariaRepository.findById( id ).orElse(null);
    }

    public void save(Veterinaria veterinaria){
        veterinariaRepository.save(veterinaria);
    }
}
