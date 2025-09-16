package Mundo;

public class GameOfLife implements Runnable {

    private boolean[][] celulas;
    private int filas;
    private int columnas;
    private boolean corriendo = false; // Controla la simulación
    private int delay = 200; // Milisegundos entre iteraciones

    public GameOfLife(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celulas = new boolean[filas][columnas];
    }

    public boolean[][] getCelulas() {
        return celulas;
    }

    public void setCelula(int fila, int col, boolean estado) {
        celulas[fila][col] = estado;
    }

    // Calcula la siguiente iteración según reglas del Juego de la Vida
    public void siguienteIteracion() {
        boolean[][] nueva = new boolean[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinos = contarVecinosVivos(i, j);
                if (celulas[i][j]) {
                    // Celula viva: sobrevive con 2 o 3 vecinos
                    nueva[i][j] = vecinos == 2 || vecinos == 3;
                } else {
                    // Celula muerta: nace con 3 vecinos
                    nueva[i][j] = vecinos == 3;
                }
            }
        }

        celulas = nueva;
    }

    // Cuenta vecinos vivos de una celda
    private int contarVecinosVivos(int fila, int col) {
        int count = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == fila && j == col) continue;
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    if (celulas[i][j]) count++;
                }
            }
        }
        return count;
    }

    // ====================== Manejo de hilos ======================

    @Override
    public void run() {
        corriendo = true;
        while (corriendo) {
            siguienteIteracion();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                corriendo = false;
            }
        }
    }

    public void iniciar() {
        if (!corriendo) {
            Thread hilo = new Thread(this);
            hilo.start();
        }
    }

    public void pausar() {
        corriendo = false;
    }

    public void detener() {
        corriendo = false;
        // Si quieres reiniciar la simulación, puedes agregar:
        // celulas = new boolean[filas][columnas];
    }
    public int getDelay() {
    return delay;
}


    public void setDelay(int delay) {
        this.delay = delay;
    }
}
