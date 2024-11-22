package com.relojeria.ui;

import com.relojeria.dao.UsuarioDAO;
import com.relojeria.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditarUsuarioFrame extends JFrame {
    private JTextField txtNombre, txtContrasena;
    private JComboBox<String> cmbRol;
    private JButton btnGuardar;
    private int usuarioId;

    public EditarUsuarioFrame(int id, String nombre, String rol) {
        usuarioId = id;
        setTitle("Editar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Crear los campos del formulario
        add(new JLabel("Nombre de Usuario:"));
        txtNombre = new JTextField(nombre);
        add(txtNombre);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField();
        add(txtContrasena);

        add(new JLabel("Rol:"));
        cmbRol = new JComboBox<>(new String[]{"ADMIN", "USER"});
        cmbRol.setSelectedItem(rol);
        add(cmbRol);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> editarUsuario());
        add(btnGuardar);
    }

    private void editarUsuario() {
        String nombre = txtNombre.getText();
        String contrasena = txtContrasena.getText();
        String rol = cmbRol.getSelectedItem().toString();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try {
            Usuario usuario = new Usuario(usuarioId, nombre, contrasena, rol);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.editarUsuario(usuario);

            JOptionPane.showMessageDialog(this, "Usuario editado exitosamente.");
            dispose(); // Cerrar la ventana después de editar el usuario
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + ex.getMessage());
        }
    }
}
