package com.logiflow.logiflow_core.dto.request;

import java.math.BigDecimal;

public class ProductoRequestDTO {
	    private String codigo;
	    private String nombre;
	    private String descripcion;

	    private Long categoriaId;
	    private Long proveedorId;

	    private BigDecimal precio;
	    private String unidadMedida;

	    private Integer stockInicial;

	    private String imagen;

	    private Boolean activo;
	    private Boolean esPerecible;
	    private Integer diasVencimiento;

	    private BigDecimal peso;
	    private String dimensiones;
		// Getters and Setters
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
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Long getCategoriaId() {
			return categoriaId;
		}
		public void setCategoriaId(Long categoriaId) {
			this.categoriaId = categoriaId;
		}
		public Long getProveedorId() {
			return proveedorId;
		}
		public void setProveedorId(Long proveedorId) {
			this.proveedorId = proveedorId;
		}
		public BigDecimal getPrecio() {
			return precio;
		}
		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}
		public String getUnidadMedida() {
			return unidadMedida;
		}
		public void setUnidadMedida(String unidadMedida) {
			this.unidadMedida = unidadMedida;
		}
		public Integer getStockInicial() {
			return stockInicial;
		}
		public void setStockInicial(Integer stockInicial) {
			this.stockInicial = stockInicial;
		}
		public String getImagen() {
			return imagen;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
		public Boolean getActivo() {
			return activo;
		}
		public void setActivo(Boolean activo) {
			this.activo = activo;
		}
		public Boolean getEsPerecible() {
			return esPerecible;
		}
		public void setEsPerecible(Boolean esPerecible) {
			this.esPerecible = esPerecible;
		}
		public Integer getDiasVencimiento() {
			return diasVencimiento;
		}
		public void setDiasVencimiento(Integer diasVencimiento) {
			this.diasVencimiento = diasVencimiento;
		}
		public BigDecimal getPeso() {
			return peso;
		}
		public void setPeso(BigDecimal peso) {
			this.peso = peso;
		}
		public String getDimensiones() {
			return dimensiones;
		}
		public void setDimensiones(String dimensiones) {
			this.dimensiones = dimensiones;
		}
	    
		public ProductoRequestDTO() {
			super();
		}
		public ProductoRequestDTO(String codigo, String nombre, String descripcion, Long categoriaId,
				Long proveedorId, BigDecimal precio, String unidadMedida, Integer stockInicial, String imagen,
				Boolean activo, Boolean esPerecible, Integer diasVencimiento, BigDecimal peso, String dimensiones) {
			super();
			this.codigo = codigo;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.categoriaId = categoriaId;
			this.proveedorId = proveedorId;
			this.precio = precio;
			this.unidadMedida = unidadMedida;
			this.stockInicial = stockInicial;
			this.imagen = imagen;
			this.activo = activo;
			this.esPerecible = esPerecible;
			this.diasVencimiento = diasVencimiento;
			this.peso = peso;
			this.dimensiones = dimensiones;
		}
}
