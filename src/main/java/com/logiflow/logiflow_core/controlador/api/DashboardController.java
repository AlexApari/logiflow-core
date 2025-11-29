package com.logiflow.logiflow_core.controlador.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logiflow.logiflow_core.dto.response.DashboardResponseDTO;
import com.logiflow.logiflow_core.dto.response.PedidoResumenDTO;
import com.logiflow.logiflow_core.servicio.DashboardService;

@Controller
@RequestMapping({"/dashboard"})
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String index(Model model) {
        DashboardResponseDTO dashboard = dashboardService.obtenerResumenDashboard();
        model.addAttribute("dashboard", dashboard);

        // Build a simple 'actividades' list from recent pedidos for the activity table
        List<Map<String, Object>> actividades = dashboard.getRecientesPedidos() == null ? List.of() :
                dashboard.getRecientesPedidos().stream()
                        .map(this::mapPedidoToActividad)
                        .collect(Collectors.toList());

        model.addAttribute("actividades", actividades);
        model.addAttribute("title", "Dashboard - LogiFlow");
        
        return "dashboard"; // resolves to templates/dashboard.html
    }

    private Map<String, Object> mapPedidoToActividad(PedidoResumenDTO p) {
        Map<String, Object> m = new HashMap<>();
        m.put("fecha", p.getFechaPedido() != null ? p.getFechaPedido().toString() : "");
        m.put("descripcion", "Pedido " + (p.getNumeroPedido() != null ? p.getNumeroPedido() : ""));
        m.put("usuario", p.getClienteNombre() != null ? p.getClienteNombre() : "");
        m.put("estado", p.getEstado() != null ? p.getEstado() : "");
        m.put("color", "#000");
        return m;
    }
}
