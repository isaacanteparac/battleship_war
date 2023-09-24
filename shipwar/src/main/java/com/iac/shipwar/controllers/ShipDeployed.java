package com.iac.shipwar.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.ShipStructure;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.models.enums.VitalConditions;

public class ShipDeployed implements Serializable {
    private TypeMarineElement marineElement;
    private Row row;
    private Column column;
    private VitalConditions vital;
    private Ship ship;
    private Map<ShipStructure, ShipDeployed> positions = new HashMap<ShipStructure, ShipDeployed>();

    public ShipDeployed(TypeMarineElement marineElement, Row row, Column column) {
        this.marineElement = marineElement;
        this.row = row;
        this.column = column;
    }

    public TypeMarineElement getMarineElement() {
        return marineElement;
    }

    public void setMarineElement(TypeMarineElement marineElement) {
        this.marineElement = marineElement;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public VitalConditions getVital() {
        return vital;
    }

    public void setVital(VitalConditions vital) {
        this.vital = vital;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setPosition(ShipStructure key_, ShipDeployed content) {
        this.positions.put(key_, content);
    }

    public Map<ShipStructure, ShipDeployed> getPosition() {
        return this.positions;
    }

    public void printDetails(String text) {
        System.out.println("\n----- ATTACK DETAIL: " + text + " ------");
        System.out.println(" - Marine Element: " + this.marineElement);
        System.out.println(" - Row: " + this.row);
        System.out.println(" - Column: " + this.column);
        System.out.println(" - Vital Conditions: " + this.vital);
        System.out.println(" - Ship Type: " + this.ship);

        if (this.positions.isEmpty()) {
            System.out.println("No positions assigned.");
        } else {
            System.out.println("Positions:");
            for (Map.Entry<ShipStructure, ShipDeployed> entry : this.positions.entrySet()) {
                ShipStructure structure = entry.getKey();
                ShipDeployed content = entry.getValue();
                System.out.println(" - Structure: " + structure);
                System.out.println("   - Marine Element: " + content.getMarineElement());
                System.out.println("   - Row: " + content.getRow());
                System.out.println("   - Column: " + content.getColumn());
                System.out.println("   - Vital Conditions: " + content.getVital());
                System.out.println("   - Ship Type: " + content.getShip());
            }
        }
        System.out.println("--------------------------\n");
    }

}
