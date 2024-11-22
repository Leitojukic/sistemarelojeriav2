package com.relojeria.dao;

import com.relojeria.modelo.Usuario;
import com.relojeria.utils.ConexionBD;

import java.sql.*;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {
        connection = ConexionBD.getConnection(); // Obtener la conexión desde la clase ConexionBD
    }

    // Método para agregar un usuario
    public void agregarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, contrasena, rol) VALUES (?, ?, ?)";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getContrasena());
            pst.setString(3, usuario.getRol());
            pst.executeUpdate();
        }
    }

    // Método para editar un usuario
    public void editarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, contrasena = ?, rol = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getContrasena());
            pst.setString(3, usuario.getRol());
            pst.setInt(4, usuario.getId());
            pst.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    // Método para obtener un usuario por su id
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
            }
        }
        return null;
    }

    // Método para obtener un usuario por su nombre de usuario
    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE nombre = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
            }
        }
        return null;
    }

    // Método para verificar si un usuario existe con su nombre y contraseña
    public boolean verificarUsuario(String nombre, String contrasena) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, nombre);
            pst.setString(2, contrasena);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Si hay un registro, el usuario es válido
        }
    }
}
