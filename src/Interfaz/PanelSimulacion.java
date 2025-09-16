package Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelSimulacion extends JPanel {

    private JLabel labelGeneracion;
    private JLabel labelPoblacion;

    // Constructor
    public PanelSimulacion() {
        setLayout(new GridLayout(2, 1, 5, 5)); // Dos filas con espacio entre ellas
        setPreferredSize(new Dimension(180, 60));

        // Fondo con color suave
        setBackground(new Color(45, 45, 60));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 255), 2),
                "Simulation Info",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE
        ));

        // Inicializar labels con estilo
        labelGeneracion = crearLabel("Generation: 0");
        labelPoblacion = crearLabel("Population: 0");

        add(labelGeneracion);
        add(labelPoblacion);
    }

    // Método auxiliar para crear labels con estilo consistente
    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    // Métodos para actualizar la información
    public void setGeneracion(int generacion) {
        labelGeneracion.setText("Generation: " + generacion);
    }

    public void setPoblacion(int poblacion) {
        labelPoblacion.setText("Population: " + poblacion);
    }
}
