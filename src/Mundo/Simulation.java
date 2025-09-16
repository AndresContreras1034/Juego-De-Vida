package Mundo;

/**
 * Clase Simulation: maneja la evolución de las células
 * y sirve como capa intermedia entre la UI y GameOfLife
 */
public class Simulation {

    private GameOfLife gameOfLife;

    public Simulation(int filas, int columnas) {
        this.gameOfLife = new GameOfLife(filas, columnas);
    }

    public GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    // Inicializa o reinicia la simulación
    public void reiniciar(int filas, int columnas) {
        this.gameOfLife = new GameOfLife(filas, columnas);
    }

    // Avanza una generación
    public void avanzarGeneracion() {
        gameOfLife.siguienteIteracion();
    }

    // Control del hilo de la simulación
    public void iniciar() {
        gameOfLife.iniciar();
    }

    public void pausar() {
        gameOfLife.pausar();
    }

    public void detener() {
        gameOfLife.detener();
    }

    public void setVelocidad(int delay) {
        gameOfLife.setDelay(delay);
    }

    public boolean[][] getEstadoActual() {
        return gameOfLife.getCelulas();
    }

    public void setCelula(int fila, int col, boolean estado) {
        gameOfLife.setCelula(fila, col, estado);
    }
}
