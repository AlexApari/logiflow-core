package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;

public class ProveedorResponseDTO {

    private Long id;
    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    
    private String direccion;
    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	private String ciudad;
    private String pais;

    private String telefono;
    private String email;

    private String personaContacto;
    private String cargoContacto;

    private String condicionPago;
    private String calificacion;

    private BigDecimal limiteCredito;

    private Boolean activo;

    public ProveedorResponseDTO() {}

    public ProveedorResponseDTO(Long id, String ruc, String razonSocial, String nombreComercial,
							   String direccion, String ciudad, String pais,
							   String telefono, String email,
							   String personaContacto, String cargoContacto,
							   String condicionPago, String calificacion,
							   BigDecimal limiteCredito, Boolean activo) {
		this.id = id;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.nombreComercial = nombreComercial;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.pais = pais;
		this.telefono = telefono;
		this.email = email;
		this.personaContacto = personaContacto;
		this.cargoContacto = cargoContacto;
		this.condicionPago = condicionPago;
		this.calificacion = calificacion;
		this.limiteCredito = limiteCredito;
		this.activo = activo;
	}

	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

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
}