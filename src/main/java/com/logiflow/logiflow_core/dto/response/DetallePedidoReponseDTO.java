package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;

public class DetallePedidoReponseDTO {

	private Long id;
	private Long pedidoId;
	private String nombreProducto;
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	private int cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal subtotal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public DetallePedidoReponseDTO(Long id, String nombreProducto, int cantidad, BigDecimal precioUnitario,
			BigDecimal subtotal) {
		super();
		this.id = id;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}
	public DetallePedidoReponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
