package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transportes")
@Getter
@Setter
public class Transporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa_vehiculo", length = 10, nullable = false, unique = true)
    private String placaVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo;

    private String marca;
    private String modelo;

    @Column(name = "año_fabricacion")
    private Integer anioFabricacion;

    private String color;

    @Column(nullable = false)
    private String conductor;

    @Column(name = "dni_conductor", length = 8)
    private String dniConductor;

    @Column(name = "licencia_conductor", length = 20, nullable = false)
    private String licenciaConductor;

    @Column(name = "categoria_licencia", length = 10)
    private String categoriaLicencia;

    @Column(name = "telefono_conductor", length = 20)
    private String telefonoConductor;

    @Column(name = "email_conductor", length = 100)
    private String emailConductor;

    @Column(name = "capacidad_carga", nullable = false, precision = 8, scale = 2)
    private BigDecimal capacidadCarga;

    @Column(name = "unidad_capacidad", length = 10)
    private String unidadCapacidad = "KG";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTransporte estado = EstadoTransporte.DISPONIBLE;

    @Column(name = "fecha_ultimo_mantenimiento")
    private LocalDate fechaUltimoMantenimiento;

    @Column(name = "fecha_proximo_mantenimiento")
    private LocalDate fechaProximoMantenimiento;

    private Integer kilometraje;

    @Column(name = "soat_vencimiento")
    private LocalDate soatVencimiento;

    @Column(name = "revision_tecnica_vencimiento")
    private LocalDate revisionTecnicaVencimiento;

    @Column(name = "seguro_vencimiento")
    private LocalDate seguroVencimiento;

    private Boolean activo;

    private String notas;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    /*
     * ==== AUDITORÍA AUTOMÁTICA ====
     */

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        fechaModificacion = LocalDateTime.now();
        if (activo == null) activo = true;
        if (estado == null) estado = EstadoTransporte.DISPONIBLE;
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }

    public enum EstadoTransporte {
   	 DISPONIBLE,
   	    EN_RUTA,
   	    MANTENIMIENTO,
   	    FUERA_SERVICIO,
   	    TALLER
   }
	public enum TipoVehiculo {
		CAMION,
		CAMIONETA,
		FURGON,
		MOTOCICLETA,
		OTRO
	}
}