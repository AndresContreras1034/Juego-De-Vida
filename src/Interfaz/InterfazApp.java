package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.Controlador;

public class InterfazApp extends JFrame {

    private PanelGrilla panelGrilla;
    private PanelSimulacion panelSimulacion;
    private PanelBotones panelBotones;

    private JLabel lblIteracion;
    private int iteracion = 0;

    private Controlador controlador;
    private Timer timer; // Para simular iteraciones automáticas

    public InterfazApp() {

        setTitle("Juego de la Vida - Conway");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Crear controlador
        controlador = new Controlador();

        // Inicializar panel de grilla y conectarlo al controlador
        panelGrilla = new PanelGrilla(20, 20, controlador);
        controlador.conectar(panelGrilla);

        panelSimulacion = new PanelSimulacion();
        panelBotones = new PanelBotones();

        // Panel derecho con simulación + botones + iteración
        JPanel panelDerecho = new JPanel(new BorderLayout(10, 10));

        // Panel de iteración
        JPanel panelIteracion = new JPanel(new BorderLayout());
        JLabel lblTexto = new JLabel("Iteración", SwingConstants.CENTER);
        lblIteracion = new JLabel("0", SwingConstants.CENTER);
        lblIteracion.setFont(new Font("Arial", Font.BOLD, 20));
        panelIteracion.add(lblTexto, BorderLayout.NORTH);
        panelIteracion.add(lblIteracion, BorderLayout.SOUTH);

        // Agregar al panel derecho
        panelDerecho.add(panelIteracion, BorderLayout.NORTH);
        panelDerecho.add(panelSimulacion, BorderLayout.CENTER);
        panelDerecho.add(panelBotones, BorderLayout.SOUTH);

        add(panelGrilla, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        // Configurar botón Start
        panelBotones.setStartListener(e -> iniciarSimulacion());

        // Configurar botón Stop
        panelBotones.setStopListener(e -> detenerSimulacion());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para iniciar la simulación
    private void iniciarSimulacion() {
        iteracion = 0;
        lblIteracion.setText(String.valueOf(iteracion));

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        // Timer para simular iteraciones automáticas
        timer = new Timer(500, new ActionListener() { // cada 500ms
            @Override
            public void actionPerformed(ActionEvent e) {
                iteracion++;
                lblIteracion.setText(String.valueOf(iteracion));

                // Avanzar la simulación
                controlador.avanzarSimulacion();

                // Actualizar panel de simulación
                int poblacion = controlador.contarCeldasVivas();
                panelSimulacion.setGeneracion(iteracion);
                panelSimulacion.setPoblacion(poblacion);
            }
        });

        timer.start();
    }

    // Método para detener la simulación
    private void detenerSimulacion() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    // Métodos para acceder a los paneles
    public PanelGrilla getPanelGrilla() {
        return panelGrilla;
    }

    public PanelSimulacion getPanelSimulacion() {
        return panelSimulacion;
    }

    public PanelBotones getPanelBotones() {
        return panelBotones;
    }

    // Método para actualizar iteración desde el controlador si se desea
    public void actualizarIteracion(int nuevaIteracion) {
        iteracion = nuevaIteracion;
        lblIteracion.setText(String.valueOf(iteracion));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazApp());
    }
}
