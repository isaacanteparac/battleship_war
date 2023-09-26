package com.iac.shipwar.models.enums;

public enum Row {
    R1(0),
    R2(1),
    R3(2),
    R4(3),
    R5(4),
    R6(5),
    R7(6),
    R8(7),
    R9(8),
    R10(9);

    private final int index;

    Row(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Row getByIndex(int index) {
        for (Row row : Row.values()) {
            if (row.index == index) {
                return row;
            }
        }
        throw new IllegalArgumentException("No enum constant with index " + index);
    }

}
