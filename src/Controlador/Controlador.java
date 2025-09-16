package controlador;

import Interfaz.PanelGrilla;
import Mundo.GameOfLife;

public class Controlador {

    private PanelGrilla pnlMundo;
    private GameOfLife gameOfLife;
    private Thread hiloSimulacion;

    public Controlador() {
        // Inicializamos GameOfLife con 20x20 celdas por defecto
        gameOfLife = new GameOfLife(20, 20);
    }

    // Conectar panel de la interfaz con el controlador
    public void conectar(PanelGrilla pnlMundo) {
        this.pnlMundo = pnlMundo;
        // Inicializamos el panel con el estado actual
        pnlMundo.setCelulas(gameOfLife.getCelulas());
    }

    // Actualiza el estado de una celda al hacer clic
    public void putCell(int fila, int col, String cell) {
        boolean estado = cell.equals("C");
        gameOfLife.setCelula(fila, col, estado);
        System.out.println("ctrl: (" + fila + "," + col + ") " + cell);
        pnlMundo.setCelulas(gameOfLife.getCelulas());
    }

    // Avanza una iteración de la simulación (sin hilo)
    public void avanzarSimulacion() {
        gameOfLife.siguienteIteracion();
        pnlMundo.setCelulas(gameOfLife.getCelulas());
    }

    // ================== Contar células vivas ==================
    public int contarCeldasVivas() {
        boolean[][] celulas = gameOfLife.getCelulas();
        int count = 0;
        for (boolean[] fila : celulas) {
            for (boolean cel : fila) {
                if (cel) count++;
            }
        }
        return count;
    }

    // ================== Control de hilos ==================
    public void iniciarSimulacion() {
        if (hiloSimulacion == null || !hiloSimulacion.isAlive()) {
            hiloSimulacion = new Thread(() -> {
                // Usamos un delay fijo de 500ms
                while (true) {
                    gameOfLife.siguienteIteracion();
                    pnlMundo.setCelulas(gameOfLife.getCelulas());
                    try {
                        Thread.sleep(500); // Ajusta la velocidad de la simulación
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            hiloSimulacion.start();
        }
    }

    public void pausarSimulacion() {
        if (hiloSimulacion != null) {
            hiloSimulacion.interrupt();
        }
    }

    public void detenerSimulacion() {
        if (hiloSimulacion != null) {
            hiloSimulacion.interrupt();
            hiloSimulacion = null;
        }
        // Reiniciamos la UI con celdas vacías si se desea
        pnlMundo.setCelulas(gameOfLife.getCelulas());
    }

    // Opcional: cambiar la velocidad de la simulación
    public void setVelocidad(int delay) {
        // Si quieres, puedes implementar delay dinámico aquí
    }
}
