package com.vetun.apirest.service;

import com.vetun.apirest.model.Cita;
import com.vetun.apirest.repository.CitaRepository;
import org.springframework.stereotype.Service;

@Service
public class CitaService {

    private CitaRepository citaRepository;

    public CitaService( CitaRepository citaRepository){
        this.citaRepository = citaRepository;
    }

    public Cita findById( Integer id ){
        return  citaRepository.findById( id ).orElse( null );
    }

    public void save( Cita cita ){
        citaRepository.save( cita );
    }
}
