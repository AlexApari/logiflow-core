package com.logiflow.logiflow_core.servicioTest;

import com.logiflow.logiflow_core.dto.request.DetallePedidoDTO;
import com.logiflow.logiflow_core.dto.request.PedidoRequestDTO;
import com.logiflow.logiflow_core.entidad.*;
import com.logiflow.logiflow_core.repositorio.*;
import com.logiflow.logiflow_core.servicio.PedidoServicioImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PedidoServicioTest {

    @Mock
    private PedidoRepositorio pedidoRepositorio;
    @Mock
    private DetallePedidoRepositorio detallePedidoRepositorio;
    @Mock
    private ClienteRepositorio clienteRepositorio;
    @Mock
    private ProductoRepositorio productoRepositorio;
    @org.mockito.InjectMocks
    private PedidoServicioImp pedidoServicio;

    @Test
    void registrar_debeCrearPedidoConDetallesCorrectos() {
        // GIVEN -----------------------------------------

        // Cliente mock
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombres("Alex");
        cliente.setApellidos("Apari");

        when(clienteRepositorio.findById(1L)).thenReturn(Optional.of(cliente));

        // Producto mock
        Producto prod = new Producto();
        prod.setId(10L);
        prod.setCodigo("P001");
        prod.setNombre("Laptop Gamer");
        prod.setPrecio(new BigDecimal("3000"));
        prod.setStockActual(50);

        when(productoRepositorio.findByCodigo("P001"))
                .thenReturn(Optional.of(prod));

        // DTO detalle
        DetallePedidoDTO d1 = new DetallePedidoDTO();
        d1.setProductoId(1L);
        d1.setCantidad(2);
        d1.setDescuentoPorcentaje(new BigDecimal("10"));
        d1.setNotas("Entrega urgente");

        // Pedido DTO
        PedidoRequestDTO dto = new PedidoRequestDTO();
        dto.setClienteId(1L);
        dto.setDireccionEntrega("Lima");
        dto.setCiudadEntrega("Lima");
        dto.setPrioridad("ALTA");
        dto.setMetodoPago("TARJETA");
        dto.setDetalles(List.of(d1));

        // WHEN -------------------------------------------
        var respuesta = pedidoServicio.registrar(dto);

        // THEN --------------------------------------------

        // Capturar pedido guardado
        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoRepositorio).save(pedidoCaptor.capture());
        Pedido pedidoGuardado = pedidoCaptor.getValue();

        assertNotNull(pedidoGuardado);
        assertEquals(cliente, pedidoGuardado.getCliente());
        assertEquals("Lima", pedidoGuardado.getDireccionEntrega());
        assertEquals("ALTA", pedidoGuardado.getPrioridad().name());
        assertEquals(LocalDate.now(), pedidoGuardado.getFechaPedido());
        assertEquals(Pedido.EstadoPedido.PENDIENTE, pedidoGuardado.getEstado());

        // Validar detalle pedido
        ArgumentCaptor<List<DetallePedido>> detalleCaptor = ArgumentCaptor.forClass(List.class);
        verify(detallePedidoRepositorio).saveAll(detalleCaptor.capture());
        List<DetallePedido> detallesGuardados = detalleCaptor.getValue();

        assertEquals(1, detallesGuardados.size());
        DetallePedido det = detallesGuardados.get(0);

        assertEquals(prod, det.getProducto());
        assertEquals(2, det.getCantidad());
        assertEquals(new BigDecimal("3000"), det.getPrecioUnitario());

        assertEquals(0, det.getSubtotal()
                .add(det.getDescuentoMonto())
                .compareTo(new BigDecimal("6000")));

        // Descuento: 10% = 600
        assertEquals(0, det.getDescuentoMonto().compareTo(new BigDecimal("600")));

        // Validar reducción de stock
        assertEquals(48, prod.getStockActual());

        // Validar DTO respuesta
        assertEquals("Alex Apari", respuesta.getClienteNombre());
        assertEquals(1, respuesta.getDetalles().size());
    }
}
