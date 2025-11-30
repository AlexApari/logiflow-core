package com.logiflow.logiflow_core.servicio;

@Service
  public class  PedidoServicioImp implements PedidoServicio {
    @Autowired
    private PedidoRepositorio pedidoRepostorio;
    @Autowired
    private DetallePedidoRepositorio detallePedidoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ProductoRepositorio productoRepositorio;
    public PedidoResumenDTO registrar(PedidoRequestDTO dto){
    Cliente cliente = clienteRepositorio.findById(dto.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    Pedido p = new Pedido();
    p.setCliente(Cliente);
      p.setDireccionEntrega(dto.getDireccionEntrega());
      p.setCiudadEntrega(dto.getCiudadEntrega());
      p.setPrioridad(dto.getPrioridad());
      p.setMetodoPago(dto.getMetodoPago());
      p.setNumeroPedido("PED-"+System.currentTimeMillis());
      p.setFechaPedido(LocalDate.now());
      p.setEstado(Pedido.EstadoPedido.PENDIENTE);

      List<DetallePedido> detalles = new ArrayList<>();
      for (DetallePedidoDTO d:dto.getDetalles()){
        Producto producto = productoRepositorio.findById(d.getProductoId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        if(producto.getStock()<d.getCantidad()) throw new RuntimeException("Stock insuficiente para:"+producto.getNombre());
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(d.getCantidad());
        detalle.setPrecioUnitario(p.getPrecio());

        BigDecimal subtotal = p.getPrecio().multiply(new BigDecimal(d.getCantidad()));
        BigDecimal descuentoMonto = subtotal.multiply(d.getDescuentoPorcentaje() != null ? d.getDescuentoPorcentaje().fivide(bigDecimal.valueOf(100)):BigDecimal.ZERO);
        detalle.setDescuentoPorcentaje(d.getDescuentoPorcentaje() != null ? d.getDEscuentoPorcentaje():BigDecimal.ZERO);
        detalle.setDescuentoMonto(descuentoMonto);
        detalle.setSubtotal(subtotal.subtract(descuentoMonto));
        detalle.setNotas(d.getNotas();

        producto.setStock(producto,getStock()-d.getCantidad());
        detalles.add(detalles);
      }
      BigDecimal subtotalTotal = detalles.stream().map(DetallePedido::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);
      p.setSubtotal(subtotalTotal);
      p.setImpuestos(subtotalTotal.multiply(BigDecimal.valueOf(0.18)));
      p.setTotal(subtotalTotal.add(pedido.getImpuestos()));

      pedidoRepositorio.save(p);
      detallePedidoRepositorio.saveAll(detalles);
      return mapToResponseDTO(p,detalles);
    }
    @Override
    public List<PedidoResumenDTO> listarPedidos(){
      return pedidoRepositorio.findAll.stream().map(p->mapToResponseDTO(p, p.getDetalles())).toList();
    }
    @Override
    public PedidoResumenDTO obtenerPedidoPorId(Long Id){
      Pedido pedido = pedidoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
      return mapToResponseDTO(pedido, pedido.getDetalles());
    }
     private PedidoResumenDTO mapToResponseDTO(Pedido pedido, List<DetallePedido> detalles){
       PedidoResumenDTO dto = new PedidoResumenDTO();
       dto.setId(pedido.getId());
       dto.setNumeroPedido(pedido.getNumeroPedido());
       dto.setClienteNombre(pedido.getCliente().getNombre()+""+pedido.getCliente().getApellidos());
       dto.setDirecionEntrega(pedido.getDireccionEntrega());
       dto.setFechaPedido(pedido.getFechaPedido());
       dto.setPrioridad(pedido.getPrioridad());
         dto.setMetodoPago(pedido.getMetodoPago());
         dto.setSubtotal(pedido.getSubtotal());  
         dto.setImpuestos(pedido.getImuestos());
         dto.setTotal(pedido.getTotal());

       List<DetallePedidoResponseDTO> detallesDTO = detalles.stream()map(d ->{
         DetallePedidoResponseDTO detalleDTO = new DetallePedidoResponseDTO();
         detalleDTO.setProductoNombre(d.getProducto().getNombre());
         detalleDTO.setCantidad(d.getCantidad());
         detalleDTO.setPrecioUnitario(d.getPrecioUnitario();
         detalleDTO.setSubtotal(d.getSubtotal();
         return detalleDTO;
       }).toList();

       dto.setDetalles(detallesDTO);
       return dto;
     }
  }
