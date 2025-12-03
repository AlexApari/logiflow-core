package com.logiflow.logiflow_core.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClienteRequestDTO {
	private String tipoDocumento;
	private String numeroDocumento;
	private String nombres;
	private String apellidos;
	private String razonSocial;
	private String nombreComercial;
	private String direccion;
	private String direccionAlternativa;
	private String ciudad;
	private String departamento;
    private String codigoPostal;
    private String telefono;
    private String telefonoAlternativo;
    private String email;
    private String emailAlternativo;
    private LocalDate fechaNacimiento;
    private String genero;
    private String tipoCliente;
    private String categoriaCliente;
    private BigDecimal limiteCredito;
    private BigDecimal descuentoPorcentaje;
    private Boolean activo;
    private String notas;
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDireccionAlternativa() {
		return direccionAlternativa;
	}
	public void setDireccionAlternativa(String direccionAlternativa) {
		this.direccionAlternativa = direccionAlternativa;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefonoAlternativo() {
		return telefonoAlternativo;
	}
	public void setTelefonoAlternativo(String telefonoAlternativo) {
		this.telefonoAlternativo = telefonoAlternativo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailAlternativo() {
		return emailAlternativo;
	}
	public void setEmailAlternativo(String emailAlternativo) {
		this.emailAlternativo = emailAlternativo;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getCategoriaCliente() {
		return categoriaCliente;
	}
	public void setCategoriaCliente(String categoriaCliente) {
		this.categoriaCliente = categoriaCliente;
	}
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public BigDecimal getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}
	public void setDescuentoPorcentaje(BigDecimal descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public ClienteRequestDTO(String tipoDocumento, String numeroDocumento, String nombres, String apellidos,
			String razonSocial, String nombreComercial, String direccion, String direccionAlternativa, String ciudad,
			String departamento, String codigoPostal, String telefono, String telefonoAlternativo, String email,
			String emailAlternativo, LocalDate fechaNacimiento, String genero, String tipoCliente,
			String categoriaCliente, BigDecimal limiteCredito, BigDecimal descuentoPorcentaje, Boolean activo,
			String notas) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.direccion = direccion;
		this.direccionAlternativa = direccionAlternativa;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.telefonoAlternativo = telefonoAlternativo;
		this.email = email;
		this.emailAlternativo = emailAlternativo;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.tipoCliente = tipoCliente;
		this.categoriaCliente = categoriaCliente;
		this.limiteCredito = limiteCredito;
		this.descuentoPorcentaje = descuentoPorcentaje;
		this.activo = activo;
		this.notas = notas;
	}
	public ClienteRequestDTO() {
		super();
	}
	
}
