package com.logiflow.logiflow_core.controlador.view;

import com.logiflow.logiflow_core.dto.request.DetallePedidoDTO;
import com.logiflow.logiflow_core.dto.request.PedidoRequestDTO;
import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;
import com.logiflow.logiflow_core.dto.response.PedidoResumenDTO;
import com.logiflow.logiflow_core.dto.response.ProductoResumenDTO;
import com.logiflow.logiflow_core.repositorio.*;
import com.logiflow.logiflow_core.servicio.PedidoServicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operaciones/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServicio pedidoServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    // ======================================
    // 1. LISTAR + FILTRAR PEDIDOS
    // ======================================
    @GetMapping
    public String paginaPedidos(
            @RequestParam(required = false) String estado,
            Model model) {

        List<PedidoResumenDTO> pedidos =
                (estado != null && !estado.isEmpty())
                        ? pedidoServicio.listarPorEstado(estado)
                        : pedidoServicio.listar();

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("estadoSeleccionado", estado);

        return "operaciones/pedidos"; 
        // <-- ESTA es la vista que debe listar los pedidos
    }

    // ======================================
    // 2. FORMULARIO NUEVO PEDIDO
    // ======================================
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO();
        pedidoRequestDTO.getDetalles().add(new DetallePedidoDTO());

        model.addAttribute("pedidoRequestDTO", pedidoRequestDTO);
        model.addAttribute("clientes", clienteRepositorio.findAll());
        model.addAttribute("productos", productoRepositorio.findAll());

        return "operaciones/pedidos-form";  
        // <-- formulario separado
    }

    // ======================================
    // 3. REGISTRAR PEDIDO
    // ======================================
    @PostMapping("/registrar")
    public String registrarPedido(@ModelAttribute("pedidoRequestDTO") PedidoRequestDTO dto) {

        pedidoServicio.registrar(dto);

        return "redirect:/operaciones/pedidos";
    }

    // ======================================
    // 4. DETALLE DEL PEDIDO
    // ======================================
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {

        PedidoResumenDTO dto = pedidoServicio.obtenerPorId(id);

        model.addAttribute("pedido", dto);

        return "operaciones/pedido-detalle";
    }

    // ======================================
    // 5. LISTA SIMPLE (si aún la necesitas)
    // ======================================
    @GetMapping("/listar")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoServicio.listar());
        return "operaciones/pedidos-lista";
    }
}
