package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "movimientos_inventario",
    indexes = {
        @Index(name = "idx_producto", columnList = "producto_id"),
        @Index(name = "idx_almacen", columnList = "almacen_id"),
        @Index(name = "idx_fecha", columnList = "fecha_movimiento"),
        @Index(name = "idx_tipo", columnList = "tipo_movimiento"),
        @Index(name = "idx_documento", columnList = "numero_documento")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "producto_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_movimiento_producto")
    )
    private Producto producto;

    // FK Almacén
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "almacen_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_movimiento_almacen")
    )
    private Almacen almacen;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "cantidad_anterior", nullable = false)
    private Integer cantidadAnterior;

    @Column(name = "cantidad_nueva", nullable = false)
    private Integer cantidadNueva;

    @Column(name = "fecha_movimiento")
    private LocalDateTime fechaMovimiento;

    @Column(columnDefinition = "TEXT")
    private String motivo;

    // FK Usuario Responsable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "usuario_responsable",
        foreignKey = @ForeignKey(name = "fk_movimiento_usuario")
    )
    private Usuario usuarioResponsable;

    @Column(name = "numero_documento", length = 50)
    private String numeroDocumento;

    // FK Pedido (Opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "referencia_pedido_id",
        foreignKey = @ForeignKey(name = "fk_movimiento_pedido")
    )
    private Pedido referenciaPedido;

    @Column(name = "costo_unitario", precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "costo_total", precision = 12, scale = 2)
    private BigDecimal costoTotal;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // PrePersist para valores por defecto
    @PrePersist
    public void prePersist() {
        if (fechaMovimiento == null) {
            fechaMovimiento = LocalDateTime.now();
        }
    }

    // ENUM del tipo de movimiento
    public enum TipoMovimiento {
        ENTRADA,
        SALIDA,
        AJUSTE,
        TRANSFERENCIA,
        DEVOLUCION
    }
}
