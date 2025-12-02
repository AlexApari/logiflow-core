package com.logiflow.logiflow_core.dto.request;

import java.math.BigDecimal;

public class ProveedorRequestDTO {
	
	private Long Id;

    public void setId(Long id) {
		Id = id;
	}
	// Información principal
    private String ruc;
    private String razonSocial;
    private String nombreComercial;

    // Ubicación
    private String direccion;
    private String ciudad;
    private String pais;

    // Contacto
    private String telefono;
    private String telefonoAlternativo;
    private String email;
    private String emailAlternativo;

    // Persona de contacto
    private String personaContacto;
    private String cargoContacto;

    // Enums
    private String condicionPago;
    private String calificacion;

    // Financiero
    private BigDecimal limiteCredito;

    // Estado
    private Boolean activo;

    // Notas
    private String notas;
    
    

    public ProveedorRequestDTO(Long id, String ruc, String razonSocial, String nombreComercial, String direccion,
			String ciudad, String pais, String telefono, String telefonoAlternativo, String email,
			String emailAlternativo, String personaContacto, String cargoContacto, String condicionPago,
			String calificacion, BigDecimal limiteCredito, Boolean activo, String notas) {
		super();
		Id = id;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
		this.telefono = telefono;
		this.telefonoAlternativo = telefonoAlternativo;
		this.email = email;
		this.emailAlternativo = emailAlternativo;
		this.personaContacto = personaContacto;
		this.cargoContacto = cargoContacto;
		this.condicionPago = condicionPago;
		this.calificacion = calificacion;
		this.limiteCredito = limiteCredito;
		this.activo = activo;
		this.notas = notas;
	}
    
    
	public ProveedorRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	// Getters y Setters
    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTelefonoAlternativo() { return telefonoAlternativo; }
    public void setTelefonoAlternativo(String telefonoAlternativo) { this.telefonoAlternativo = telefonoAlternativo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEmailAlternativo() { return emailAlternativo; }
    public void setEmailAlternativo(String emailAlternativo) { this.emailAlternativo = emailAlternativo; }

    public String getPersonaContacto() { return personaContacto; }
    public void setPersonaContacto(String personaContacto) { this.personaContacto = personaContacto; }

    public String getCargoContacto() { return cargoContacto; }
    public void setCargoContacto(String cargoContacto) { this.cargoContacto = cargoContacto; }

    public String getCondicionPago() { return condicionPago; }
    public void setCondicionPago(String condicionPago) { this.condicionPago = condicionPago; }

    public String getCalificacion() { return calificacion; }
    public void setCalificacion(String calificacion) { this.calificacion = calificacion; }

    public BigDecimal getLimiteCredito() { return limiteCredito; }
    public void setLimiteCredito(BigDecimal limiteCredito) { this.limiteCredito = limiteCredito; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
	public Long getId() {
		// TODO Auto-generated method stub
		return Id;
	}
}