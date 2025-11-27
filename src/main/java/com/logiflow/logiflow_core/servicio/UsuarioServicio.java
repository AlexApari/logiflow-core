package com.logiflow.logiflow_core.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


import com.logiflow.logiflow_core.dto.request.UsuarioRegistroDTO;
import com.logiflow.logiflow_core.dto.response.UsuarioRespuestaDTO;

public interface UsuarioServicio extends UserDetailsService{
	
	 // Crea un nuevo usuario a partir del DTO
    UsuarioRespuestaDTO crearUsuario(UsuarioRegistroDTO registroDTO);

    // Obtiene todos los usuarios como DTOs
    List<UsuarioRespuestaDTO> obtenerTodos();

    // Obtiene los datos de un usuario para editar
    UsuarioRegistroDTO obtenerParaEditar(Integer id);

    // Actualiza un usuario existente
    UsuarioRespuestaDTO actualizarUsuario(Integer id, UsuarioRegistroDTO dto);

    // Elimina un usuario por id
    void eliminarUsuario(Integer id);
}
