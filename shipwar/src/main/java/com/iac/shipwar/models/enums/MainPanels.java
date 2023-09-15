package com.iac.shipwar.models.enums;

public enum MainPanels {
    INFORMATION("Informacion"),
    MYBOARD("Yo"),
    ENEMYBOARD("Enemigo");

    private final String displayName;

    MainPanels(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
