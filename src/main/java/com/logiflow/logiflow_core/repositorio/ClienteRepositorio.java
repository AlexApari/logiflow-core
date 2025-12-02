package com.logiflow.logiflow_core.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.logiflow.logiflow_core.entidad.Cliente;
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
