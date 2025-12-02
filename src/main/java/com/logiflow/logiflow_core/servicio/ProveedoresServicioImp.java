package com.logiflow.logiflow_core.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logiflow.logiflow_core.dto.request.ProveedorRequestDTO;
import com.logiflow.logiflow_core.dto.response.ProveedorResponseDTO;
import com.logiflow.logiflow_core.entidad.Proveedor;
import com.logiflow.logiflow_core.repositorio.ProveedorRepositorio;

@Service
@Transactional
public class ProveedoresServicioImp implements ProveedoresServicio {

    private final ProveedorRepositorio proveedorRepositorio;

    public ProveedoresServicioImp(ProveedorRepositorio proveedorRepositorio) {
        this.proveedorRepositorio = proveedorRepositorio;
    }

    // -----------------------------
    //        REGISTRAR
    // -----------------------------
    @Override
    public ProveedorResponseDTO registrarProveedor(ProveedorRequestDTO dto) {

        Proveedor proveedor = new Proveedor();
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setRuc(dto.getRuc());
        proveedor.setNombreComercial(dto.getNombreComercial());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setTelefonoAlternativo(dto.getTelefonoAlternativo());
        proveedor.setEmail(dto.getEmail());
        proveedor.setEmailAlternativo(dto.getEmailAlternativo());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setCiudad(dto.getCiudad());
        proveedor.setPais(dto.getPais());
        proveedor.setPersonaContacto(dto.getPersonaContacto());
        proveedor.setCargoContacto(dto.getCargoContacto());
        proveedor.setCondicionPago(Proveedor.CondicionPago.valueOf(dto.getCondicionPago()));
        proveedor.setCalificacion(Proveedor.Calificacion.valueOf(dto.getCalificacion()));
        proveedor.setLimiteCredito(dto.getLimiteCredito());
        proveedor.setActivo(dto.getActivo());
        proveedor.setNotas(dto.getNotas());

        proveedorRepositorio.save(proveedor);

        return convertirADTO(proveedor);
    }

    // -----------------------------
    //        EDITAR
    // -----------------------------
    @Override
    public ProveedorRequestDTO obtenerProveedorParaEditar(String ruc) {

		Proveedor proveedor = proveedorRepositorio.findByRuc(ruc)
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado con RUC: " + ruc));

		ProveedorRequestDTO dto = new ProveedorRequestDTO();
		dto.setId(proveedor.getId());
		dto.setRazonSocial(proveedor.getRazonSocial());
		dto.setRuc(proveedor.getRuc());
		dto.setTelefono(proveedor.getTelefono());
		dto.setEmail(proveedor.getEmail());
		dto.setDireccion(proveedor.getDireccion());
		dto.setPersonaContacto(proveedor.getPersonaContacto());
		dto.setCondicionPago(proveedor.getCondicionPago().name());
		dto.setNombreComercial(proveedor.getNombreComercial());
		dto.setTelefonoAlternativo(proveedor.getTelefonoAlternativo());
		dto.setEmailAlternativo(proveedor.getEmailAlternativo());
		dto.setCiudad(proveedor.getCiudad());
		dto.setPais(proveedor.getPais());
		dto.setCargoContacto(proveedor.getCargoContacto());
		dto.setCalificacion(proveedor.getCalificacion().name());
		dto.setLimiteCredito(proveedor.getLimiteCredito());
		dto.setActivo(proveedor.getActivo());
		dto.setNotas(proveedor.getNotas());
		

		return dto;
	}
    
    
    @Override
    @Transactional
    public ProveedorResponseDTO editarProveedor(Long Id, ProveedorRequestDTO dto) {

        Proveedor proveedor = proveedorRepositorio.findById(Id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + Id));
        System.out.println(proveedor.getId());
        
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setRuc(dto.getRuc());
        proveedor.setNombreComercial(dto.getNombreComercial());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setTelefonoAlternativo(dto.getTelefonoAlternativo());
        proveedor.setEmail(dto.getEmail());
        proveedor.setEmailAlternativo(dto.getEmailAlternativo());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setCiudad(dto.getCiudad());
        proveedor.setPais(dto.getPais());
        proveedor.setPersonaContacto(dto.getPersonaContacto());
        proveedor.setCargoContacto(dto.getCargoContacto());
        proveedor.setCondicionPago(Proveedor.CondicionPago.valueOf(dto.getCondicionPago()));
        proveedor.setCalificacion(Proveedor.Calificacion.valueOf(dto.getCalificacion()));
        proveedor.setLimiteCredito(dto.getLimiteCredito());
        proveedor.setActivo(dto.getActivo());
        proveedor.setNotas(dto.getNotas());


        proveedorRepositorio.save(proveedor);

        return convertirADTO(proveedor);
    }

    // -----------------------------
    //        ELIMINAR
    // -----------------------------
    @Override
    public void eliminarProveedor(String ruc) {

        Proveedor proveedor = proveedorRepositorio.findByRuc(ruc)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + ruc));

        proveedorRepositorio.delete(proveedor);
    }

    // -----------------------------
    //        LISTAR
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public List<ProveedorResponseDTO> listarProveedores() {
        return proveedorRepositorio.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // -----------------------------
    //        BUSCAR POR ID
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public ProveedorResponseDTO findById(Long id) {
        Proveedor proveedor = proveedorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));

        return convertirADTO(proveedor);
    }

    // -----------------------------
    //        CONVERSOR
    // -----------------------------
    private ProveedorResponseDTO convertirADTO(Proveedor p) {
        return new ProveedorResponseDTO(
                p.getId(),
                p.getRuc(),
                p.getRazonSocial(),
                p.getNombreComercial(),
                p.getDireccion(),
        		p.getCiudad(),
        		p.getPais(),		
        		p.getTelefono(),
                p.getEmail(),
                p.getPersonaContacto(),
                p.getCargoContacto(),
                p.getCondicionPago().name(),
        		p.getCalificacion().name(),
        		p.getLimiteCredito(),
        		p.getActivo());
    }


	@Override
	public List<ProveedorResponseDTO> listarProveedoresActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarProveedores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarProveedoresActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarProveedoresInactivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProveedorResponseDTO> buscarPorRazonSocial(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProveedorResponseDTO> buscarPorCiudad(String ciudad) {
		// TODO Auto-generated method stub
		return null;
	}
}