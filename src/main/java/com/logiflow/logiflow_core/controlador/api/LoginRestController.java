package com.logiflow.logiflow_core.controlador.api;

import org.springframework.web.bind.annotation.RestController;

import com.logiflow.logiflow_core.dto.request.LoginRequestDTO;
import com.logiflow.logiflow_core.dto.response.LoginResponseDTO;
import com.logiflow.logiflow_core.servicio.LoginServiceImp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/api/login")
public class LoginRestController {
	
	private final LoginServiceImp loginServicio;
	public LoginRestController(LoginServiceImp loginServicio) {
		this.loginServicio = loginServicio;
	}
	@PostMapping
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
	
		try {
		LoginResponseDTO response = loginServicio.autenticarUsuario(loginRequest);
		return ResponseEntity.ok(response);
		}catch (RuntimeException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO(null, "Credenciales inválidas"));
	}
	}
	
}
