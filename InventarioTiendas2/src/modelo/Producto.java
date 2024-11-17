package modelo;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int umbralMinimo;

    public Producto(int idProducto, String nombre, String descripcion, int cantidad, int umbralMinimo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.umbralMinimo = umbralMinimo;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public int getUmbralMinimo() { return umbralMinimo; }
    public void setUmbralMinimo(int umbralMinimo) { this.umbralMinimo = umbralMinimo; }
}