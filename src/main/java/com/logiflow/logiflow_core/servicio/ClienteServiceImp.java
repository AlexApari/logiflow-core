package com.logiflow.logiflow_core.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.logiflow.logiflow_core.dto.request.ClienteRequestDTO;
import com.logiflow.logiflow_core.dto.response.ClienteResumenDTO;
import com.logiflow.logiflow_core.entidad.Cliente;
import com.logiflow.logiflow_core.repositorio.ClienteRepositorio;


@Service
public class ClienteServiceImp implements ClienteService {

	private ClienteRepositorio clienteRepositorio;

	public ClienteServiceImp(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}

	@Override
	public ClienteResumenDTO crearCliente(ClienteRequestDTO clienteRequestDTO) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente();
		c.setActivo(clienteRequestDTO.getActivo());
		c.setApellidos(clienteRequestDTO.getApellidos());
		c.setCategoriaCliente(Cliente.CategoriaCliente.valueOf(clienteRequestDTO.getCategoriaCliente()));
		c.setCiudad(clienteRequestDTO.getCiudad());
		c.setCodigoPostal(clienteRequestDTO.getCodigoPostal());
		c.setDepartamento(clienteRequestDTO.getDepartamento());
		c.setDescuentoPorcentaje(clienteRequestDTO.getDescuentoPorcentaje());
		c.setDireccion(clienteRequestDTO.getDireccion());
		c.setDireccionAlternativa(clienteRequestDTO.getDireccionAlternativa());
		c.setEmail(clienteRequestDTO.getEmail());
		c.setEmailAlternativo(clienteRequestDTO.getEmailAlternativo());
		c.setFechaNacimiento(clienteRequestDTO.getFechaNacimiento());
		c.setGenero(Cliente.Genero.valueOf(clienteRequestDTO.getGenero()));
		c.setLimiteCredito(clienteRequestDTO.getLimiteCredito());
		c.setNombreComercial(clienteRequestDTO.getNombreComercial());
		c.setNombres(clienteRequestDTO.getNombres());
		c.setNotas(clienteRequestDTO.getNotas());
		c.setNumeroDocumento(clienteRequestDTO.getNumeroDocumento());
		c.setRazonSocial(clienteRequestDTO.getRazonSocial());
		c.setTelefono(clienteRequestDTO.getTelefono());
		c.setTelefonoAlternativo(clienteRequestDTO.getTelefonoAlternativo());
		c.setTipoCliente(Cliente.TipoCliente.valueOf(clienteRequestDTO.getTipoCliente()));
		c.setTipoDocumento(Cliente.TipoDocumento.valueOf(clienteRequestDTO.getTipoDocumento()));
		clienteRepositorio.save(c);
	
		return convertirDTO(c);
	}

	private ClienteResumenDTO convertirDTO(Cliente c) {
		// TODO Auto-generated method stub
		ClienteResumenDTO dto= new ClienteResumenDTO();
		dto.setId(c.getId());
		dto.setTipoDocumento(c.getTipoDocumento().name());
		dto.setNumeroDocumento(c.getNumeroDocumento());
		dto.setNombres(c.getNombres());
		dto.setApellidos(c.getApellidos());
		dto.setNombreComercial(c.getNombreComercial());
		dto.setCiudad(c.getCiudad());
		dto.setTipoCliente(c.getTipoCliente().name());
		dto.setCategoriaCliente(c.getCategoriaCliente().name());
		return dto;
	}

	@Override
	public List<ClienteResumenDTO> obtenerTodosLosClientes() {
		// TODO Auto-generated method stub
		return clienteRepositorio.findAll().stream().map(this::convertirDTO).toList();
	}

	@Override
	public ClienteRequestDTO obtenerClienteParaEditar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteResumenDTO actualizarCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarEstadoCliente(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClienteResumenDTO> listarPorCategoria(String categoriaCliente) {
		// TODO Auto-generated method stub
		Cliente.CategoriaCliente categoriaEnum=Cliente.CategoriaCliente.valueOf(categoriaCliente.toUpperCase());
		return clienteRepositorio.findByCategoriaCliente(categoriaEnum).stream().map(this::convertirDTO).toList();
	}

}
