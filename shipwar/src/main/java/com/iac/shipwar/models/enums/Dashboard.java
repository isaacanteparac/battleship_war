package com.iac.shipwar.models.enums;

import java.util.Set;

public enum Dashboard {
    SETTING("Confirgurar Partida"),
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
