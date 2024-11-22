package com.relojeria.ui;

import com.relojeria.dao.ProductoDAO;
import com.relojeria.model.Producto;
import javax.swing.*;
import java.util.List;

public class GestionProductosFrame extends JFrame {
    private JTextField nombreField, descripcionField, precioField, stockField;
    private JButton agregarButton, listarButton;
    private JTextArea productosArea;

    public GestionProductosFrame() {
        setTitle("Gestión de Productos");

        // Crear los elementos de la interfaz
        nombreField = new JTextField(20);
        descripcionField = new JTextField(20);
        precioField = new JTextField(20);
        stockField = new JTextField(20);
        agregarButton = new JButton("Agregar Producto");
        listarButton = new JButton("Listar Productos");
        productosArea = new JTextArea(10, 40);
        productosArea.setEditable(false);

        // Agregar acciones a los botones
        agregarButton.addActionListener(e -> {
            AltaProductoFrame altaFrame = new AltaProductoFrame();
            altaFrame.setVisible(true);

            // Actualizar la tabla al cerrar el frame de alta
            altaFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    listarProductos(); // Actualizar la lista de productos al cerrar el frame
                }
            });
        });

        listarButton.addActionListener(e -> listarProductos());

        // Crear la interfaz de usuario
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Nombre"));
        add(nombreField);
        add(new JLabel("Descripción"));
        add(descripcionField);
        add(new JLabel("Precio"));
        add(precioField);
        add(new JLabel("Stock"));
        add(stockField);
        add(agregarButton);
        add(listarButton);
        add(new JScrollPane(productosArea));

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void agregarProducto() {
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        double precio = Double.parseDouble(precioField.getText());
        int stock = Integer.parseInt(stockField.getText());

        Producto producto = new Producto(nombre, descripcion, precio, stock);
        ProductoDAO dao = new ProductoDAO();
        boolean exito = dao.guardarProducto(producto);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Producto agregado con éxito");
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto");
        }
    }

    private void listarProductos() {
        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.obtenerProductos();
        productosArea.setText("");
        for (Producto producto : productos) {
            productosArea.append("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + "\n");
        }
    }

    public static void main(String[] args) {
        new GestionProductosFrame();
    }
}
