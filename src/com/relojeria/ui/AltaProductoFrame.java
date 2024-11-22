package com.relojeria.ui;

import com.relojeria.dao.ProductoDAO;
import com.relojeria.modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AltaProductoFrame extends JFrame {
    // Declaración de los componentes de la interfaz gráfica
    private JTextField txtNombre, txtPrecio, txtStock;
    private JButton btnAgregar;

    // Constructor de la clase AltaProductoFrame
    public AltaProductoFrame() {
        // Configuración de la ventana
        setTitle("Alta de Producto");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2)); // Uso de GridLayout para organizar los componentes

        // Etiqueta y campo de texto para el nombre del producto
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        // Etiqueta y campo de texto para el precio del producto
        add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        // Etiqueta y campo de texto para el stock del producto
        add(new JLabel("Stock:"));
        txtStock = new JTextField();
        add(txtStock);

        // Botón para agregar el producto
        btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(e -> agregarProducto());
        add(btnAgregar);
    }

    // Método para agregar el producto a la base de datos
    private void agregarProducto() {
        try {
            // Crear una nueva instancia de Producto y establecer sus atributos
            Producto producto = new Producto();
            producto.setNombre(txtNombre.getText()); // Obtener el nombre del producto desde el campo de texto
            producto.setPrecio(Double.parseDouble(txtPrecio.getText())); // Obtener el precio y convertirlo a double
            producto.setStock(Integer.parseInt(txtStock.getText())); // Obtener el stock y convertirlo a int

            // Crear una instancia del DAO para agregar el producto a la base de datos
            ProductoDAO dao = new ProductoDAO();
            dao.agregarProducto(producto);

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
            dispose(); // Cerrar la ventana de alta de producto

        } catch (NumberFormatException ex) {
            // Capturar excepción si los valores de precio o stock no son números válidos
            JOptionPane.showMessageDialog(this, "Error: Ingresa valores válidos.");
        } catch (SQLException ex) {
            // Capturar excepción si ocurre un error al interactuar con la base de datos
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + ex.getMessage());
        } catch (Exception ex) {
            // Capturar cualquier otro tipo de excepción
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
        }
    }
}
