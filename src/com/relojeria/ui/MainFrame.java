package com.relojeria.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuUsuarios, menuProductos, menuReportes;
    private JMenuItem menuItemGestionUsuarios, menuItemGestionProductos, menuItemReporteVentas;

    public MainFrame() {
        setTitle("Sistema de Gestión de Relojería");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear la barra de menú
        menuBar = new JMenuBar();

        // Menú de Usuarios
        menuUsuarios = new JMenu("Usuarios");
        menuItemGestionUsuarios = new JMenuItem("Gestión de Usuarios");
        menuItemGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionUsuariosFrame().setVisible(true);
            }
        });
        menuUsuarios.add(menuItemGestionUsuarios);

        // Menú de Productos
        menuProductos = new JMenu("Productos");
        menuItemGestionProductos = new JMenuItem("Gestión de Productos");
        menuItemGestionProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionProductosFrame().setVisible(true);
            }
        });
        menuProductos.add(menuItemGestionProductos);

        // Menú de Reportes
        menuReportes = new JMenu("Reportes");
        menuItemReporteVentas = new JMenuItem("Reporte de Ventas");
        menuItemReporteVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReporteVentasFrame().setVisible(true);
            }
        });
        menuReportes.add(menuItemReporteVentas);

        // Agregar los menús a la barra de menú
        menuBar.add(menuUsuarios);
        menuBar.add(menuProductos);
        menuBar.add(menuReportes);

        // Agregar la barra de menú al frame
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
