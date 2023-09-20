package com.iac.shipwar.controllers;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.TypeMarineElement;

public class Singleton {

    private static Singleton instance;
    private Map<Row, Map<Column, ShipDeployed>> goodBoard = new HashMap<Row, Map<Column, ShipDeployed>>();
    private Map<Row, Map<Column, ShipDeployed>> enemyBoard = new HashMap<Row, Map<Column, ShipDeployed>>();

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void createRowsAndColumns() {
        if (goodBoard.isEmpty() && enemyBoard.isEmpty()) {
            for (Row r : Row.values()) {
                Map<Column, ShipDeployed> mDefault = new HashMap<>(); // Initialize mDefault here
                for (Column c : Column.values()) {
                    mDefault.put(c, new ShipDeployed(TypeMarineElement.OCEAN, r, c));
                }
                this.goodBoard.put(r, mDefault);
                this.enemyBoard.put(r, mDefault);
            }
            System.out.println("se genero");
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
    

}
