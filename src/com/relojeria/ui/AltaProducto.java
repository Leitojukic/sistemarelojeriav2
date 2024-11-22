package com.relojeria.ui;

import com.relojeria.dao.ProductoDAO;
import com.relojeria.model.Producto;

import javax.swing.*;

public class AltaProductoFrame extends JFrame {
    public AltaProductoFrame() {
        setTitle("Alta de Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Componentes del formulario
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(140, 20, 200, 25);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 60, 100, 25);
        JTextField txtDescripcion = new JTextField();
        txtDescripcion.setBounds(140, 60, 200, 25);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 100, 100, 25);
        JTextField txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 100, 200, 25);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(20, 140, 100, 25);
        JTextField txtStock = new JTextField();
        txtStock.setBounds(140, 140, 200, 25);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(140, 200, 100, 30);

        // Evento para guardar el producto
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());

            Producto producto = new Producto(nombre, descripcion, precio, stock);
            ProductoDAO dao = new ProductoDAO();
            if (dao.guardarProducto(producto)) {
                JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el producto.");
            }
        });

        add(lblNombre);
        add(txtNombre);
        add(lblDescripcion);
        add(txtDescripcion);
        add(lblPrecio);
        add(txtPrecio);
        add(lblStock);
        add(txtStock);
        add(btnGuardar);
    }
}
