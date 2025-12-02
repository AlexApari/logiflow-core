package com.logiflow.logiflow_core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logiflow.logiflow_core.entidad.DetallePedido;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Long> {

}
