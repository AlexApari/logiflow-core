package com.logiflow.logiflow_core.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.logiflow.logiflow_core.entidad.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

Optional<Usuario> findById(Long id);
Optional<Usuario> findByNombre(String nombre);
Optional<Usuario> findByEmail(String email);
@Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.nombre = :nombreRol")
List<Usuario> findByRolNombre(@Param("nombreRol") String nombreRol);
}
