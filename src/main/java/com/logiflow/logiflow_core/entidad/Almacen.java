package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "almacenes",
    indexes = {
        @Index(name = "idx_codigo", columnList = "codigo"),
        @Index(name = "idx_nombre", columnList = "nombre"),
        @Index(name = "idx_activo", columnList = "activo"),
        @Index(name = "idx_tipo", columnList = "tipo_almacen")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String direccion;

    @Column(length = 100)
    private String ciudad;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String responsable;

    @Column(name = "capacidad_maxima", precision = 12, scale = 2)
    private BigDecimal capacidadMaxima;

    @Column(name = "capacidad_utilizada", precision = 12, scale = 2)
    private BigDecimal capacidadUtilizada;

    @Column(name = "unidad_capacidad", length = 10)
    private String unidadCapacidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_almacen", length = 20)
    private TipoAlmacen tipoAlmacen;

    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;


    // Auto timestamps
    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        if (activo == null) activo = true;
        if (tipoAlmacen == null) tipoAlmacen = TipoAlmacen.SECUNDARIO;
        if (unidadCapacidad == null) unidadCapacidad = "M3";
        if (capacidadUtilizada == null) capacidadUtilizada = BigDecimal.ZERO;
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }


    // ENUM interno
    public enum TipoAlmacen {
        PRINCIPAL,
        SECUNDARIO,
        DEPOSITO,
        TEMPORAL
    }
}
