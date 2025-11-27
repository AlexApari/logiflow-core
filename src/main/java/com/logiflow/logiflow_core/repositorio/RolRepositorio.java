package com.logiflow.logiflow_core.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logiflow.logiflow_core.entidad.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{
	Rol findByNombre(String nombre);
}
