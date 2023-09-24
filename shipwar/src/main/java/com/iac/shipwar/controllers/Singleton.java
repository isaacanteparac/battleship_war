package com.iac.shipwar.controllers;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.interfaces.IGame;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.server.CreateGame;
import com.iac.shipwar.server.JoinGame;

public class Singleton {

    private static Singleton instance;
    private IGame gameInstance;
    private Map<Row, Map<Column, ShipDeployed>> goodBoard = new HashMap<Row, Map<Column, ShipDeployed>>();
    private Map<Row, Map<Column, ShipDeployed>> enemyBoard = new HashMap<Row, Map<Column, ShipDeployed>>();

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Cambiado el tipo de la variable

    public void serverActive(boolean create, String http) {
        try {
            this.gameInstance = ((create) ? new CreateGame()
                    : new JoinGame(http));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void createRowsAndColumns() {
        if (goodBoard.isEmpty() && enemyBoard.isEmpty()) {
            for (Row r : Row.values()) {
                Map<Column, ShipDeployed> mDefault = new HashMap<>();
                for (Column c : Column.values()) {
                    mDefault.put(c, new ShipDeployed(TypeMarineElement.OCEAN, r, c));
                }
                this.goodBoard.put(r, mDefault);
                this.enemyBoard.put(r, mDefault);
            }
        }
    }

    public Map<Row, Map<Column, ShipDeployed>> getGoodBoard() {
        return this.goodBoard;
    }

    public Map<Row, Map<Column, ShipDeployed>> getEnemyBoard() {
        return this.enemyBoard;
    }

    public ShipDeployed getShipEnemy(Row row, Column column) {
        return this.enemyBoard.get(row).get(column);
    }

    public ShipDeployed getShipGood(Row row, Column column) {
        return this.goodBoard.get(row).get(column);
    }


/* 
    public void attackEnemy(Row r, Column c , ShipDeployed s){
        this.enemyBoard.get(r).get(c).setMarineElement(s.getMarineElement());
        this.gameInstance.sendData(s);
    }*/


    public IGame getGameInstance() {
        return this.gameInstance;
    }

    /* TODO: ELIMINAR */
    public void imprimirGBoard() {
        for (Map.Entry<Row, Map<Column, ShipDeployed>> entry : enemyBoard.entrySet()) {
            Row row = entry.getKey();
            Map<Column, ShipDeployed> columnMap = entry.getValue();

            for (Map.Entry<Column, ShipDeployed> innerEntry : columnMap.entrySet()) {
                Column column = innerEntry.getKey();
                ShipDeployed ship = innerEntry.getValue();

                System.out.println("Row: " + row + ", Column: " + column);
                System.out.println("Ship Details: ");
                System.out.println(" - Marine Element: " + ship.getMarineElement());
                System.out.println(" - Vital Conditions: " + ship.getVital());
                System.out.println(" - Ship Type: " + ship.getShip());
                System.out.println(" - Row Position: " + ship.getRow());
                System.out.println(" - Column Position: " + ship.getColumn());
                System.out.println(" - Position Map: " + ship.getPosition().toString());
                System.out.println("--------------------------");
            }
        }

    }

    public void getBoardRowColum(Row targetRow, Column targetColumn) {
        ShipDeployed ship = enemyBoard.get(targetRow).get(targetColumn);

        if (ship != null) {
            System.out.println("\nShip Details: ");
            System.out.println(" - Marine Element: " + ship.getMarineElement());
            System.out.println(" - Vital Conditions: " + ship.getVital());
            System.out.println(" - Ship Type: " + ship.getShip());
            System.out.println(" - Row: " + ship.getRow());
            System.out.println(" - Column: " + ship.getColumn());
            System.out.println(" - Position Map: " + ship.getPosition().toString());
            System.out.println("--------------------------");
        } else {
            System.out.println("No hay barco en la fila " + targetRow + ", columna " + targetColumn);
        }
    }
    /* HASTA AQUI */
}
