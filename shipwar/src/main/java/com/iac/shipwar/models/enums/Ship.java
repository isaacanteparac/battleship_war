package com.iac.shipwar.models.enums;

public enum Ship {
    SMALL(1, "#ec0bcf", "Corbetas", 4),
    MEDIUM(2, "#ec980b", "Fragatas", 3),
    BIG(3, "#0bec28", "Buques", 50);

    private final int size;
    private final String colorHex;
    private final String displayName;
    private final int number_;

    Ship(int size, String colorHex, String displayName, int number_) {
        this.size = size;
        this.displayName = displayName;
        this.colorHex = colorHex;
        this.number_ = number_;
    }

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number_;
    }

    public String getColorHex() {
        return colorHex;
    }

    public String getDisplayName() {
        return displayName;
    }
}
