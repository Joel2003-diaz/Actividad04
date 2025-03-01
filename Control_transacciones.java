package control_transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control_transacciones {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/gestiondatos";
            String usuario = "root"; 
            String contraseña = "123456"; 

            conn = DriverManager.getConnection(url, usuario, contraseña);
            conn.setAutoCommit(false); // Desactivar el modo autocommit para manejar transacciones manualmente

            System.out.println("Conexión exitosa a la base de datos.");

            // Insertar un nuevo producto
            System.out.println("Insertando producto...");
            insertarProducto(conn, "iphone 16 pro max", "1 tb 16 ram sod 108 mp 32 mpx", 15, 7500000.00);
            System.out.println("Producto insertado.");

            // Insertar un nuevo cliente
            System.out.println("Insertando cliente...");
            int clienteId = insertarCliente(conn, "juan marcillo", "Avenida los negros", "juan@email.com", "6666666");
            System.out.println("Cliente insertado con ID: " + clienteId);

            // Insertar una compra realizada por el cliente
            System.out.println("Insertando compra...");
            insertarCompra(conn, clienteId, 1, 1, 7500000.00, new java.sql.Date(System.currentTimeMillis()));
            System.out.println("Compra insertada.");

            // Confirmar la transacción si todo es exitoso
            conn.commit();
            System.out.println("Transacción completada con éxito.");

        } catch (SQLException e) {
            // Revertir la transacción en caso de error
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transacción revertida debido a un error.");
                } catch (SQLException ex) {
                    System.err.println("Error al revertir la transacción: " + ex.getMessage());
                }
            }
            System.err.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    // Método para insertar un producto
    private static void insertarProducto(Connection conn, String nombre, String descripcion, int cantidad, double precio)
            throws SQLException {
        String sql = "INSERT INTO productos (Nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precio);
            stmt.executeUpdate();
            System.out.println("Producto insertado: " + nombre);
        }
    }

    // Método para insertar un cliente y devolver su ID
    private static int insertarCliente(Connection conn, String nombre, String direccion, String correo, String telefono)
            throws SQLException {
        String sql = "INSERT INTO Clientes (Nombre, direccion, correo_electronico, Telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setString(3, correo);
            stmt.setString(4, telefono);
            stmt.executeUpdate();

            // Obtener el ID generado para el cliente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int cliente_Id = generatedKeys.getInt(1);
                    System.out.println("Cliente insertado con ID: " + cliente_Id);
                    return cliente_Id;
                } else {
                    throw new SQLException("No se pudo obtener el ID del cliente.");
                }
            }
        }
    }

    // Método para insertar una compra
    private static void insertarCompra(Connection conn, int cliente_Id, int codigo_producto, int cantidad, double precio_unitario, java.sql.Date fecha) throws SQLException {
        String sql = "INSERT INTO Compras (cliente_id, codigo_producto, cantidad, precio_unitario, fecha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente_Id);
            stmt.setInt(2, codigo_producto);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precio_unitario);
            stmt.setDate(5, fecha);
            stmt.executeUpdate();
            System.out.println("Compra insertada para el cliente ID: " + cliente_Id);
        }
    }
}