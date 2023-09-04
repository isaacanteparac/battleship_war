package logic;

import java.util.ArrayList;

public class Board {

    protected ArrayList<ArrayList<Integer>> matrix;
    protected int rows = 5; // Número de filas
    protected int columns = rows; // Número de columnas
    protected int empty = 0;

    public Board(){
        rowXcolumn();
    }

    private void rowXcolumn() {
        this.matrix = new ArrayList<>(this.rows);
        for (int i = 0; i < this.rows; i++) {
            ArrayList<Integer> aRow = new ArrayList<>(this.columns);
            for (int j = 0; j < this.columns; j++) {
                aRow.add(this.empty); // Inicializar todas las celdas con valorInicial
            }
            this.matrix.add(aRow);
        }
    }

    public void printMatrix() {
        for (ArrayList<Integer> row : this.matrix) {
            for (Integer valor : row) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

}
