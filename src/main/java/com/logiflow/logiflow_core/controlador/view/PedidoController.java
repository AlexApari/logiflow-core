package com.logiflow.logiflow_core.controlador.view;

import com.logiflow.logiflow_core.dto.request.DetallePedidoDTO;
import com.logiflow.logiflow_core.dto.request.PedidoRequestDTO;
import com.logiflow.logiflow_core.repositorio.*;
import com.logiflow.logiflow_core.servicio.PedidoServicio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operaciones/pedidos")
public class PedidoController {

	private PedidoServicio pedidoServicio;
	private ClienteRepositorio clienteRepositorio;
	private ProductoRepositorio productoRepositorio;

	PedidoController(PedidoServicio pedidoServicio, ClienteRepositorio clienteRepositorio,
			ProductoRepositorio productoRepositorio) {
		this.pedidoServicio = pedidoServicio;
		this.clienteRepositorio = clienteRepositorio;
		this.productoRepositorio = productoRepositorio;
	}

	// Mostrar el formulario
	@GetMapping
	public String mostrarFormulario(Model model) {
		PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO();
	    // inicializamos con un detalle vacío para que Thymeleaf genere un select
	    pedidoRequestDTO.getDetalles().add(new DetallePedidoDTO());
	    model.addAttribute("pedidoRequestDTO", pedidoRequestDTO);
	    model.addAttribute("clientes", clienteRepositorio.findAll());
		model.addAttribute("productos", productoRepositorio.findAll());

		return "operaciones/pedidos"; // Thymeleaf buscará templates/pedido.html
	}

	// Guardar pedido
	@PostMapping("/registrar")
	public String registrarPedido(@ModelAttribute("pedidoRequestDTO") PedidoRequestDTO dto, Model model) {
		pedidoServicio.registrar(dto);
		return "redirect:/operaciones/pedidos"; // Redirige a la lista de pedidos
	}

	// Listar pedidos
	@GetMapping("/listar")
	public String listarPedidos(Model model) {
		model.addAttribute("pedidos", pedidoServicio.listar());
		return "pedidos-lista"; // Thymeleaf buscará templates/pedidos-lista.html
	}
}
