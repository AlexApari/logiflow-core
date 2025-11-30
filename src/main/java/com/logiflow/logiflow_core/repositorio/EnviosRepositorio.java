package com.logiflow.logiflow_core.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logiflow.logiflow_core.entidad.Envios;
import com.logiflow.logiflow_core.entidad.Envios.EstadoEnvio;
import org.springframework.stereotype.Repository;
@Repository
public interface EnviosRepositorio extends JpaRepository<Envios, Long> {

    // Latest shipments to show on index
    List<Envios> findTop10ByOrderByFechaEnvioDesc();

    // Count shipments by state
    Long countByEstado(EstadoEnvio e);

    // Total number of shipments
    @Query("SELECT COUNT(e) FROM Envios e")
    Long contarEnvios();

    // Total shipping cost
    @Query("SELECT COALESCE(SUM(e.costoEnvio), 0) FROM Envios e")
    BigDecimal totalCostoEnvios();

    // Recent shipments by state
    List<Envios> findTop5ByEstadoOrderByFechaEnvioDesc(Envios estado);

    // Find shipments for a given pedido id
    @Query("SELECT e FROM Envios e WHERE e.pedido.id = :pedidoId ORDER BY e.fechaEnvio DESC")
    List<Envios> findByPedidoIdOrderByFechaEnvioDesc(@Param("pedidoId") Long pedidoId);

    // Shipments by transport company
    @Query("SELECT e FROM Envios e WHERE e.transporte.id = :transporteId ORDER BY e.fechaEnvio DESC")
    List<Envios> findByTransporteIdOrderByFechaEnvioDesc(@Param("transporteId") Long transporteId);
}