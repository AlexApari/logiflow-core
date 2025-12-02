package com.logiflow.logiflow_core.servicio;

import java.util.List;

import com.logiflow.logiflow_core.dto.request.ProveedorRequestDTO;
import com.logiflow.logiflow_core.dto.response.ProveedorResponseDTO;

public interface ProveedoresServicio {

    ProveedorResponseDTO registrarProveedor(ProveedorRequestDTO request);
    ProveedorRequestDTO obtenerProveedorParaEditar(String ruc);
    ProveedorResponseDTO editarProveedor(Long Id, ProveedorRequestDTO request);
    void eliminarProveedor(String ruc); // puede ser delete lógico si 'activo' es false

    ProveedorResponseDTO findById(Long id);
    List<ProveedorResponseDTO> listarProveedores();
    List<ProveedorResponseDTO> listarProveedoresActivos();

    Long contarProveedores();
    Long contarProveedoresActivos();
    Long contarProveedoresInactivos(); 

    List<ProveedorResponseDTO> buscarPorRazonSocial(String texto);
    List<ProveedorResponseDTO> buscarPorCiudad(String ciudad);
}