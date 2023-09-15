package com.iac.shipwar.models.enums;

public enum Dashboard {
    SCORE("Puntuacion"),
    SHIP("Barcos"),
    DESTROYED("Destruidos"),
    FAILED("Fallados"),
    COORDINATES("Coordenadas"),
    ATTACK("Atacar");

    private final String displayName;

    Dashboard(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
