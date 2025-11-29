package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoResumenDTO {
    private Long id;
    private String numeroPedido;
    private String clienteNombre;
    private LocalDate fechaPedido;
    private String estado;
    private BigDecimal total;

    public PedidoResumenDTO() {}

    public PedidoResumenDTO(Long id, String numeroPedido, String clienteNombre, LocalDate fechaPedido, String estado, BigDecimal total) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.clienteNombre = clienteNombre;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.total = total;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}