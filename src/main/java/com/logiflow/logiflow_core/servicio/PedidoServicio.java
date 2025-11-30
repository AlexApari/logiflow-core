package com.logiflow.logiflow_core.servicio;

@Service
public interface PedidoServicio {
  PedidoResumenDTO registrar(PedidoRequestDTO);
  PedidoResumenDTO obtenerPorId(Long Id);
  List<PedidoResumenDTO>listar();
}
