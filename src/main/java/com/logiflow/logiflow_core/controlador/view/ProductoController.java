package com.logiflow.logiflow_core.controlador.view;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;

import com.logiflow.logiflow_core.servicio.ProductoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/catalogo/productos")
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoService productoService;

	ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping
	public String paginaProductos(Model model) {

		model.addAttribute("productoForm", new ProductoRequestDTO());
		model.addAttribute("productos",
				productoService.obtenerTodosLosProductos() != null ? productoService.obtenerTodosLosProductos()
						: new ArrayList<>());

		System.out.println(productoService.obtenerTodosLosProductos());

		return "catalogo/productos";
	}

	@PostMapping("/crear")
	public String crearProducto(@ModelAttribute("productoForm") ProductoRequestDTO dto) {
		if (dto.getCodigo() != null) {
			productoService.actualizarProducto(dto.getCodigo(), dto);
		} else {
			productoService.crearProducto(dto);
		}
		return "redirect:/catalogo/productos";
	}

	@PostMapping("/activar-desactivar")
	public String cambiarEstadoProducto(@RequestParam("codigo") String codigo) {
		productoService.cambiarEstadoProducto(codigo);

		return "redirect:/catalogo/productos";
	}

	@GetMapping("/editar/{codigo}")
	public String abrirFormularioEdicion(@PathVariable String codigo, Model model) {
		ProductoRequestDTO dto = productoService.obtenerProductoParaEditar(codigo);
		model.addAttribute("productoForm", dto); // Esto llenará tu formulario existente
		return "catalogo/productos"; // la misma página donde está tu formulario
	}

	// @PostMapping("/editar")
//	public String editarProducto(@ModelAttribute("productoForm") ProductoRequestDTO dto) {
	// productoService.actualizarProducto(dto.getCodigo(), dto);
	// return "redirect:/catalogo/productos";
	// }

}
