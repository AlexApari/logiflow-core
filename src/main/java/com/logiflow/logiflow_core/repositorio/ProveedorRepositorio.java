package com.logiflow.logiflow_core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.logiflow.logiflow_core.entidad.Proveedor;
@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

}
