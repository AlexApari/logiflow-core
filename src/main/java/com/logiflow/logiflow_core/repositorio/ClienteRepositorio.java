package com.logiflow.logiflow_core.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logiflow.logiflow_core.entidad.Cliente;
import com.logiflow.logiflow_core.entidad.Cliente.CategoriaCliente;
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

	List<Cliente> findByCategoriaCliente(CategoriaCliente categoriaCliente);

}
