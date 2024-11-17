package dao;

import conexion.ConexionBD;
import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements BaseDAO<Producto> {
    @Override
    public void agregar(Producto producto) {
        String sql = "INSERT INTO Producto (IDProducto, Nombre, Descripcion, Cantidad, UmbralMinimo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getCantidad());
            ps.setInt(5, producto.getUmbralMinimo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto obtener(int id) {
        String sql = "SELECT * FROM Producto WHERE IDProducto = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("IDProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getInt("Cantidad"),
                    rs.getInt("UmbralMinimo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("IDProducto"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"),
                    rs.getInt("Cantidad"),
                    rs.getInt("UmbralMinimo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE Producto SET Nombre = ?, Descripcion = ?, Cantidad = ?, UmbralMinimo = ? WHERE IDProducto = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getCantidad());
            ps.setInt(4, producto.getUmbralMinimo());
            ps.setInt(5, producto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Producto WHERE IDProducto = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
