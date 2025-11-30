package com.logiflow.logiflow_core.dto.request;
import java.util.List;

public class PedidosRequestDTO {
  //Cliente
  private long clienteId;
  //Entrega
  private LocalDate fechaEntregaEstimada;
  private String direccionEntrega;
  private String referenciaDireccion;
  private String ciudadEntrega;
  //enums
  private Pedido.EstadoPedido estado;
  private Pedido.PrioridadPedido prioridad;
  private Pedido.MetodoPago metodoPago;
  //OBservaciones
  private String observaciones
  //detallesdel pedido
  private List<DetallePedidoRequestDTO> detalles;
}
