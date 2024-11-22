package com.relojeria.service;

import com.relojeria.utils.JWTUtil;
import java.sql.*;

public class AuthService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/relojeria";
    private static final String DB_USER = "pioix2docuatrimestre";
    private static final String DB_PASS = "Mario1234";

    /**
     * Método para autenticar a un usuario.
     *
     * @param usuario    Nombre de usuario ingresado.
     * @param contrasena Contraseña ingresada.
     * @return Un token JWT si las credenciales son correctas, o null si no lo son.
     */
    public String autenticarUsuario(String usuario, String contrasena) {
        try {
            if (validarCredenciales(usuario, contrasena)) {
                String rol = obtenerRolDesdeBD(usuario);
                return JWTUtil.generarToken(usuario, rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Valida las credenciales del usuario contra la base de datos.
     *
     * @param usuario    Nombre de usuario.
     * @param contrasena Contraseña.
     * @return True si las credenciales son válidas, false en caso contrario.
     * @throws SQLException Si hay problemas de conexión con la base de datos.
     */
    private boolean validarCredenciales(String usuario, String contrasena) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Si hay resultados, las credenciales son válidas
        }
    }

    /**
     * Obtiene el rol del usuario desde la base de datos.
     *
     * @param usuario Nombre de usuario.
     * @return Rol del usuario.
     * @throws SQLException Si hay problemas de conexión con la base de datos.
     */
    private String obtenerRolDesdeBD(String usuario) throws SQLException {
        String query = "SELECT rol FROM usuarios WHERE usuario = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        }
        return "USER"; // Valor por defecto si no se encuentra el rol
    }
}
