package com.relojeria.ui;

import com.relojeria.dao.UsuarioDAO;
import com.relojeria.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GestionUsuariosFrame extends JFrame {
    private JTable tablaUsuarios;
    private JButton btnAgregarUsuario, btnEditarUsuario, btnEliminarUsuario;
    private UsuarioDAO usuarioDAO;

    public GestionUsuariosFrame() {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar el DAO
        usuarioDAO = new UsuarioDAO();

        // Crear los botones
        JPanel panelBotones = new JPanel();
        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnEditarUsuario = new JButton("Editar Usuario");
        btnEliminarUsuario = new JButton("Eliminar Usuario");
        
        panelBotones.add(btnAgregarUsuario);
        panelBotones.add(btnEditarUsuario);
        panelBotones.add(btnEliminarUsuario);

        add(panelBotones, BorderLayout.SOUTH);

        // Crear la tabla de usuarios
        tablaUsuarios = new JTable();
        cargarTablaUsuarios();
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar eventos a los botones
        btnAgregarUsuario.addActionListener(e -> agregarUsuario());
        btnEditarUsuario.addActionListener(e -> editarUsuario());
        btnEliminarUsuario.addActionListener(e -> eliminarUsuario());
    }

    private void cargarTablaUsuarios() {
        try {
            // Obtener la lista de usuarios desde la base de datos
            List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
            // Crear una tabla con los datos
            String[][] data = new String[usuarios.size()][4];
            String[] columnNames = {"ID", "Usuario", "Rol", "Estado"};
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario usuario = usuarios.get(i);
                data[i][0] = String.valueOf(usuario.getId());
                data[i][1] = usuario.getNombre();
                data[i][2] = usuario.getRol();
                data[i][3] = usuario.isActivo() ? "Activo" : "Inactivo";
            }
            tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + ex.getMessage());
        }
    }

    private void agregarUsuario() {
        // Crear un nuevo frame para agregar usuario
        AltaUsuarioFrame altaUsuarioFrame = new AltaUsuarioFrame();
        altaUsuarioFrame.setVisible(true);
    }

    private void editarUsuario() {
        int row = tablaUsuarios.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tablaUsuarios.getValueAt(row, 0).toString());
            String nombre = tablaUsuarios.getValueAt(row, 1).toString();
            String rol = tablaUsuarios.getValueAt(row, 2).toString();

            // Crear un nuevo frame para editar usuario
            EditarUsuarioFrame editarUsuarioFrame = new EditarUsuarioFrame(id, nombre, rol);
            editarUsuarioFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
        }
    }

    private void eliminarUsuario() {
        int row = tablaUsuarios.getSelectedRow();
        if (row != -1) {
            int id = Integer.parseInt(tablaUsuarios.getValueAt(row, 0).toString());
            int confirmation = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este usuario?");
            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    usuarioDAO.eliminarUsuario(id);
                    JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito.");
                    cargarTablaUsuarios(); // Recargar la tabla
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
        }
    }
}
