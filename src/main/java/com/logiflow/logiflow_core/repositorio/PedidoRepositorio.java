package com.logiflow.logiflow_core.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.logiflow.logiflow_core.entidad.Pedido;
import com.logiflow.logiflow_core.entidad.Pedido.EstadoPedido;
@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

    // Latest orders to show on index
    List<Pedido> findTop10ByOrderByFechaPedidoDesc();

    // Count orders by state (e.g., PENDIENTE, EN_PROCESO, COMPLETADO)
    Long countByEstado(EstadoPedido e);

    // Total number of orders
    @Query("SELECT COUNT(p) FROM Pedido p")
    Long contarPedidos();

    // Total sales (sum of total column) — may return null if no rows
    @Query("SELECT COALESCE(SUM(p.total), 0) FROM Pedido p")
    BigDecimal totalVentas();

    // Recent orders by state
    List<Pedido> findTop5ByEstadoOrderByFechaPedidoDesc(Pedido estado);

    // Find orders for a given cliente id (useful for dashboard widgets)
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId ORDER BY p.fechaPedido DESC")
    List<Pedido> findByClienteIdOrderByFechaPedidoDesc(@Param("clienteId") Long clienteId);
}