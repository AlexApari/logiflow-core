package com.logiflow.logiflow_core.dto.request;

import java.math.BigDecimal;


public class DetallePedidoDTO {
	
private Long pedidoId;
	private Long productoId;
  private Integer cantidad;
  private BigDecimal descuentoPorcentaje;
  private String notas;
  // Getters and Setters
  
  public Integer getCantidad() {
	return cantidad;
  }
  public void setCantidad(Integer cantidad) {
	this.cantidad = cantidad;
  }
  public BigDecimal getDescuentoPorcentaje() {
	return descuentoPorcentaje;
  }
  public void setDescuentoPorcentaje(BigDecimal descuentoPorcentaje) {
	this.descuentoPorcentaje = descuentoPorcentaje;
  }
  public String getNotas() {
	return notas;
  }
  public void setNotas(String notas) {
	this.notas = notas;
  }
  public DetallePedidoDTO(Long pedidoId,Long productoId, Integer cantidad, BigDecimal descuentoPorcentaje, String notas) {
	super();
	this.pedidoId = pedidoId;
	this.productoId = productoId;
	this.cantidad = cantidad;
	this.descuentoPorcentaje = descuentoPorcentaje;
	this.notas = notas;
  }
  public DetallePedidoDTO() {
	super();
	// TODO Auto-generated constructor stub
  }
  public Long getPedidoId() {
	return pedidoId;
  }
  public void setPedidoId(Long pedidoId) {
	this.pedidoId = pedidoId;
  }
  public Long getProductoId() {
	return productoId;
  }
  public void setProductoId(Long productoId) {
	this.productoId = productoId;
  }
  
}
