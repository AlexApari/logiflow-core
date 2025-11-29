package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;

public class ProductoResumenDTO {
    private Long id;
    private String nombre;
    private String sku;
    private Integer stock;
    private BigDecimal precio;

    public ProductoResumenDTO() {}

    public ProductoResumenDTO(Long id, String nombre, String sku, Integer stock, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.sku = sku;
        this.stock = stock;
        this.precio = precio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
}