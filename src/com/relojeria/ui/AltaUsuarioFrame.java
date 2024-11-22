package com.relojeria.ui;

import com.relojeria.dao.UsuarioDAO;
import com.relojeria.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AltaUsuarioFrame extends JFrame {
    private JTextField txtNombre, txtContrasena;
    private JComboBox<String> cmbRol;
    private JButton btnAgregar;

    public AltaUsuarioFrame() {
        setTitle("Agregar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Crear los campos del formulario
        add(new JLabel("Nombre de Usuario:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField();
        add(txtContrasena);

        add(new JLabel("Rol:"));
        cmbRol = new JComboBox<>(new String[]{"ADMIN", "USER"});
        add(cmbRol);

        btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.addActionListener(e -> agregarUsuario());
        add(btnAgregar);
    }

    private void agregarUsuario() {
        String nombre = txtNombre.getText();
        String contrasena = txtContrasena.getText();
        String rol = cmbRol.getSelectedItem().toString();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try {
            Usuario usuario = new Usuario(nombre, contrasena, rol);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.agregarUsuario(usuario);

            JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");
            dispose(); // Cerrar la ventana después de agregar el usuario
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + ex.getMessage());
        }
    }
}
