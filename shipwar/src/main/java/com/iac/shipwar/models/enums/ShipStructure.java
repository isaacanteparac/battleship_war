package com.iac.shipwar.models.enums;

public enum ShipStructure {
    BOW("Proa"),
    CENTER("Centro"),
    STERN("Popa");

    private final String displayName;

    ShipStructure(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

