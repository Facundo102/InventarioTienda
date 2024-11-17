package modelo;

import java.util.Date;

public class OrdenDeCompra {
    private int idOrden;
    private Date fecha;
    private int cantidadSolicitada;
    private String estadoOrden;
    private Producto producto;

    public OrdenDeCompra(int idOrden, Date fecha, int cantidadSolicitada, String estadoOrden, Producto producto) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.cantidadSolicitada = cantidadSolicitada;
        this.estadoOrden = estadoOrden;
        this.producto = producto;
    }

    // Getters y Setters
    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public int getCantidadSolicitada() { return cantidadSolicitada; }
    public void setCantidadSolicitada(int cantidadSolicitada) { this.cantidadSolicitada = cantidadSolicitada; }
    public String getEstadoOrden() { return estadoOrden; }
    public void setEstadoOrden(String estadoOrden) { this.estadoOrden = estadoOrden; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}