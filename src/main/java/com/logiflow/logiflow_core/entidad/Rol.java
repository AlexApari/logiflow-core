package com.logiflow.logiflow_core.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")	
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "activo")
	private Boolean activo;

	@Column(name = "fecha_creacion")
	private java.util.Date fechaCreacion;

	// Explicit getter for nombre to ensure compilation when Lombok isn't processed
	public String getNombre() {
		return this.nombre;
	}
}