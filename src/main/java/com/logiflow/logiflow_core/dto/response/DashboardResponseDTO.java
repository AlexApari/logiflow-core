package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponseDTO {

    // Products
    private Long totalProductos;
    private Long productosBajoStock; // count of products below threshold
    private List<TopProductoDTO> topProductos; // for charts/tables
    private List<ProductoResumenDTO> recientesProductos;

    // Orders (Pedidos)
    private Long totalPedidos;
    private BigDecimal totalVentas;
    private List<EstadoCountDTO> pedidosPorEstado;
    private List<PedidoResumenDTO> recientesPedidos;

    // Shipments (Envios)
    private Long totalEnvios;
    private BigDecimal totalCostoEnvios;
    private List<EstadoCountDTO> enviosPorEstado;
    private List<EnvioResumenDTO> recientesEnvios;

    public DashboardResponseDTO() {}

    // getters and setters
    public Long getTotalProductos() { return totalProductos; }
    public void setTotalProductos(Long totalProductos) { this.totalProductos = totalProductos; }
    public Long getProductosBajoStock() { return productosBajoStock; }
    public void setProductosBajoStock(Long productosBajoStock) { this.productosBajoStock = productosBajoStock; }
    public List<TopProductoDTO> getTopProductos() { return topProductos; }
    public void setTopProductos(List<TopProductoDTO> topProductos) { this.topProductos = topProductos; }
    public List<ProductoResumenDTO> getRecientesProductos() { return recientesProductos; }
    public void setRecientesProductos(List<ProductoResumenDTO> recientesProductos) { this.recientesProductos = recientesProductos; }
    public Long getTotalPedidos() { return totalPedidos; }
    public void setTotalPedidos(Long totalPedidos) { this.totalPedidos = totalPedidos; }
    public BigDecimal getTotalVentas() { return totalVentas; }
    public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }
    public List<EstadoCountDTO> getPedidosPorEstado() { return pedidosPorEstado; }
    public void setPedidosPorEstado(List<EstadoCountDTO> pedidosPorEstado) { this.pedidosPorEstado = pedidosPorEstado; }
    public List<PedidoResumenDTO> getRecientesPedidos() { return recientesPedidos; }
    public void setRecientesPedidos(List<PedidoResumenDTO> recientesPedidos) { this.recientesPedidos = recientesPedidos; }
    public Long getTotalEnvios() { return totalEnvios; }
    public void setTotalEnvios(Long totalEnvios) { this.totalEnvios = totalEnvios; }
    public BigDecimal getTotalCostoEnvios() { return totalCostoEnvios; }
    public void setTotalCostoEnvios(BigDecimal totalCostoEnvios) { this.totalCostoEnvios = totalCostoEnvios; }
    public List<EstadoCountDTO> getEnviosPorEstado() { return enviosPorEstado; }
    public void setEnviosPorEstado(List<EstadoCountDTO> enviosPorEstado) { this.enviosPorEstado = enviosPorEstado; }
    public List<EnvioResumenDTO> getRecientesEnvios() { return recientesEnvios; }
    public void setRecientesEnvios(List<EnvioResumenDTO> recientesEnvios) { this.recientesEnvios = recientesEnvios; }
}