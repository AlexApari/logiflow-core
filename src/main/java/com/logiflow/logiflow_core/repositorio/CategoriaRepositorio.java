package com.logiflow.logiflow_core.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logiflow.logiflow_core.entidad.Categoria;
@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
	Optional<Categoria> findByNombre(String nombre);
}
