package com.logiflow.logiflow_core.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.logiflow.logiflow_core.entidad.Usuario;
import com.logiflow.logiflow_core.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImp implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicioImp(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepositorio.findByNombre(username);
        if (usuario.isEmpty()) {
            usuario = usuarioRepositorio.findByEmail(username);
        }
        return usuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

}