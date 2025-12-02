package com.logiflow.logiflow_core.dto.response;

import java.math.BigDecimal;

public class ProductoResumenDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String categoriaNombre;
    private Integer stockActual;
    private BigDecimal precio;
    private Boolean Activo;

    public ProductoResumenDTO() {}

    public ProductoResumenDTO(Long id, String codigo, String nombre,
                              String categoriaNombre, Integer stockActual, BigDecimal precio, Boolean Activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoriaNombre = categoriaNombre;
        this.stockActual = stockActual;
        this.precio = precio;
        this.Activo= Activo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoriaNombre() {
		return categoriaNombre;
	}

	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}

	public Integer getStockActual() {
		return stockActual;
	}

	public void setStockActual(Integer stockActual) {
		this.stockActual = stockActual;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

    // Getters y setters...
	public ProductoResumenDTO(String codigo, String nombre, BigDecimal precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

	public Boolean getActivo() {
		return Activo;
	}

	public void setActivo(Boolean activo) {
		Activo = activo;
	}

}