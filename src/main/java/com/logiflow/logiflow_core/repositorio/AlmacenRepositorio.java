package com.logiflow.logiflow_core.repositorio;

import com.logiflow.logiflow_core.entidad.Almacen;
import com.logiflow.logiflow_core.entidad.Almacen.TipoAlmacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AlmacenRepositorio extends JpaRepository<Almacen, Long> {

    // Buscar por código (único)
    Almacen findByCodigo(String codigo);

    // Buscar por nombre (único)
    Almacen findByNombre(String nombre);

    // Lista de almacenes activos
    List<Almacen> findByActivoTrue();

    // Lista de almacenes por tipo
    List<Almacen> findByTipoAlmacen(TipoAlmacen tipo);

    // Últimos almacenes creados
    List<Almacen> findTop10ByOrderByFechaCreacionDesc();

    // Contar almacenes por estado (activo / inactivo)
    Long countByActivo(Boolean activo);

    // Contar almacenes por tipo
    Long countByTipoAlmacen(TipoAlmacen tipo);

    // Total capacidad máxima
    @Query("SELECT COALESCE(SUM(a.capacidadMaxima), 0) FROM Almacen a")
    BigDecimal totalCapacidadMaxima();

    // Total capacidad utilizada
    @Query("SELECT COALESCE(SUM(a.capacidadUtilizada), 0) FROM Almacen a")
    BigDecimal totalCapacidadUtilizada();

    // Buscar almacenes por ciudad
    List<Almacen> findByCiudadIgnoreCase(String ciudad);

    // Buscar almacenes con capacidad disponible (capacidad_utilizada < capacidad_maxima)
    @Query("""
            SELECT a 
            FROM Almacen a 
            WHERE a.capacidadUtilizada < a.capacidadMaxima
            """)
    List<Almacen> findAlmacenesConEspacioDisponible();

    // Buscar almacén por responsable
    List<Almacen> findByResponsableIgnoreCase(String responsable);

    // Buscar almacenes creados entre dos fechas
    @Query("""
            SELECT a 
            FROM Almacen a 
            WHERE a.fechaCreacion BETWEEN :inicio AND :fin
            ORDER BY a.fechaCreacion DESC
            """)
    List<Almacen> buscarPorRangoFechas(
            @Param("inicio") java.time.LocalDateTime inicio,
            @Param("fin") java.time.LocalDateTime fin
    );
}