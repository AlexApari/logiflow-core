package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "proveedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ruc", length = 11, nullable = false, unique = true)
    private String ruc;

    @Column(name = "razon_social", length = 200, nullable = false)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 200)
    private String nombreComercial;

    @Column(name = "direccion", columnDefinition = "TEXT", nullable = false)
    private String direccion;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "pais", length = 100)
    private String pais = "Perú";

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "telefono_alternativo", length = 20)
    private String telefonoAlternativo;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "email_alternativo", length = 100)
    private String emailAlternativo;

    @Column(name = "persona_contacto", length = 100)
    private String personaContacto;

    @Column(name = "cargo_contacto", length = 100)
    private String cargoContacto;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicion_pago", nullable = false)
    private CondicionPago condicionPago = CondicionPago.CONTADO;

    @Column(name = "limite_credito", precision = 12, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "calificacion", nullable = false)
    private Calificacion calificacion = Calificacion.BUENO;

    @Column(name = "activo")
    private Boolean activo = true;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    // =============================
    // ENUMS
    // =============================

    public enum CondicionPago {
        CONTADO, CREDITO_15, CREDITO_30, CREDITO_45, CREDITO_60, CREDITO_90
    }

    public enum Calificacion {
        EXCELENTE, BUENO, REGULAR, MALO
    }

    // =============================
    // CALLBACKS
    // =============================

    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = LocalDateTime.now();
    }
}
