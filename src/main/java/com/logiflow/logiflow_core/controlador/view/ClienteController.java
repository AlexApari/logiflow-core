package com.logiflow.logiflow_core.controlador.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logiflow.logiflow_core.dto.request.ClienteRequestDTO;
import com.logiflow.logiflow_core.dto.response.ClienteResumenDTO;
import com.logiflow.logiflow_core.servicio.ClienteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/operaciones/clientes")
@RequiredArgsConstructor
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String paginaClientes(
	        @RequestParam( required = false) String categoriaCliente,
	        Model model) {
		System.out.println("ID categoría seleccionada: " + categoriaCliente);

		List<ClienteResumenDTO> clientes =
                (categoriaCliente != null && !categoriaCliente.isEmpty())
                        ? clienteService.listarPorCategoria(categoriaCliente)
                        : clienteService.obtenerTodosLosClientes();

	    model.addAttribute("clientes", clientes);
	    // para marcar el seleccionado en el select
	    model.addAttribute("categoriaSeleccionada", categoriaCliente);

	    return "operaciones/clientes";
	}
	@GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
		
        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
        model.addAttribute("clienteRequestDTO", clienteRequestDTO);
        model.addAttribute("modoEdicion",false);

        return "operaciones/clientes-form";  
        // <-- formulario separado
    }

    // ======================================
    // 3. REGISTRAR PEDIDO
    // ======================================
    @PostMapping("/registrar")
    public String registrarCliente(@ModelAttribute("clienteRequestDTO") ClienteRequestDTO dto) {

        clienteService.crearCliente(dto);

        return "redirect:/operaciones/clientes";
    }

}
