package com.iac.shipwar.controllers;

import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.models.enums.VitalConditions;

public class ShipDeployed {
    private TypeMarineElement marineElement;
    private String id;
    private Row row;
    private Column column;
    private VitalConditions vital;
    private Ship ship;
    //private Map<String, 

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
