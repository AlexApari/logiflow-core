package com.logiflow.logiflow_core.servicio;

import java.util.List;

import com.logiflow.logiflow_core.dto.request.PedidoRequestDTO;
import com.logiflow.logiflow_core.dto.response.PedidoResumenDTO;


public interface PedidoServicio {
  PedidoResumenDTO registrar(PedidoRequestDTO pedidoRequestDTO);
  PedidoResumenDTO obtenerPorId(Long Id);
  List<PedidoResumenDTO>listar();
  
}
