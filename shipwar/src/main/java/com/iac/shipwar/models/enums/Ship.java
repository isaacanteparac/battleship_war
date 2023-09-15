package com.iac.shipwar.models.enums;

public enum Ship {
    SMALL(1, "#045cfb", "Corbetas", 4),
    MEDIUM(2, "#a304fb", "Fragatas", 3),
    BIG(3, "#5cfb04", "Buques", 2);

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
