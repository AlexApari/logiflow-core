package com.logiflow.logiflow_core.servicio;

import java.util.List;

import com.logiflow.logiflow_core.dto.request.ClienteRequestDTO;
import com.logiflow.logiflow_core.dto.response.ClienteResumenDTO;

public interface ClienteService {
	
	ClienteResumenDTO crearCliente(ClienteRequestDTO clienreRequestDTO);
	List<ClienteResumenDTO> obtenerTodosLosClientes();
	ClienteRequestDTO obtenerClienteParaEditar(Long id); 
	ClienteResumenDTO actualizarCliente(Long id,ClienteRequestDTO clienteRequestDTO);
	void cambiarEstadoCliente(Long id);
	List<ClienteResumenDTO> listarPorCategoria(String categoriaCliente);
	

}
