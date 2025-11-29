package com.logiflow.logiflow_core.servicio;

import java.math.BigDecimal;
import java.util.List;

import com.logiflow.logiflow_core.dto.response.*;

public interface DashboardService {

	DashboardResponseDTO obtenerResumenDashboard();
	List<EstadoCountDTO> obtenerConteoPorEstado();
	List<TopProductoDTO> obtenerTopProductosVendidos(int limite);
	List<ProductoResumenDTO> obtenerProductosRecientes(int limite);
	List<PedidoResumenDTO> obtenerPedidosRecientes(int limite);
	List<EnvioResumenDTO> obtenerEnviosRecientes(int limite);
	Long obtenerTotalProductos();
	Long obtenerTotalPedidos();
	BigDecimal obtenerTotalVentas();
	Long obtenerTotalEnvios();
	BigDecimal obtenerTotalCostoEnvios();
	
}
