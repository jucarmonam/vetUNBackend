package com.vetun.apirest.service;

import com.vetun.apirest.model.Mascota;
import com.vetun.apirest.repository.MascotaRepository;
import org.springframework.stereotype.Service;

@Service
public class MascotaService {

    public MascotaRepository  mascotaRepository;

    public MascotaService( MascotaRepository mascotaRepository ){
        this.mascotaRepository = mascotaRepository;
    }

    public Mascota findById( Integer id ){
        return mascotaRepository.findById( id ).orElse( null );
    }

    public void save( Mascota mascota ){
        mascotaRepository.save( mascota );
    }
}
