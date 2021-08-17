package edu.cibertec.capitulo7.dto;

public class DetalleVenta {

    private Integer id;
    private Integer codigoventa;
    private String ventaCliente;
    private Integer codigoproducto;
    private String productoNombre;
    private Double cantidad;
    private Double descuento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigoventa() {
        return codigoventa;
    }

    public void setCodigoventa(Integer codigoventa) {
        this.codigoventa = codigoventa;
    }

    public String getVentaCliente() {
        return ventaCliente;
    }

    public void setVentaCliente(String ventaCliente) {
        this.ventaCliente = ventaCliente;
    }

    public Integer getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(Integer codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    
    
}
