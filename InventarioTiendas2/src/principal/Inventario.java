package principal;

import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class Inventario {
    private ProductoDAO productoDAO = new ProductoDAO();

    public List<Producto> obtenerInventario() {
        return productoDAO.listar();
    }

    public boolean registrarEntrada(int idProducto, int cantidad) {
        Producto producto = productoDAO.obtener(idProducto);
        if (producto != null) {
            producto.setCantidad(producto.getCantidad() + cantidad);
            productoDAO.actualizar(producto);
            return true;
        }
        return false;
    }

    public boolean registrarSalida(int idProducto, int cantidad) {
        Producto producto = productoDAO.obtener(idProducto);
        if (producto != null && producto.getCantidad() >= cantidad) {
            producto.setCantidad(producto.getCantidad() - cantidad);
            productoDAO.actualizar(producto);
            return true;
        }
        return false;
    }

    public List<Producto> obtenerProductosBajoStock() {
        List<Producto> productosBajoStock = productoDAO.listar();
        productosBajoStock.removeIf(producto -> producto.getCantidad() >= producto.getUmbralMinimo());
        return productosBajoStock;
    }
}