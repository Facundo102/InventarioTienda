package dao;

import conexion.ConexionBD;
import modelo.OrdenDeCompra;
import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenDeCompraDAO implements BaseDAO<OrdenDeCompra> {
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    public void agregar(OrdenDeCompra orden) {
        String sql = "INSERT INTO OrdenDeCompra (IDOrden, Fecha, CantidadSolicitada, EstadoOrden, IDProducto) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orden.getIdOrden());
            ps.setDate(2, new java.sql.Date(orden.getFecha().getTime()));
            ps.setInt(3, orden.getCantidadSolicitada());
            ps.setString(4, orden.getEstadoOrden());
            ps.setInt(5, orden.getProducto().getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrdenDeCompra obtener(int id) {
        String sql = "SELECT * FROM OrdenDeCompra WHERE IDOrden = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto producto = productoDAO.obtener(rs.getInt("IDProducto"));
                return new OrdenDeCompra(
                    rs.getInt("IDOrden"),
                    rs.getDate("Fecha"),
                    rs.getInt("CantidadSolicitada"),
                    rs.getString("EstadoOrden"),
                    producto
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrdenDeCompra> listar() {
        List<OrdenDeCompra> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM OrdenDeCompra";
        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = productoDAO.obtener(rs.getInt("IDProducto"));
                ordenes.add(new OrdenDeCompra(
                    rs.getInt("IDOrden"),
                    rs.getDate("Fecha"),
                    rs.getInt("CantidadSolicitada"),
                    rs.getString("EstadoOrden"),
                    producto
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordenes;
    }

    @Override
    public void actualizar(OrdenDeCompra orden) {
        String sql = "UPDATE OrdenDeCompra SET Fecha = ?, CantidadSolicitada = ?, EstadoOrden = ?, IDProducto = ? WHERE IDOrden = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(orden.getFecha().getTime()));
            ps.setInt(2, orden.getCantidadSolicitada());
            ps.setString(3, orden.getEstadoOrden());
            ps.setInt(4, orden.getProducto().getIdProducto());
            ps.setInt(5, orden.getIdOrden());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM OrdenDeCompra WHERE IDOrden = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}