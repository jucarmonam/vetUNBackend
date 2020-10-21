package com.vetun.apirest.service;

import com.vetun.apirest.model.Usuario;
import com.vetun.apirest.pojo.RegistrarDuenoPOJO;
import com.vetun.apirest.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;



    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByUsername(String username ){
        return usuarioRepository.findByUsername( username );
    }

    public void save( Usuario usuario ){
        usuarioRepository.save( usuario );
    }

    public Usuario mapperUsuarioEntity(RegistrarDuenoPOJO duenoPOJO ){
        Usuario newUser = new Usuario( );
        newUser.setUsername( duenoPOJO.getUsername() );
        newUser.setCorreoElectronico( duenoPOJO.getCorreoElectronico() );
        newUser.setPassword(duenoPOJO.getPassword());
        return newUser;
    }
}
