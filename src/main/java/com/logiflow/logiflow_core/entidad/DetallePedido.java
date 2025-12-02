package com.logiflow.logiflow_core.entidad;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "detalle_pedidos",
    indexes = {
        @Index(name = "idx_pedido", columnList = "pedido_id"),
        @Index(name = "idx_producto", columnList = "producto_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK PEDIDO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "pedido_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_detalle_pedido")
    )
    private Pedido pedido;

    // FK PRODUCTO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "producto_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_detalle_producto")
    )
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "descuento_porcentaje", precision = 5, scale = 2)
    private BigDecimal descuentoPorcentaje;

    @Column(name = "descuento_monto", precision = 10, scale = 2)
    private BigDecimal descuentoMonto;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(length = 500)
    private String notas;


    // Valores por defecto
    @PrePersist
    public void prePersist() {
        if (descuentoPorcentaje == null) descuentoPorcentaje = BigDecimal.ZERO;
        if (descuentoMonto == null) descuentoMonto = BigDecimal.ZERO;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public BigDecimal getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}


	public void setDescuentoPorcentaje(BigDecimal descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}


	public BigDecimal getDescuentoMonto() {
		return descuentoMonto;
	}


	public void setDescuentoMonto(BigDecimal descuentoMonto) {
		this.descuentoMonto = descuentoMonto;
	}


	public BigDecimal getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}


	public String getNotas() {
		return notas;
	}


	public void setNotas(String notas) {
		this.notas = notas;
	}
    
}
