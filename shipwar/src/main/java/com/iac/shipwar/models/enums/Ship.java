package com.iac.shipwar.models.enums;

public enum Ship {
    MINI(1, "#dc5a10", "Mini", 4),
    SMALL(2, "#ac10dc", "Pequeño", 3),
    MEDIUM(3, "#dcac10", "Mediano", 2),
    BIG(4, "#10dcac", "Grande", 1);//1

    private final int size;
    private String colorHex;
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

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
