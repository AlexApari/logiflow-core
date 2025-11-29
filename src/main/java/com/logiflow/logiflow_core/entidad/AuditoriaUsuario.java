package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "auditoria_usuarios",
    indexes = {
        @Index(name = "idx_usuario", columnList = "usuario_id"),
        @Index(name = "idx_fecha", columnList = "fecha_accion"),
        @Index(name = "idx_accion", columnList = "accion")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK usuario_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_audit_usuario"))
    private Usuario usuario;

    // ENUM accion
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccionAuditoria accion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // FK realizado_por (puede ser NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realizado_por",
                foreignKey = @ForeignKey(name = "fk_audit_realizador"))
    private Usuario realizadoPor;

    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "detalles_json", columnDefinition = "TEXT")
    private String detallesJson;


    @PrePersist
    public void prePersist() {
        if (fechaAccion == null) {
            fechaAccion = LocalDateTime.now();
        }
    }

    // ENUM interno
    public enum AccionAuditoria {
        CREAR, MODIFICAR, ELIMINAR, LOGIN, LOGOUT,
        CAMBIO_PASSWORD, BLOQUEO, DESBLOQUEO
    }
}