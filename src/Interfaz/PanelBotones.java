/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel {
    private JButton botonStart;
    private JButton botonStop;

    // Constructor
    public PanelBotones() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Distribución centrada con separación
        setPreferredSize(new Dimension(150, 50));

        // Crear botones
        botonStart = new JButton("Start");
        botonStop = new JButton("Stop");

        // Agregar botones al panel
        add(botonStart);
        add(botonStop);

        setBorder(BorderFactory.createTitledBorder("Controls"));
    }

    // Métodos para agregar listeners desde el controlador
    public void setStartListener(ActionListener listener) {
        botonStart.addActionListener(listener);
    }

    public void setStopListener(ActionListener listener) {
        botonStop.addActionListener(listener);
    }
}
