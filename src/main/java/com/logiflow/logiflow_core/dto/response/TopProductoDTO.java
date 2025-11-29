package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;

public class TopProductoDTO {
    private Long id;
    private String nombre;
    private String sku;
    private Integer cantidadVendida;
    private BigDecimal totalVentas;

    public TopProductoDTO() {}

    public TopProductoDTO(Long id, String nombre, String sku, Integer cantidadVendida, BigDecimal totalVentas) {
        this.id = id;
        this.nombre = nombre;
        this.sku = sku;
        this.cantidadVendida = cantidadVendida;
        this.totalVentas = totalVentas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public Integer getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(Integer cantidadVendida) { this.cantidadVendida = cantidadVendida; }
    public BigDecimal getTotalVentas() { return totalVentas; }
    public void setTotalVentas(BigDecimal totalVentas) { this.totalVentas = totalVentas; }
}