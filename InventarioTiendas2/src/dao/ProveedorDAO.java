package dao;

import conexion.ConexionBD; 
import modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO implements BaseDAO<Proveedor> {
    @Override
    public void agregar(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor (IDProveedor, Nombre, Contacto) VALUES (?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, proveedor.getIdProveedor());
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getContacto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Proveedor obtener(int id) {
        String sql = "SELECT * FROM Proveedor WHERE IDProveedor = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Proveedor(
                    rs.getInt("IDProveedor"),
                    rs.getString("Nombre"),
                    rs.getString("Contacto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                proveedores.add(new Proveedor(
                    rs.getInt("IDProveedor"),
                    rs.getString("Nombre"),
                    rs.getString("Contacto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    @Override
    public void actualizar(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET Nombre = ?, Contacto = ? WHERE IDProveedor = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getContacto());
            ps.setInt(3, proveedor.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Proveedor WHERE IDProveedor = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
