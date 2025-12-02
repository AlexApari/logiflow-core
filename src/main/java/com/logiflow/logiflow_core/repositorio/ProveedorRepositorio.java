package com.logiflow.logiflow_core.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.logiflow.logiflow_core.entidad.Proveedor;
@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

		Optional<Proveedor> findById(Long id);

		Optional<Proveedor> findByRuc(String ruc);
}
