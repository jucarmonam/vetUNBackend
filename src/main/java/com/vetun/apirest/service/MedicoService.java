package com.vetun.apirest.service;

import com.vetun.apirest.model.Medico;
import com.vetun.apirest.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private MedicoRepository medicoRepository;

    public MedicoService( MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    public Medico findById( Integer id ){
        return medicoRepository.findById(id).orElse(null);
    }

    public void save ( Medico medico ){
        medicoRepository.save( medico );
    }

}
