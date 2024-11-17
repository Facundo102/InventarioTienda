package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/InventarioTiendas";
    private static final String USER = "colocar_su_usuario";
    private static final String PASSWORD = "colocar_su_contraseña";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void probarConexion() {
        try (Connection con = conectar()) {
            if (con != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
