package com.logiflow.logiflow_core.controlador.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logiflow.logiflow_core.dto.request.LoginRequestDTO;
import com.logiflow.logiflow_core.servicio.LoginServicio;

@Controller
@RequestMapping("/api/logiflow/login")
public class LoginViewController{
	
	public LoginViewController(LoginServicio loginServicio) {
	}
	
	@GetMapping
	public String showLoginPage(Model model) {
		model.addAttribute("loginRequest",new LoginRequestDTO());
		return "login"; // Retorna el nombre de la vista (login.html)
	}
	

	
}