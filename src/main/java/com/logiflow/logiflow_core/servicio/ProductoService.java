package com.logiflow.logiflow_core.servicio;

import java.util.List;

import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;
import com.logiflow.logiflow_core.dto.response.ProductoResumenDTO;


public interface ProductoService {
	
	ProductoResumenDTO crearProducto(ProductoRequestDTO productoRequestDTO);
	List<ProductoResumenDTO> obtenerTodosLosProductos();
	ProductoRequestDTO obtenerProductoParaEditar(String codigo);
	ProductoResumenDTO actualizarProducto(String codigo, ProductoRequestDTO productoRequestDTO);
	void cambiarEstadoProducto(String codigo);
	

}
