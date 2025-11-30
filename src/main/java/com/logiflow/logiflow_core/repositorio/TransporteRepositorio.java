package com.logiflow.logiflow_core.repositorio;

import com.logiflow.logiflow_core.entidad.Transporte;
import com.logiflow.logiflow_core.entidad.Transporte.EstadoTransporte;
import com.logiflow.logiflow_core.entidad.Transporte.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {

    // Buscar por placa exacta
    Optional<Transporte> findByPlacaVehiculo(String placaVehiculo);

    // Filtrar por estado (DISPONIBLE, EN_RUTA, etc.)
    List<Transporte> findByEstado(EstadoTransporte estado);

    // Filtrar por tipo de vehículo (CAMION, CAMIONETA, etc.)
    List<Transporte> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

    // Buscar vehículos activos
    List<Transporte> findByActivoTrue();

    // Buscar los próximos a mantenimiento (fecha próximo mantenimiento antes de X)
    List<Transporte> findByFechaProximoMantenimientoBefore(LocalDate fecha);

    // Filtrar por capacidad de carga mínima
    List<Transporte> findByCapacidadCargaGreaterThanEqual(java.math.BigDecimal capacidad);

    // Buscar por conductor
    List<Transporte> findByConductorContainingIgnoreCase(String conductor);

    // Buscar por vencimiento de SOAT antes de fecha dada
    List<Transporte> findBySoatVencimientoBefore(LocalDate fecha);

    // Buscar por revisión técnica próxima a vencer
    List<Transporte> findByRevisionTecnicaVencimientoBefore(LocalDate fecha);

    // Buscar por licencia del conductor
    Optional<Transporte> findByLicenciaConductor(String licenciaConductor);
}