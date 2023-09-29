package com.iac.shipwar.models.enums;

public enum Dashboard {
    SETTING("Confirgurar Partida"),
    SCORE("Puntuacion"),
    SHIP("Barcos"),
    SHOOTINGLOG("Registro de disparos"),
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
