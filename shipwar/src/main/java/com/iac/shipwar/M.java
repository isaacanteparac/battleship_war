package com.iac.shipwar;

import com.iac.shipwar.models.enums.Column;
import java.util.ArrayList;
import java.util.List;

public class M {
    public static void main(String[] args) {
        int[][] matrix = new int[10][10];
        Column userX = Column.C6; // Cambiado a Column
        int userY = 5;
        int shipSize = 3;

        System.out.println("posicion inicial x:" + userX + " y:" + 5);

        markShipPositions(matrix, userX, userY, shipSize);

        // calcula los mov válidos
        List<int[]> validMoves = calculateValidMoves(matrix, userX, userY, shipSize);

        displayMatrix(matrix, validMoves);
        
    }

    public static void markShipPositions(int[][] matrix, Column x, int y, int shipSize) {
        for (int i = 0; i < shipSize; i++) {
            if (x.getIndex() + i < 10) {
                matrix[x.getIndex() + i][y] = 1;
            }
        }
    }

    public static List<int[]> calculateValidMoves(int[][] matrix, Column x, int y, int size) {
        List<int[]> validMoves = new ArrayList<>();
        size -= 1;

        // arriba
        if (x.getIndex() - size >= 0) {
            validMoves.add(new int[]{x.getIndex() - size, y});
        }

        // abajo
        if (x.getIndex() + size <= 9) {
            validMoves.add(new int[]{x.getIndex() + size, y});
        }

        // izquierda
        if (y - size >= 0) {
            validMoves.add(new int[]{x.getIndex(), y - size});
        }

        // derecha
        if (y + size <= 9) {
            validMoves.add(new int[]{x.getIndex(), y + size});
        }

        System.out.println("Movimientos válidos:");

        for (int[] move : validMoves) {
            System.out.println(move+": Coordenada x: " + move[0] + ", Coordenada y: " + move[1]);
        }

        return validMoves;
    }

    public static void displayMatrix(int[][] matrix, List<int[]> validMoves) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == Column.C6.getIndex() && j == 5) {
                    System.out.print("x "); // posición inicial

                } else if (containsCoordinate(validMoves, i, j)) {
                    System.out.print("V "); // movimiento válido
                } else {
                    System.out.print("- "); // celda vacía
                }
            }
            System.out.println();
        }
    }

    public static boolean containsCoordinate(List<int[]> coordinates, int x, int y) {
        for (int[] coordinate : coordinates) {
            if (coordinate[0] == x && coordinate[1] == y) {
                return true;
            }
        }
        return false;
    }
}
