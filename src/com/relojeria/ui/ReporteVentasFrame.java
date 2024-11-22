package com.relojeria.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class ReporteVentasFrame extends JFrame {
    public ReporteVentasFrame() {
        // Crear un dataset con los datos (puedes reemplazar esto con datos reales)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "Ventas", "Enero");
        dataset.addValue(200, "Ventas", "Febrero");

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Reporte de Ventas",  // Título del gráfico
                "Mes",                // Eje X
                "Monto",              // Eje Y
                dataset              // Datos
        );

        // Crear un panel para mostrar el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // Agregar el panel del gráfico a la ventana
        add(chartPanel);

        // Configuraciones básicas de la ventana
        setTitle("Reporte de Ventas");
        setSize(800, 600); // Ajusta el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejecutar la ventana del reporte
        new ReporteVentasFrame();
    }
}
