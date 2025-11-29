package com.logiflow.logiflow_core.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import com.logiflow.logiflow_core.dto.request.LoginRequestDTO;
import com.logiflow.logiflow_core.dto.response.LoginResponseDTO;
import com.logiflow.logiflow_core.entidad.Usuario;
import com.logiflow.logiflow_core.repositorio.*;

@Service
public class LoginServiceImp implements LoginServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public LoginResponseDTO autenticarUsuario(LoginRequestDTO loginRequest) {
		// 1. Buscar usuario por email
        Usuario usuario = usuarioRepositorio.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // 2. Validar contraseña
        boolean passwordCoincide = passwordEncoder.matches(
                loginRequest.getPassword(),
                usuario.getPassword()
        );

        if (!passwordCoincide) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Generar token
        String token = generarTokenParaUsuario(usuario);

        return new LoginResponseDTO(token, "Autenticación exitosa");
	}
	private String generarTokenParaUsuario(Usuario usuario) {
	    // ejemplo simple (solo para ilustrar)
	    return UUID.randomUUID().toString(); // token simulado
	}

	@Override
	public void cerrarSesion(String token) {
	    // lógica para cerrar sesión, si no hay implementación temporal:
	    // puede estar vacío mientras desarrollas
	}


}
