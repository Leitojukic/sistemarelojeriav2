package com.relojeria.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class ReporteVentas {
    public void mostrarReporte() {
        // Datos para el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5000, "Ventas", "Enero");
        dataset.addValue(7000, "Ventas", "Febrero");
        dataset.addValue(8000, "Ventas", "Marzo");

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Reporte de Ventas",  // Título
                "Mes",               // Eje X
                "Monto",             // Eje Y
                dataset
        );

        // Mostrar en un panel
        JFrame frame = new JFrame("Reporte de Ventas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new ChartPanel(chart));
        frame.setVisible(true);
    }
}
