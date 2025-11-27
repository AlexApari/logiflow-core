package com.logiflow.logiflow_core.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logiflow.logiflow_core.repositorio.*;
import com.logiflow.logiflow_core.dto.request.UsuarioRegistroDTO;
import com.logiflow.logiflow_core.dto.response.UsuarioRespuestaDTO;
import com.logiflow.logiflow_core.entidad.Usuario;
import lombok.*;
@Service
public class UsuarioServicioImp implements UsuarioServicio{
	
	private BCryptPasswordEncoder passwordEncoder;
	private UsuarioRepositorio usuarioRepositorio;
	private RolRepositorio rolRepositorio;
	
	public UsuarioServicioImp(BCryptPasswordEncoder passwordEncoder, UsuarioRepositorio usuarioRepositorio,
					RolRepositorio rolRepositiorio) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.usuarioRepositorio = usuarioRepositorio;
		this.rolRepositorio = rolRepositiorio;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Try to find by email first, then by username
		Usuario usuario = usuarioRepositorio.findByEmail(username)
				.orElseGet(() -> usuarioRepositorio.findAll().stream()
						.filter(u -> username.equals(u.getUsername()))
						.findFirst()
						.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username)));

		Collection<GrantedAuthority> authorities = usuario.getRoles() == null ?
				java.util.Collections.emptyList() :
				usuario.getRoles().stream()
					.map(r -> new SimpleGrantedAuthority(r.getNombre()))
					.collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), authorities);
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		// Build Usuario from DTO, encode password and assign default ADMIN role
		
	}

	@Override
	public List<UsuarioRespuestaDTO> obtenerTodos() {
		// Map Usuario to UsuarioRespuestaDTO; pick the first role if present
		return usuarioRepositorio.findAll().stream().map(u -> {
			UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO();
			dto.setId(u.getId());
			dto.setNombre(u.getNombre());
			dto.setEmail(u.getEmail());
			dto.setRol(u.getRoles() == null ? null : u.getRoles().stream().findFirst().orElse(null));
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public UsuarioRegistroDTO obtenerParaEditar(Integer id) {
		// TODO: implement
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		UsuarioRegistroDTO dto = new UsuarioRegistroDTO();
		dto.setId(usuario.getId());
		dto.setUsername(usuario.getUsername());
		dto.setEmail(usuario.getEmail());
		dto.setNombre(usuario.getNombre());
		dto.setApellido(usuario.getApellido());
		dto.setTelefono(usuario.getTelefono());
		dto.setDireccion(usuario.getDireccion());
		dto.setActivo(usuario.getActivo());
		dto.setPassword(null); // Do not expose password
		dto.setRol(null);
		return dto;
	}

	@Override
	public UsuarioRespuestaDTO crearUsuario(UsuarioRegistroDTO dto) {
		// TODO: implement
		Rol rol = rolRepositorio.findByNombre("ADMIN");
		Usuario usuario = new Usuario();
		usuario.setUsername(dto.getUsername());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		usuario.setEmail(dto.getEmail());
		usuario.setNombre(dto.getNombre());
		usuario.setApellido(dto.getApellido());
		usuario.setTelefono(dto.getTelefono());
		usuario.setDireccion(dto.getDireccion());
		usuario.setActivo(dto.getActivo() != null ? registroDTO.getActivo() : true);
		usuario.setRoles(Arrays.asList(rol));
		return usuarioRepositorio.save(usuario);
	}


	@Override
	public UsuarioRespuestaDTO actualizarUsuario(Integer id, UsuarioRegistroDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuario(Integer id) {
		// TODO Auto-generated method stub
		
	}

}