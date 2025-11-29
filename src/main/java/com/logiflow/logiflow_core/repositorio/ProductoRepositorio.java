package com.logiflow.logiflow_core.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logiflow.logiflow_core.entidad.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
	
	boolean existsByCodigo(String codigo);
	
	Optional<Producto> findByCodigo(String codigo);
	List<Producto> findAll();
	@Query("SELECT COUNT(p) FROM Producto p WHERE p.categoria.id = :categoriaId AND p.activo = true")
    Long contarProductosPorCategoria(@Param("categoriaId") Long categoriaId);
	 @Query("SELECT p FROM Producto p " +
	           "JOIN DetallePedido dp ON dp.producto.id = p.id " +
	           "GROUP BY p.id " +
	           "ORDER BY SUM(dp.cantidad) DESC")
	    List<Producto> findProductosMasVendidos();
}
