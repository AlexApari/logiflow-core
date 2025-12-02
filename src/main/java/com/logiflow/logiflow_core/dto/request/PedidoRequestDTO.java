package com.logiflow.logiflow_core.dto.request;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoRequestDTO {
  //Cliente
  private Long clienteId;
  //Entrega
  private String numeroPedido;
  public String getNumeroPedido() {
	return numeroPedido;
}
  public void setNumeroPedido(String numeroPedido) {
	this.numeroPedido = numeroPedido;
  }
  private LocalDate fechaPedido;
  public LocalDate getFechaPedido() {
	return fechaPedido;
}
  public void setFechaPedido(LocalDate fechaPedido) {
	this.fechaPedido = fechaPedido;
  }
  private LocalDate fechaEntregaEstimada;
  private String direccionEntrega;
  private String referenciaDireccion;
  private String ciudadEntrega;
  //enums
  private String estado;
  private String prioridad;
  private String metodoPago;
  //OBservaciones
  private String observaciones;
  public Long getClienteId() {
	return clienteId;
}
  public void setClienteId(Long clienteId) {
	this.clienteId = clienteId;
  }
  public LocalDate getFechaEntregaEstimada() {
	return fechaEntregaEstimada;
  }
  public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
	this.fechaEntregaEstimada = fechaEntregaEstimada;
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
  public String getEstado() {
	return estado;
  }
  public void setEstado(String estado) {
	this.estado = estado;
  }
  public String getPrioridad() {
	return prioridad;
  }
  public void setPrioridad(String prioridad) {
	this.prioridad = prioridad;
  }
  public String getMetodoPago() {
	return metodoPago;
  }
  public void setMetodoPago(String metodoPago) {
	this.metodoPago = metodoPago;
  }
  public String getObservaciones() {
	return observaciones;
  }
  public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
  }
  public List<DetallePedidoDTO> getDetalles() {
	return detalles;
  }
  public void setDetalles(List<DetallePedidoDTO> detalles) {
	this.detalles = detalles;
  }
  //detallesdel pedido
  private List<DetallePedidoDTO> detalles = new java.util.ArrayList<DetallePedidoDTO>();
  public PedidoRequestDTO() {
	super();
	// TODO Auto-generated constructor stub
  }
  public PedidoRequestDTO(long clienteId, LocalDate fechaEntregaEstimada, String direccionEntrega, String referenciaDireccion,
		String ciudadEntrega, String estado, String prioridad,
		String metodoPago, String observaciones, List<DetallePedidoDTO> detalles) {
	super();
	this.clienteId = clienteId;
	this.fechaEntregaEstimada = fechaEntregaEstimada;
	this.direccionEntrega = direccionEntrega;
	this.referenciaDireccion = referenciaDireccion;
	this.ciudadEntrega = ciudadEntrega;
	this.estado = estado;
	this.prioridad = prioridad;
	this.metodoPago = metodoPago;
	this.observaciones = observaciones;
	this.detalles = new ArrayList<DetallePedidoDTO>(detalles);
  }
		

}
