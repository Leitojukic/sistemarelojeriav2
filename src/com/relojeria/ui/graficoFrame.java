package com.relojeria.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class GraficoFrame extends JFrame {
    public GraficoFrame() {
        setTitle("Gráfico de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "Usuarios", "Admin");
        dataset.addValue(15, "Usuarios", "User");

        JFreeChart chart = ChartFactory.createBarChart(
                "Distribución de Usuarios",
                "Rol",
                "Cantidad",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }
}
