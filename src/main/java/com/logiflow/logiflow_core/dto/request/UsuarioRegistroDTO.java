package com.logiflow.logiflow_core.dto.request;

import java.time.LocalDateTime;

public class UsuarioRegistroDTO {

	private Long id;
	private String username;
	private String password;
	private String email;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private Boolean activo;
	private LocalDateTime fechaCreacion;
	private LocalDateTime ultimoAcceso;
	private UsuarioRegistroDTO creadoPor;
	private UsuarioRegistroDTO modificadoPor;
	private LocalDateTime fechaModificacion;
	private String rol;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellido() { return apellido; }
	public void setApellido(String apellido) { this.apellido = apellido; }

	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }

	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }

	public Boolean getActivo() { return activo; }
	public void setActivo(Boolean activo) { this.activo = activo; }

	public LocalDateTime getFechaCreacion() { return fechaCreacion; }
	public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

	public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
	public void setUltimoAcceso(LocalDateTime ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

	public UsuarioRegistroDTO getCreadoPor() { return creadoPor; }
	public void setCreadoPor(UsuarioRegistroDTO creadoPor) { this.creadoPor = creadoPor; }

	public UsuarioRegistroDTO getModificadoPor() { return modificadoPor; }
	public void setModificadoPor(UsuarioRegistroDTO modificadoPor) { this.modificadoPor = modificadoPor; }

	public LocalDateTime getFechaModificacion() { return fechaModificacion; }
	public void setFechaModificacion(LocalDateTime fechaModificacion) { this.fechaModificacion = fechaModificacion; }

	public String getRol() { return rol; }
	public void setRol(String rol) { this.rol = rol; }

}