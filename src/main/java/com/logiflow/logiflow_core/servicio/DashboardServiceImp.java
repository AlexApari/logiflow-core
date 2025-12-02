package com.logiflow.logiflow_core.servicio;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logiflow.logiflow_core.dto.response.*;
import com.logiflow.logiflow_core.entidad.Envios.EstadoEnvio;
import com.logiflow.logiflow_core.entidad.Pedido.EstadoPedido;
import com.logiflow.logiflow_core.entidad.Producto;
import com.logiflow.logiflow_core.repositorio.EnviosRepositorio;
import com.logiflow.logiflow_core.repositorio.PedidoRepositorio;
import com.logiflow.logiflow_core.repositorio.ProductoRepositorio;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImp implements DashboardService {

    private final ProductoRepositorio productoRepositorio;
    private final PedidoRepositorio pedidoRepositorio;
    private final EnviosRepositorio enviosRepositorio;

    // Explicit constructor (avoid Lombok @RequiredArgsConstructor so IDE compiles without Lombok)
    public DashboardServiceImp(ProductoRepositorio productoRepositorio,
                               PedidoRepositorio pedidoRepositorio,
                               EnviosRepositorio enviosRepositorio) {
        this.productoRepositorio = productoRepositorio;
        this.pedidoRepositorio = pedidoRepositorio;
        this.enviosRepositorio = enviosRepositorio;
    }

    @Override
    public DashboardResponseDTO obtenerResumenDashboard() {
        DashboardResponseDTO dto = new DashboardResponseDTO();

        // Products
        Long totalProductos = obtenerTotalProductos();
        dto.setTotalProductos(totalProductos);

        long bajoStock = productoRepositorio.findAll()
        	    .stream()
        	    .filter(p -> p.getStockActual() != null && p.getStockActual() <= 30)
        	    .count();
        	dto.setProductosBajoStock(bajoStock);


        dto.setTopProductos(obtenerTopProductosVendidos(5));
        dto.setRecientesProductos(obtenerProductosRecientes(5));

        // Pedidos
        dto.setTotalPedidos(obtenerTotalPedidos());
        dto.setTotalVentas(obtenerTotalVentas());

        List<EstadoCountDTO> pedidosEstado = java.util.Arrays.stream(EstadoPedido.values())
                .map(e -> new EstadoCountDTO(e.name(), pedidoRepositorio.countByEstado(e)))
                .collect(Collectors.toList());
        dto.setPedidosPorEstado(pedidosEstado);
        dto.setRecientesPedidos(obtenerPedidosRecientes(5));

        // Envios
        dto.setTotalEnvios(obtenerTotalEnvios());
        dto.setTotalCostoEnvios(obtenerTotalCostoEnvios());

        List<EstadoCountDTO> enviosEstado = java.util.Arrays.stream(EstadoEnvio.values())
                .map(e -> new EstadoCountDTO(e.name(), enviosRepositorio.countByEstado(e)))
                .collect(Collectors.toList());
        dto.setEnviosPorEstado(enviosEstado);
        dto.setRecientesEnvios(obtenerEnviosRecientes(5));

        return dto;
    }

    @Override
    public List<EstadoCountDTO> obtenerConteoPorEstado() {
        return java.util.Arrays.stream(EstadoPedido.values())
                .map(e -> new EstadoCountDTO(e.name(), pedidoRepositorio.countByEstado(e)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TopProductoDTO> obtenerTopProductosVendidos(int limite) {
        List<Producto> masVendidos = productoRepositorio.findProductosMasVendidos();
        return masVendidos.stream()
                .limit(limite)
                .map(p -> new TopProductoDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getCodigo(),
                        0,
                        BigDecimal.ZERO
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoResumenDTO> obtenerProductosRecientes(int limite) {
        return productoRepositorio.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getFechaCreacion, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .limit(limite)
                .map(p -> new ProductoResumenDTO(
                        p.getId(),
                        p.getNombre(),
                        p.getCodigo(),
                        null, p.getStockActual(),
                        p.getPrecio(),
                        p.getActivo()
                        
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResumenDTO> obtenerPedidosRecientes(int limite) {
        return pedidoRepositorio.findTop10ByOrderByFechaPedidoDesc()
                .stream()
                .limit(limite)
                .map(p -> new PedidoResumenDTO(
                        p.getId(),
                        p.getNumeroPedido(),
                        p.getCliente() != null ? 
                        	    p.getCliente().getNombres() + " " + p.getCliente().getApellidos() :
                        	    "Sin cliente",
                        p.getFechaPedido(),
                        p.getEstado() != null ? p.getEstado().name() : null,
                        p.getTotal()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnvioResumenDTO> obtenerEnviosRecientes(int limite) {
        return enviosRepositorio.findTop10ByOrderByFechaEnvioDesc()
                .stream()
                .limit(limite)
                .map(e -> new EnvioResumenDTO(
                        e.getId(),
                        e.getNumeroEnvio(),
                        e.getNumeroGuia(),
                        e.getPedido() != null ? e.getPedido().getId() : null,
                        e.getPedido() != null ? e.getPedido().getNumeroPedido() : null,
                        e.getFechaEnvio(),
                        e.getEstado() != null ? e.getEstado().name() : null,
                        e.getCostoEnvio()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Long obtenerTotalProductos() {
        return productoRepositorio.count();
    }

    @Override
    public Long obtenerTotalPedidos() {
        return pedidoRepositorio.contarPedidos();
    }

    @Override
    public BigDecimal obtenerTotalVentas() {
        return pedidoRepositorio.totalVentas();
    }

    @Override
    public Long obtenerTotalEnvios() {
        return enviosRepositorio.contarEnvios();
    }

    @Override
    public BigDecimal obtenerTotalCostoEnvios() {
        return enviosRepositorio.totalCostoEnvios();
    }
}