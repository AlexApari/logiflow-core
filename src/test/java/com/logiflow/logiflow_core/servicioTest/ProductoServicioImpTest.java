package com.logiflow.logiflow_core.servicioTest;

import com.logiflow.logiflow_core.dto.request.ProductoRequestDTO;
import com.logiflow.logiflow_core.entidad.Producto;
import com.logiflow.logiflow_core.entidad.Categoria;
import com.logiflow.logiflow_core.entidad.Proveedor;
import com.logiflow.logiflow_core.repositorio.ProductoRepositorio;
import com.logiflow.logiflow_core.repositorio.CategoriaRepositorio;
import com.logiflow.logiflow_core.repositorio.ProveedorRepositorio;
import com.logiflow.logiflow_core.servicio.ProductoServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServicioImpTest {

    @Mock
    private ProductoRepositorio productoRepositorio;

    @Mock
    private CategoriaRepositorio categoriaRepositorio;

    @Mock
    private ProveedorRepositorio proveedorRepositorio;

    @InjectMocks
    private ProductoServiceImp productoServicio;

    @Test
    void crearProducto_debeGuardarStockActualCorrectamente() {

        // DTO
        ProductoRequestDTO dto = new ProductoRequestDTO();
        dto.setCodigo("P001");
        dto.setNombre("Laptop Gaming");
        dto.setPrecio(new BigDecimal("3500"));
        dto.setStockActual(25);
        dto.setCategoriaId(1L);
        dto.setProveedorId(1L);

        // Mock Categoria
        Categoria cat = new Categoria();
        cat.setId(1L);
        when(categoriaRepositorio.findById(1L))
                .thenReturn(Optional.of(cat));

        // Mock Proveedor
        Proveedor prov = new Proveedor();
        prov.setId(1L);
        when(proveedorRepositorio.findById(1L))
                .thenReturn(Optional.of(prov));

        // 🔥 Mock necesario: save() debe devolver el producto
        when(productoRepositorio.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecutar servicio
        productoServicio.crearProducto(dto);

        // Capturar lo guardado
        ArgumentCaptor<Producto> captor = ArgumentCaptor.forClass(Producto.class);
        verify(productoRepositorio).save(captor.capture());
        Producto p = captor.getValue();

        // Assertions
        assertEquals("P001", p.getCodigo());
        assertEquals(25, p.getStockActual());
        assertEquals(cat, p.getCategoria());
        assertEquals(prov, p.getProveedor());
    }

}
