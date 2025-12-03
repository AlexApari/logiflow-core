package com.logiflow.logiflow_core.controlador.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;
import com.logiflow.logiflow_core.dto.response.ProductoResumenDTO;
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
	public String paginaProductos(
	        @RequestParam( required = false) Long categoria,
	        Model model) {
		System.out.println("ID categoría seleccionada: " + categoria);

	    List<ProductoResumenDTO> productos;

	    if (categoria != null) {
	        productos = productoService.listarPorCategoria(categoria);
	    } else {
	        productos = productoService.obtenerTodosLosProductos();
	    }

	    model.addAttribute("productoForm", new ProductoRequestDTO());
	    model.addAttribute("productos", productos != null ? productos : new ArrayList<>());

	    // para llenar el select de categorías
	    model.addAttribute("productos", productos);

	    // para marcar el seleccionado en el select
	    model.addAttribute("categoriaSeleccionada", categoria);

	    return "catalogo/productos";
	}
	
	@GetMapping("/nuevo")
	public String abrirNuevo(Model model) {
		model.addAttribute("productoForm", new ProductoRequestDTO());
		model.addAttribute("modoEdicion", false);
		return "catalogo/producto-form";
	}
	@PostMapping("/crear")
	public String crearProducto(@ModelAttribute("productoForm") ProductoRequestDTO dto) {
	    productoService.crearProducto(dto);
	    return "redirect:/catalogo/productos";
	}


	@PostMapping("/activar-desactivar")
	public String cambiarEstadoProducto(@RequestParam("codigo") String codigo) {
		productoService.cambiarEstadoProducto(codigo);

		return "redirect:/catalogo/productos";
	}

	@GetMapping("/editar/{codigo}")
	public String abrirEdicion(@PathVariable String codigo, Model model) {
		ProductoRequestDTO dto = productoService.obtenerProductoParaEditar(codigo);
		model.addAttribute("productoForm", dto);
		model.addAttribute("modoEdicion", true);
		return "catalogo/producto-form";
	}

	@PostMapping("/editar/{codigo}")
	public String editarProducto(@PathVariable String codigo, @ModelAttribute("productoForm") ProductoRequestDTO dto) {

		productoService.actualizarProducto(codigo, dto);
		return "redirect:/catalogo/productos";
	}

}
