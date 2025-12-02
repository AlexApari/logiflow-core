package com.logiflow.logiflow_core.servicio;

import java.util.List;

import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;
import com.logiflow.logiflow_core.dto.response.ProductoResumenDTO;
import com.logiflow.logiflow_core.entidad.*;
import com.logiflow.logiflow_core.repositorio.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImp implements ProductoService {

	
	private ProductoRepositorio productoRepositorio;
	private CategoriaRepositorio categoriaRepositorio;
	private ProveedorRepositorio proveedorRepositorio;
	
	public ProductoServiceImp(ProductoRepositorio productoRepositorio, CategoriaRepositorio categoriaRepositorio, ProveedorRepositorio proveedorRepositorio) {		
        this.productoRepositorio = productoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.proveedorRepositorio = proveedorRepositorio;
    }

	@Override
	public ProductoResumenDTO crearProducto(ProductoRequestDTO productoRequestDTO) {
		// TODO Auto-generated method stub
		if (productoRepositorio.existsByCodigo(productoRequestDTO.getCodigo())) {
			throw new RuntimeException("El código del producto ya existe");
		}
		Categoria categoria = categoriaRepositorio.findById(productoRequestDTO.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
		Proveedor proveedor = proveedorRepositorio.findById(productoRequestDTO.getProveedorId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

		Producto p = new Producto();
		p.setCodigo(productoRequestDTO.getCodigo());
		p.setNombre(productoRequestDTO.getNombre());
		p.setDescripcion(productoRequestDTO.getDescripcion());
		p.setPrecio(productoRequestDTO.getPrecio());
		p.setUnidadMedida(productoRequestDTO.getUnidadMedida());
		p.setStockActual(productoRequestDTO.getStockActual());
		p.setImagen(productoRequestDTO.getImagen());
		p.setActivo(productoRequestDTO.getActivo());
		p.setEsPerecible(productoRequestDTO.getEsPerecible());
		p.setDiasVencimiento(productoRequestDTO.getDiasVencimiento());
		p.setPeso(productoRequestDTO.getPeso());
		p.setDimensiones(productoRequestDTO.getDimensiones());
		p.setCategoria(categoria);
		p.setProveedor(proveedor);

		Producto guardado = productoRepositorio.save(p);
		return convertirAResumenDTO(guardado);
	}

	@Override
	public List<ProductoResumenDTO> obtenerTodosLosProductos() {
		// TODO Auto-generated method stub
		return productoRepositorio.findAll().stream().map(this::convertirAResumenDTO).toList();
	}

	@Override
	public ProductoRequestDTO obtenerProductoParaEditar(String codigo) {
		// TODO Auto-generated method stub
		Producto producto = productoRepositorio.findByCodigo(codigo)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		ProductoRequestDTO dto = new ProductoRequestDTO();
		dto.setCodigo(producto.getCodigo());
		dto.setNombre(producto.getNombre());
		dto.setDescripcion(producto.getDescripcion());
		dto.setPrecio(producto.getPrecio());
		dto.setUnidadMedida(producto.getUnidadMedida());
		dto.setStockActual(producto.getStockActual());
		dto.setImagen(producto.getImagen());
		dto.setActivo(producto.getActivo());
		dto.setEsPerecible(producto.getEsPerecible());
		dto.setDiasVencimiento(producto.getDiasVencimiento());
		dto.setPeso(producto.getPeso());
		dto.setDimensiones(producto.getDimensiones());
		dto.setCategoriaId(producto.getCategoria().getId());
		dto.setProveedorId(producto.getProveedor().getId());

		return dto;
	}

	@Override
	public ProductoResumenDTO actualizarProducto(String codigo, ProductoRequestDTO productoRequestDTO) {
		Producto producto = productoRepositorio.findByCodigo(codigo)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		Categoria categoria = categoriaRepositorio.findById(productoRequestDTO.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

		Proveedor proveedor = proveedorRepositorio.findById(productoRequestDTO.getProveedorId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

		producto.setNombre(productoRequestDTO.getNombre());
		producto.setDescripcion(productoRequestDTO.getDescripcion());
		producto.setPrecio(productoRequestDTO.getPrecio());
		producto.setUnidadMedida(productoRequestDTO.getUnidadMedida());
		// 1. Obtener el stock actual que ya está en la base de datos
		Integer stockActualExistente = producto.getStockActual();

		// 2. Obtener la cantidad que se añade desde el request
		Integer cantidadAñadir = productoRequestDTO.getStockActual();

		// 3. Calcular el nuevo total sumando ambos valores
		Integer nuevoStockTotal = stockActualExistente + cantidadAñadir;

		// 4. Establecer el nuevo total en la entidad
		producto.setStockActual(nuevoStockTotal);

		producto.setImagen(productoRequestDTO.getImagen());
		producto.setActivo(productoRequestDTO.getActivo());
		producto.setEsPerecible(productoRequestDTO.getEsPerecible());
		producto.setDiasVencimiento(productoRequestDTO.getDiasVencimiento());
		producto.setPeso(productoRequestDTO.getPeso());
		producto.setDimensiones(productoRequestDTO.getDimensiones());
		producto.setCategoria(categoria);
		producto.setProveedor(proveedor);
		producto.setActivo(productoRequestDTO.getActivo());

		productoRepositorio.save(producto);

		// Convertir a Resumen
		return convertirAResumenDTO(producto);
	}

	@Override
	@Transactional
	public void cambiarEstadoProducto(String codigo) {
	    Producto producto = productoRepositorio.findByCodigo(codigo)
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

	    producto.setActivo(!Boolean.TRUE.equals(producto.getActivo())); // Cambia de activo ⇄ inactivo

	    productoRepositorio.save(producto);
	}


	private ProductoResumenDTO convertirAResumenDTO(Producto p) {
		ProductoResumenDTO dto = new ProductoResumenDTO();
		dto.setId(p.getId());
		dto.setCodigo(p.getCodigo());
		dto.setNombre(p.getNombre());
		dto.setPrecio(p.getPrecio());
		dto.setStockActual(p.getStockActual());
		dto.setCategoriaNombre(p.getCategoria().getNombre());
		dto.setActivo(p.getActivo());
		return dto;
	}

}
