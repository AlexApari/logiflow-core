package com.logiflow.logiflow_core.controlador.view;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logiflow.logiflow_core.dto.request.ProveedorRequestDTO;

@Controller
@RequestMapping("/operaciones/proveedores")
@RequiredArgsConstructor
public class ProveedorController {
	
	
	private final com.logiflow.logiflow_core.servicio.ProveedoresServicio proveedorService;
	ProveedorController(com.logiflow.logiflow_core.servicio.ProveedoresServicio proveedorService) {
		this.proveedorService = proveedorService;
	}
	
	@GetMapping
	public String paginaProveedores(Model model) {

		model.addAttribute("proveedorForm", new ProveedorRequestDTO());
		model.addAttribute("proveedores", proveedorService.listarProveedores() != null ?
		proveedorService.listarProveedores() : new ArrayList<>());

		System.out.println(proveedorService.listarProveedores());

		return "operaciones/proveedores";
	}
	@PostMapping("/registrar")
	public String guardarProveedor(@ModelAttribute("proveedorForm") ProveedorRequestDTO dto) {
	    if (dto.getId() != null) {
	        proveedorService.editarProveedor(dto.getId(), dto);
	    } else {
	        proveedorService.registrarProveedor(dto);
	    }
	    return "redirect:/operaciones/proveedores";
	}

	@PostMapping("/eliminar")
	public String eliminarProveedor(@ModelAttribute("proveedorForm") ProveedorRequestDTO dto) {

		proveedorService.eliminarProveedor(dto.getRuc());

		return "redirect:/operaciones/proveedores"; 
	}
	@GetMapping("/editar/{ruc}")
	public String abrirFormularioEdicion(@PathVariable String ruc, Model model) {
		ProveedorRequestDTO dto = proveedorService.obtenerProveedorParaEditar(ruc);
		model.addAttribute("proveedorForm", dto); // Esto llenará tu formulario existente
		return "operaciones/proveedores"; // la misma página donde está tu formulario
	}
	//@PostMapping("/editar")
//	public String editarProveedor(@ModelAttribute("proveedorForm") ProveedorRequestDTO dto) {
	//	proveedorService.editarProveedor(dto.getId(), dto);
	//	return "redirect:/operaciones/proveedores"; 
	//}
	
}
