package principal;

import conexion.ConexionBD;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import dao.OrdenDeCompraDAO;
import modelo.Producto;
import modelo.OrdenDeCompra;
import modelo.Proveedor;

public class SistemaInventario {
    private static Inventario inventario = new Inventario();
    private static ProductoDAO productoDAO = new ProductoDAO();
    private static ProveedorDAO proveedorDAO = new ProveedorDAO();
    private static OrdenDeCompraDAO ordenDeCompraDAO = new OrdenDeCompraDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ConexionBD.probarConexion(); // Probar conexión al inicio

        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Consultar Inventario");
            System.out.println("2. Registrar Entrada de Producto");
            System.out.println("3. Registrar Salida de Producto");
            System.out.println("4. Generar Reporte de Bajo Stock");
            System.out.println("5. Ver Proveedores");
            System.out.println("6. Crear Orden de Compra");
            System.out.println("7. Ver Órdenes de Compra");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    consultarInventario();
                    break;
                case 2:
                    registrarEntradaProducto();
                    break;
                case 3:
                    registrarSalidaProducto();
                    break;
                case 4:
                    generarReporteBajoStock();
                    break;
                case 5:
                    verProveedores();
                    break;
                case 6:
                    crearOrdenDeCompra();
                    break;
                case 7:
                    verOrdenesDeCompra();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    public static void consultarInventario() {
        System.out.println("\n=== Consultar Inventario ===");
        List<Producto> productos = productoDAO.listar();
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getIdProducto() + ", Nombre: " + producto.getNombre() + 
                               ", Cantidad: " + producto.getCantidad() + ", Umbral: " + producto.getUmbralMinimo());
        }
    }

    public static void registrarEntradaProducto() {
        System.out.print("\nIngrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad a añadir: ");
        int cantidad = scanner.nextInt();
        
        if (inventario.registrarEntrada(idProducto, cantidad)) {
            System.out .println("Entrada registrada correctamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void registrarSalidaProducto() {
        System.out.print("\nIngrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad a retirar: ");
        int cantidad = scanner.nextInt();
        
        if (inventario.registrarSalida(idProducto, cantidad)) {
            System.out.println("Salida registrada correctamente.");
        } else {
            System.out.println("Producto no encontrado o cantidad insuficiente.");
        }
    }

    public static void generarReporteBajoStock() {
        System.out.println("\n=== Reporte de Productos con Bajo Stock ===");
        List<Producto> productos = inventario.obtenerProductosBajoStock();
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getIdProducto() + ", Nombre: " + producto.getNombre() + 
                               ", Cantidad: " + producto.getCantidad());
        }
    }

    public static void verProveedores() {
        System.out.println("\n=== Lista de Proveedores ===");
        List<Proveedor> proveedores = proveedorDAO.listar();
        for (Proveedor proveedor : proveedores) {
            System.out.println("ID: " + proveedor.getIdProveedor() + ", Nombre: " + proveedor.getNombre() + 
                               ", Contacto: " + proveedor.getContacto());
        }
    }

    public static void crearOrdenDeCompra() {
        System.out.print("\nIngrese el ID del producto para la orden de compra: ");
        int idProducto = scanner.nextInt();
        Producto producto = productoDAO.obtener(idProducto);
        
        if (producto != null) {
            System.out.print("Ingrese la cantidad solicitada: ");
            int cantidadSolicitada = scanner.nextInt();
            
            OrdenDeCompra nuevaOrden = new OrdenDeCompra(ordenDeCompraDAO.listar().size() + 1, new Date(), cantidadSolicitada, "Pendiente", producto);
            ordenDeCompraDAO.agregar(nuevaOrden);
            
            System.out.println("Orden de compra creada exitosamente para el producto: " + producto.getNombre());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void verOrdenesDeCompra() {
        System.out.println("\n=== Lista de Órdenes de Compra ===");
        List<OrdenDeCompra> ordenes = ordenDeCompraDAO.listar();
        for (OrdenDeCompra orden : ordenes) {
            System.out.println("ID Orden: " + orden.getIdOrden() + ", Fecha: " + orden.getFecha() + 
                               ", Cantidad Solicitada: " + orden.getCantidadSolicitada() + ", Estado: " + orden.getEstadoOrden() + 
                               ", Producto: " + orden.getProducto().getNombre());
        }
    }
}