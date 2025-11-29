package com.logiflow.logiflow_core.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "productos",
    indexes = {
        @Index(name = "idx_codigo", columnList = "codigo"),
        @Index(name = "idx_nombre", columnList = "nombre"),
        @Index(name = "idx_categoria", columnList = "categoria_id"),
        @Index(name = "idx_proveedor", columnList = "proveedor_id"),
        @Index(name = "idx_activo", columnList = "activo"),
        @Index(name = "idx_stock_bajo", columnList = "stock_actual, stock_minimo"),
        @Index(name = "idx_codigo_barras", columnList = "codigo_barras")
})
@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // -----------------------
    // DATOS PRINCIPALES
    // -----------------------

    @Column(length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // -----------------------
    // RELACIONES
    // -----------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    // -----------------------
    // PRECIOS Y COMERCIO
    // -----------------------

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "precio_compra", precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "margen_ganancia", precision = 5, scale = 2)
    private BigDecimal margenGanancia;

    @Column(name = "unidad_medida", length = 10, nullable = false)
    private String unidadMedida = "UNIDAD";

    // -----------------------
    // INVENTARIO
    // -----------------------

    @Column(name = "stock_inicial")
    private Integer stockInicial = 0;

    @Column(name = "stock_actual")
    private Integer stockActual = 0;

    @Column(name = "stock_minimo")
    private Integer stockMinimo = 0;

    @Column(name = "stock_maximo")
    private Integer stockMaximo = 1000;

    // -----------------------
    // UBICACIÓN FÍSICA
    // -----------------------

    private String ubicacion;

    private String pasillo;

    private String estante;

    private String nivel;

    // -----------------------
    // DATOS ADICIONALES
    // -----------------------

    @Column(length = 255)
    private String imagen;

    @Column(name = "codigo_barras", length = 50)
    private String codigoBarras;

    private Boolean activo = true;

    @Column(name = "es_perecible")
    private Boolean esPerecible = false;

    @Column(name = "dias_vencimiento")
    private Integer diasVencimiento;

    @Column(precision = 8, scale = 2)
    private BigDecimal peso;

    @Column(length = 50)
    private String dimensiones;

    // -----------------------
    // AUDITORÍA
    // -----------------------

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }

    // Explicit getters to satisfy IDE/compiler when lombok isn't processed
    public Long getId() { return this.id; }
    public String getCodigo() { return this.codigo; }
    public String getNombre() { return this.nombre; }
    public BigDecimal getPrecio() { return this.precio; }
    public Integer getStockActual() { return this.stockActual; }
    public Integer getStockMinimo() { return this.stockMinimo; }
    public LocalDateTime getFechaCreacion() { return this.fechaCreacion; }
}