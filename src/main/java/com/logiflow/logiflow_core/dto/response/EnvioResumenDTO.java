package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EnvioResumenDTO {
    private Long id;
    private String numeroEnvio;
    private String numeroGuia;
    private Long pedidoId;
    private String pedidoNumero;
    private LocalDateTime fechaEnvio;
    private String estado;
    private BigDecimal costoEnvio;

    public EnvioResumenDTO() {}

    public EnvioResumenDTO(Long id, String numeroEnvio, String numeroGuia, Long pedidoId, String pedidoNumero, LocalDateTime fechaEnvio, String estado, BigDecimal costoEnvio) {
        this.id = id;
        this.numeroEnvio = numeroEnvio;
        this.numeroGuia = numeroGuia;
        this.pedidoId = pedidoId;
        this.pedidoNumero = pedidoNumero;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
        this.costoEnvio = costoEnvio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroEnvio() { return numeroEnvio; }
    public void setNumeroEnvio(String numeroEnvio) { this.numeroEnvio = numeroEnvio; }
    public String getNumeroGuia() { return numeroGuia; }
    public void setNumeroGuia(String numeroGuia) { this.numeroGuia = numeroGuia; }
    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }
    public String getPedidoNumero() { return pedidoNumero; }
    public void setPedidoNumero(String pedidoNumero) { this.pedidoNumero = pedidoNumero; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getCostoEnvio() { return costoEnvio; }
    public void setCostoEnvio(BigDecimal costoEnvio) { this.costoEnvio = costoEnvio; }
}