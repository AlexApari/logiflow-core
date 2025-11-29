package com.logiflow.logiflow_core.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "envios",
       indexes = {
           @Index(name = "idx_numero_envio", columnList = "numero_envio"),
           @Index(name = "idx_numero_guia", columnList = "numero_guia"),
           @Index(name = "idx_pedido", columnList = "pedido_id"),
           @Index(name = "idx_transporte", columnList = "transporte_id"),
           @Index(name = "idx_estado", columnList = "estado"),
           @Index(name = "idx_fecha_envio", columnList = "fecha_envio")
       })
@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
public class Envios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_envio", length = 20, nullable = false, unique = true)
    private String numeroEnvio;

    @Column(name = "numero_guia", length = 50, unique = true)
    private String numeroGuia;

    // RELACIONES ---------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transporte_id")
    private Transporte transporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_despachador")
    private Usuario usuarioDespachador;

    // FECHAS -------------------------

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_entrega_estimada")
    private LocalDateTime fechaEntregaEstimada;

    @Column(name = "fecha_entrega_real")
    private LocalDateTime fechaEntregaReal;

    // DIRECCIONES --------------------

    @Column(name = "direccion_origen", columnDefinition = "TEXT", nullable = false)
    private String direccionOrigen;

    @Column(name = "direccion_destino", columnDefinition = "TEXT", nullable = false)
    private String direccionDestino;

    // DATOS LOGÍSTICOS ---------------

    @Column(name = "distancia_km", precision = 8, scale = 2)
    private BigDecimal distanciaKm;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoEnvio estado = EstadoEnvio.PREPARANDO;

    @Column(name = "firma_recepcion", length = 100)
    private String firmaRecepcion;

    @Column(name = "dni_receptor", length = 8)
    private String dniReceptor;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "costo_envio", precision = 10, scale = 2)
    private BigDecimal costoEnvio = BigDecimal.ZERO;

    @Column(name = "peso_total", precision = 8, scale = 2)
    private BigDecimal pesoTotal;

    @Column(name = "volumen_total", precision = 8, scale = 2)
    private BigDecimal volumenTotal;

    // AUDITORÍA ----------------------

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @PreUpdate
    private void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }
    public enum EstadoEnvio {
    	PREPARANDO,
        EN_TRANSITO,
        EN_DESTINO,
        ENTREGADO,
        DEVUELTO,
        INCIDENCIA
    }
   




    // Explicit getters to avoid relying on Lombok in the IDE/build
    public Long getId() { return this.id; }
    public String getNumeroEnvio() { return this.numeroEnvio; }
    public String getNumeroGuia() { return this.numeroGuia; }
    public Pedido getPedido() { return this.pedido; }
    public LocalDateTime getFechaEnvio() { return this.fechaEnvio; }
    public EstadoEnvio getEstado() { return this.estado; }
    public BigDecimal getCostoEnvio() { return this.costoEnvio; }
}