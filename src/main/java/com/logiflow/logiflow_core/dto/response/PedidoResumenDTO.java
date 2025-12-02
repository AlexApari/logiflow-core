package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class PedidoResumenDTO {
    private Long id;
    private String numeroPedido;
    private String clienteNombre;
    private LocalDate fechaPedido;
    private String estado;
    private BigDecimal total;
    private String prioridad;
    private String metodoPago;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private List<DetallePedidoReponseDTO> detalles;

    public PedidoResumenDTO() {}

    public PedidoResumenDTO(Long id, String numeroPedido, String clienteNombre, LocalDate fechaPedido, String estado,
			BigDecimal total, String prioridad, String metodoPago, BigDecimal subtotal,
			BigDecimal impuestos, List<DetallePedidoReponseDTO> detalles) {
		super();
		this.id = id;
		this.numeroPedido = numeroPedido;
		this.clienteNombre = clienteNombre;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.total = total;
		this.prioridad = prioridad;
		this.metodoPago = metodoPago;
		this.subtotal = subtotal;
		this.impuestos = impuestos;
		this.detalles = detalles;
	}
    public PedidoResumenDTO(Long id, String numeroPedido, String clienteNombre, LocalDate fechaPedido, String estado,
    					BigDecimal total) {
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
    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(BigDecimal impuestos) {
		this.impuestos = impuestos;
	}

	public List<DetallePedidoReponseDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedidoReponseDTO> detalles) {
		this.detalles = detalles;
	}
}