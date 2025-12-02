package com.logiflow.logiflow_core.entidad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pedidos",
    indexes = {
        @Index(name = "idx_numero_pedido", columnList = "numero_pedido"),
        @Index(name = "idx_cliente", columnList = "cliente_id"),
        @Index(name = "idx_fecha_pedido", columnList = "fecha_pedido"),
        @Index(name = "idx_estado", columnList = "estado"),
        @Index(name = "idx_prioridad", columnList = "prioridad"),
        @Index(name = "idx_fecha_entrega", columnList = "fecha_entrega_estimada")
})
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ------------------------
    // CAMPOS PRINCIPALES
    // ------------------------

    @Column(name = "numero_pedido", length = 20, nullable = false, unique = true)
    private String numeroPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fechaPedido;

    @Column(name = "fecha_entrega_estimada")
    private LocalDate fechaEntregaEstimada;

    @Column(name = "fecha_entrega_real")
    private LocalDate fechaEntregaReal;

    @Column(name = "direccion_entrega", columnDefinition = "TEXT", nullable = false)
    private String direccionEntrega;

    @Column(name = "referencia_direccion", length = 200)
    private String referenciaDireccion;

    @Column(name = "ciudad_entrega", length = 100)
    private String ciudadEntrega;

    // ------------------------
    // ENUMS
    // ------------------------

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad", length = 10)
    private PrioridadPedido prioridad = PrioridadPedido.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", length = 20)
    private MetodoPago metodoPago = MetodoPago.EFECTIVO;

    public enum MetodoPago {
    	EFECTIVO,
        TARJETA,
        TRANSFERENCIA,
        CREDITO
    }
    public enum EstadoPedido {
	    PENDIENTE,
	    PROCESANDO,
	    EMPAQUETADO,
	    ENVIADO,
	    ENTREGADO,
	    CANCELADO,
	    DEVUELTO
	}
    public enum PrioridadPedido {
    	NORMAL,
        ALTA,
        URGENTE
    }


    // ------------------------
    // MONTOS
    // ------------------------

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal impuestos = BigDecimal.ZERO;

    @Column(name = "costo_envio", precision = 12, scale = 2)
    private BigDecimal costoEnvio = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    // ------------------------
    // OBSERVACIONES
    // ------------------------

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "notas_internas", columnDefinition = "TEXT")
    private String notasInternas;

    // ------------------------
    // RELACIONES CON USUARIOS
    // ------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creacion")
    private Usuario usuarioCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_modificacion")
    private Usuario usuarioModificacion;

    // ------------------------
    // AUDITORÍA
    // ------------------------

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }

    @Column(name = "fecha_cancelacion")
    private LocalDateTime fechaCancelacion;

    @Column(name = "motivo_cancelacion", columnDefinition = "TEXT")
    private String motivoCancelacion;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    public List<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	// Explicit getters used by services (avoid relying on Lombok in IDE)
    public Long getId() { return this.id; }
    public String getNumeroPedido() { return this.numeroPedido; }
    public Cliente getCliente() { return this.cliente; }
    public LocalDate getFechaPedido() { return this.fechaPedido; }
    public EstadoPedido getEstado() { return this.estado; }
    public BigDecimal getTotal() { return this.total; }
    public PrioridadPedido getPrioridad() { return this.prioridad; }
	public LocalDate getFechaEntregaEstimada() {
		return fechaEntregaEstimada;
	}
	public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
		this.fechaEntregaEstimada = fechaEntregaEstimada;
	}
	public LocalDate getFechaEntregaReal() {
		return fechaEntregaReal;
	}
	public void setFechaEntregaReal(LocalDate fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	public String getReferenciaDireccion() {
		return referenciaDireccion;
	}
	public void setReferenciaDireccion(String referenciaDireccion) {
		this.referenciaDireccion = referenciaDireccion;
	}
	public String getCiudadEntrega() {
		return ciudadEntrega;
	}
	public void setCiudadEntrega(String ciudadEntrega) {
		this.ciudadEntrega = ciudadEntrega;
	}
	public MetodoPago getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public BigDecimal getImpuestos() {
		return impuestos;
	}
	public void setImpuestos(BigDecimal impuestos) {
		this.impuestos = impuestos;
	}
	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getNotasInternas() {
		return notasInternas;
	}
	public void setNotasInternas(String notasInternas) {
		this.notasInternas = notasInternas;
	}
	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}
	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public LocalDateTime getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(LocalDateTime fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public void setPrioridad(PrioridadPedido prioridad) {
		this.prioridad = prioridad;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
    
}