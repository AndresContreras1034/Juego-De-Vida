package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import controlador.Controlador;

public class PanelGrilla extends JPanel {
    private int filas;
    private int columnas;
    private boolean[][] celulas; // Estado interno
    private int tamañoCelula = 20; // Tamaño de cada celda
    private Controlador controlador;

    // Constructor
    public PanelGrilla(int filas, int columnas, Controlador controlador) {
        this.filas = filas;
        this.columnas = columnas;
        this.controlador = controlador;
        this.celulas = new boolean[filas][columnas];

        setPreferredSize(new Dimension(columnas * tamañoCelula, filas * tamañoCelula));

        // Listener para clics
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = e.getX() / tamañoCelula;
                int fila = e.getY() / tamañoCelula;

                if (fila >= 0 && fila < filas && col >= 0 && col < columnas) {
                    // Cambiar estado de la celda
                    celulas[fila][col] = !celulas[fila][col];

                    // Notificar al controlador si existe
                    if (controlador != null) {
                        controlador.putCell(fila, col, celulas[fila][col] ? "C" : "-");
                    }

                    // Repintar la grilla
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int x = j * tamañoCelula;
                int y = i * tamañoCelula;

                // Celda viva: verde, celda muerta: gris claro
                if (celulas[i][j]) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }
                g.fillRect(x, y, tamañoCelula, tamañoCelula);

                // Dibujar borde
                g.setColor(Color.BLACK);
                g.drawRect(x, y, tamañoCelula, tamañoCelula);
            }
        }
    }

    // ================== Inicialización ==================

    // Rellenar aleatoriamente
    public void inicializarAleatorio(double probabilidadViva) {
        Random rand = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celulas[i][j] = rand.nextDouble() < probabilidadViva;
            }
        }
        repaint();
    }

    // Patrón: bloque 2x2 (estable)
    public void inicializarBloque(int fila, int col) {
        if (fila + 1 < filas && col + 1 < columnas) {
            celulas[fila][col] = true;
            celulas[fila][col + 1] = true;
            celulas[fila + 1][col] = true;
            celulas[fila + 1][col + 1] = true;
        }
        repaint();
    }

    // Patrón: glider (nave planeadora)
    public void inicializarGlider(int fila, int col) {
        if (fila + 2 < filas && col + 2 < columnas) {
            celulas[fila][col + 1] = true;
            celulas[fila + 1][col + 2] = true;
            celulas[fila + 2][col] = true;
            celulas[fila + 2][col + 1] = true;
            celulas[fila + 2][col + 2] = true;
        }
        repaint();
    }

    // Patrón: oscilador (blinker)
    public void inicializarBlinker(int fila, int col) {
        if (fila < filas && col + 2 < columnas) {
            celulas[fila][col] = true;
            celulas[fila][col + 1] = true;
            celulas[fila][col + 2] = true;
        }
        repaint();
    }

    // ================== Getters y Setters ==================
    public void setCelulas(boolean[][] nuevasCelulas) {
        this.celulas = nuevasCelulas;
        repaint();
    }

    public boolean[][] getCelulas() {
        return celulas;
    }
}
