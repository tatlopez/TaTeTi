package com.example.tateti;
import java.util.Random;

public class ControlDeJuego {

    /*
    * Si el valor es 0 esta vacia
    * Si el valor es 1 cruces
    * Si el valor es 2 circulos
    * */
    int[][] tablero = new int[3][3];

    int[][] combinacionesGanadoras  = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // horizontales
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // verticales
            {0, 4, 8}, {2, 4, 6}              // diagonales
    };

    public ControlDeJuego(){
        for(int i = 0; i<3; i++)
            for(int j = 0; j < 3; j++)
                tablero[i][j] = 0;
    }

    public void asignarValorJugado(int i, int j, String usa){
        if (usa.equalsIgnoreCase("circulos")) {
            tablero[i][j] = 2;
        } else {
            tablero[i][j] = 1;
        }

    }

    public int[] proximoMovimiento(){
        int[] resultado = new int[2];

        // Verificar si hay alguna combinación de dos símbolos propios que pueda completar una línea
        for (int[] combinacion : combinacionesGanadoras) {
            int propio = 0;
            int vacio = 0;
            for (int pos : combinacion) {
                int fila = pos / 3;
                int columna = pos % 3;
                if (tablero[fila][columna] == 0) {
                    vacio++;
                    resultado[0] = fila;
                    resultado[1] = columna;
                } else if (tablero[fila][columna] == 2) {
                    propio++;
                }
            }
            if (propio == 2 && vacio == 1) {
                return resultado; // Completar la línea propia
            }
        }

        // Verificar si hay alguna combinación de dos símbolos del oponente que pueda bloquear
        for (int[] combinacion : combinacionesGanadoras) {
            int oponente = 0;
            int vacio = 0;
            for (int pos : combinacion) {
                int fila = pos / 3;
                int columna = pos % 3;
                if (tablero[fila][columna] == 0) {
                    vacio++;
                    resultado[0] = fila;
                    resultado[1] = columna;
                } else if (tablero[fila][columna] == 1) {
                    oponente++;
                }
            }
            if (oponente == 2 && vacio == 1) {
                return resultado; // Bloquear al oponente
            }
        }

        // Si no se pueden completar líneas propias ni bloquear al oponente, hacer un movimiento aleatorio
        Random random = new Random();
        do {
            resultado[0] = random.nextInt(3); // Fila aleatoria
            resultado[1] = random.nextInt(3); // Columna aleatoria
        } while (tablero[resultado[0]][resultado[1]] != 0); // Repetir hasta encontrar una casilla vacía

        return resultado;
    }

    public boolean gano(){

        for (int[] combinacion : combinacionesGanadoras) {
            int valor = tablero[combinacion[0] / 3][combinacion[0] % 3];
            if (valor != 0 && valor == tablero[combinacion[1] / 3][combinacion[1] % 3]
                    && valor == tablero[combinacion[2] / 3][combinacion[2] % 3]) {
                return true;
            }
        }
        return false;
    }

    public boolean hayEmpate() {
        // Contador para el número de casillas llenas
        int casillasLlenas = 0;

        // Recorremos el tablero y contamos las casillas llenas
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != 0) {
                    casillasLlenas++;
                }
            }
        }

        // Si todas las casillas están llenas y no hay un ganador, entonces es un empate
        return casillasLlenas == 9 && !gano();
    }



}
