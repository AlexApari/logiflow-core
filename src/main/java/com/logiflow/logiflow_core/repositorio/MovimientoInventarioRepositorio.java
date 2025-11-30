package com.logiflow.logiflow_core.repositorio;

import com.logiflow.logiflow_core.entidad.MovimientoInventario;
import com.logiflow.logiflow_core.entidad.MovimientoInventario.TipoMovimiento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientoInventarioRepositorio extends JpaRepository<MovimientoInventario, Long> {

    // --------------------------------------------
    // BÚSQUEDAS BÁSICAS
    // --------------------------------------------

    // Movimientos por producto
    List<MovimientoInventario> findByProductoId(Long productoId);

    // Movimientos por almacén
    List<MovimientoInventario> findByAlmacenId(Long almacenId);

    // Movimientos por tipo
    List<MovimientoInventario> findByTipoMovimiento(TipoMovimiento tipo);

    // Movimientos por número de documento
    List<MovimientoInventario> findByNumeroDocumento(String numeroDocumento);

    // Lista de movimientos por pedido relacionado
    List<MovimientoInventario> findByReferenciaPedidoId(Long pedidoId);


    // --------------------------------------------
    // CONSULTAS AVANZADAS
    // --------------------------------------------

    // Movimientos recientes por almacén (los últimos N)
    List<MovimientoInventario> findTop10ByAlmacenIdOrderByFechaMovimientoDesc(Long almacenId);

    // Buscar movimientos en un rango de fechas
    @Query("""
        SELECT m FROM MovimientoInventario m
        WHERE m.fechaMovimiento BETWEEN :inicio AND :fin
        ORDER BY m.fechaMovimiento DESC
    """)
    List<MovimientoInventario> buscarPorRangoFechas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    // Buscar por producto, tipo y rango de fechas
    @Query("""
        SELECT m FROM MovimientoInventario m
        WHERE m.producto.id = :productoId
          AND m.tipoMovimiento = :tipo
          AND m.fechaMovimiento BETWEEN :inicio AND :fin
        ORDER BY m.fechaMovimiento DESC
    """)
    List<MovimientoInventario> buscarMovimientosProductoFechas(
            @Param("productoId") Long productoId,
            @Param("tipo") TipoMovimiento tipo,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    // Buscar movimientos que contengan texto en motivo u observaciones
    @Query("""
        SELECT m FROM MovimientoInventario m
        WHERE LOWER(m.motivo) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(m.observaciones) LIKE LOWER(CONCAT('%', :texto, '%'))
    """)
    List<MovimientoInventario> buscarPorTexto(@Param("texto") String texto);


    // --------------------------------------------
    // MÉTRICAS Y AGREGADOS
    // --------------------------------------------

    // Cantidad total movida de un producto (sumatoria)
    @Query("""
        SELECT COALESCE(SUM(m.cantidad), 0)
        FROM MovimientoInventario m
        WHERE m.producto.id = :productoId
    """)
    Long cantidadTotalMovidaPorProducto(@Param("productoId") Long productoId);

    // Total de entradas o salidas de un producto
    @Query("""
        SELECT COALESCE(SUM(m.cantidad), 0)
        FROM MovimientoInventario m
        WHERE m.producto.id = :productoId
          AND m.tipoMovimiento = :tipo
    """)
    Long cantidadTotalPorTipo(
            @Param("productoId") Long productoId,
            @Param("tipo") TipoMovimiento tipo
    );

    // Suma de costo total por almacén
    @Query("""
        SELECT COALESCE(SUM(m.costoTotal), 0)
        FROM MovimientoInventario m
        WHERE m.almacen.id = :almacenId
    """)
    BigDecimal costoTotalPorAlmacen(@Param("almacenId") Long almacenId);

    // Total de costos por producto
    @Query("""
        SELECT COALESCE(SUM(m.costoTotal), 0)
        FROM MovimientoInventario m
        WHERE m.producto.id = :productoId
    """)
    BigDecimal costoTotalPorProducto(@Param("productoId") Long productoId);


    // --------------------------------------------
    // ORDENAMIENTOS
    // --------------------------------------------

    // Movimientos por producto ordenados por fecha más reciente
    List<MovimientoInventario> findByProductoIdOrderByFechaMovimientoDesc(Long productoId);

    // Movimientos por almacén ordenados por fecha más reciente
    List<MovimientoInventario> findByAlmacenIdOrderByFechaMovimientoDesc(Long almacenId);
}