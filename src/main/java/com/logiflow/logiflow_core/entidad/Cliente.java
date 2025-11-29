package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 15)
    private String numeroDocumento;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(length = 100)
    private String apellidos;

    @Column(name = "razon_social", length = 200)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 200)
    private String nombreComercial;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "direccion_alternativa", columnDefinition = "TEXT")
    private String direccionAlternativa;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(length = 100)
    private String departamento;

    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @Column(length = 20)
    private String telefono;

    @Column(name = "telefono_alternativo", length = 20)
    private String telefonoAlternativo;

    @Column(length = 100)
    private String email;

    @Column(name = "email_alternativo", length = 100)
    private String emailAlternativo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", length = 10)
    private TipoCliente tipoCliente = TipoCliente.NATURAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_cliente", length = 10)
    private CategoriaCliente categoriaCliente = CategoriaCliente.REGULAR;

    @Column(name = "limite_credito", precision = 12, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;

    @Column(name = "descuento_porcentaje", precision = 5, scale = 2)
    private BigDecimal descuentoPorcentaje = BigDecimal.ZERO;

    private Boolean activo = true;

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private LocalDateTime fechaModificacion;
    
    public enum CategoriaCliente {
        VIP, REGULAR, OCASIONAL
    }
    public enum Genero {
        M, F, OTRO
    }

public enum TipoCliente {
    NATURAL, JURIDICO
}

	public enum TipoDocumento {
		DNI, RUC, PASAPORTE, CARNET_EXTRANJERIA
	}
}
