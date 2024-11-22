package com.relojeria.dao;

import com.relojeria.modelo.Producto;
import com.relojeria.utils.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                productos.add(producto);
            }
        }
        return productos;
    }

    public void agregarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.executeUpdate();
        }
    }

    public void actualizarProducto(Producto producto) throws SQLException {
        String query = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE idProducto = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setInt(4, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        String query = "DELETE FROM productos WHERE idProducto = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
        }
    }
}
