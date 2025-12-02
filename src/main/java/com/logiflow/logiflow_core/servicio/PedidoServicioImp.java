package com.logiflow.logiflow_core.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.logiflow.logiflow_core.dto.response.*;
import com.logiflow.logiflow_core.entidad.*;
import com.logiflow.logiflow_core.repositorio.*;
import org.springframework.stereotype.Service;
import com.logiflow.logiflow_core.dto.request.*;

@Service
  public class  PedidoServicioImp implements PedidoServicio {
    
    private PedidoRepositorio pedidoRepositorio;
    
    private DetallePedidoRepositorio detallePedidoRepositorio;
    
    private ClienteRepositorio clienteRepositorio;
    
    private ProductoRepositorio productoRepositorio;
    public PedidoServicioImp(PedidoRepositorio pedidoRepositorio, DetallePedidoRepositorio detallePedidoRepositorio, ClienteRepositorio clienteRepositorio, ProductoRepositorio productoRepositorio) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.detallePedidoRepositorio = detallePedidoRepositorio;
		this.clienteRepositorio = clienteRepositorio;
		this.productoRepositorio = productoRepositorio;
	}
    public PedidoResumenDTO registrar(PedidoRequestDTO dto){
    Cliente cliente = clienteRepositorio.findById(dto.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    Pedido p = new Pedido();
    p.setCliente(cliente);
    p.setNumeroPedido(dto.getNumeroPedido());
      p.setDireccionEntrega(dto.getDireccionEntrega());
      p.setCiudadEntrega(dto.getCiudadEntrega());
      p.setPrioridad(Pedido.PrioridadPedido.valueOf(dto.getPrioridad()));
      p.setMetodoPago(Pedido.MetodoPago.valueOf(dto.getMetodoPago()));
      p.setNumeroPedido("PED-"+System.currentTimeMillis());
      p.setFechaPedido(LocalDate.now());
      p.setFechaEntregaEstimada(dto.getFechaEntregaEstimada());
      p.setEstado(Pedido.EstadoPedido.PENDIENTE);

      List<DetallePedido> detalles = new ArrayList<>();
      for (DetallePedidoDTO d : dto.getDetalles()){
    	  // 1. Obtener producto
          Producto producto = productoRepositorio.findById(d.getProductoId())
                  .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

          // 2. Validar stock
          if (producto.getStockActual() < d.getCantidad()) {
              throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
          }DetallePedido detalle = new DetallePedido();
        detalle.setPedido(p);
        detalle.setProducto(producto);
        detalle.setCantidad(d.getCantidad());
        detalle.setPrecioUnitario(producto.getPrecio());

        BigDecimal subtotal = producto.getPrecio().multiply(new BigDecimal(d.getCantidad()));
        BigDecimal descuentoMonto = subtotal.multiply(d.getDescuentoPorcentaje() != null ? d.getDescuentoPorcentaje().divide(BigDecimal.valueOf(100)):BigDecimal.ZERO);
        detalle.setDescuentoPorcentaje(d.getDescuentoPorcentaje() != null ? d.getDescuentoPorcentaje():BigDecimal.ZERO);
        detalle.setDescuentoMonto(descuentoMonto);
        detalle.setSubtotal(subtotal.subtract(descuentoMonto));
        detalle.setNotas(d.getNotas());

        producto.setStockActual(producto.getStockActual()-d.getCantidad());
        detalles.add(detalle);
      }
      BigDecimal subtotalTotal = detalles.stream().map(DetallePedido::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
      p.setSubtotal(subtotalTotal);
      p.setImpuestos(subtotalTotal.multiply(BigDecimal.valueOf(0.18)));
      p.setTotal(subtotalTotal.add(p.getImpuestos()));

      pedidoRepositorio.save(p);
      detallePedidoRepositorio.saveAll(detalles);
      return mapToResponseDTO(p,detalles);
    }
    @Override
    public List<PedidoResumenDTO> listar(){
      return pedidoRepositorio.findAll().stream().map(p->mapToResponseDTO(p, p.getDetalles())).toList();
    }
    @Override
    public PedidoResumenDTO obtenerPorId(Long Id){
      Pedido pedido = pedidoRepositorio.findById(Id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
      return mapToResponseDTO(pedido, pedido.getDetalles());
    }
    
     private PedidoResumenDTO mapToResponseDTO(Pedido pedido, List<DetallePedido> detalles){
       PedidoResumenDTO dto = new PedidoResumenDTO();
       dto.setId(pedido.getId()); 
       dto.setNumeroPedido(pedido.getNumeroPedido());
       dto.setClienteNombre(pedido.getCliente().getNombres()+" "+pedido.getCliente().getApellidos());
       dto.setFechaPedido(pedido.getFechaPedido());
    
       dto.setPrioridad(pedido.getPrioridad().name());
         dto.setMetodoPago(pedido.getMetodoPago().name());
         dto.setSubtotal(pedido.getSubtotal());  
         dto.setImpuestos(pedido.getImpuestos());
         dto.setTotal(pedido.getTotal());

       List<DetallePedidoReponseDTO> detallesDTO = detalles.stream().map(d ->{
         DetallePedidoReponseDTO detalleDTO = new DetallePedidoReponseDTO();
         detalleDTO.setNombreProducto(d.getProducto().getNombre());
         detalleDTO.setCantidad(d.getCantidad());
         detalleDTO.setPrecioUnitario(d.getPrecioUnitario());
         detalleDTO.setSubtotal(d.getSubtotal());
         return detalleDTO;
       }).toList();

       dto.setDetalles(detallesDTO);
       return dto;
     }
  }
