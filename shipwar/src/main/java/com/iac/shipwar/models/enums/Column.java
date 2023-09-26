package com.iac.shipwar.models.enums;

public enum Column {
    C1(0),
    C2(1),
    C3(2),
    C4(3),
    C5(4),
    C6(5),
    C7(6),
    C8(7),
    C9(8),
    C10(9);

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Column getByIndex(int index) {
        for (Column column : Column.values()) {
            if (column.index == index) {
                return column;
            }
        }
        throw new IllegalArgumentException("No enum constant with index " + index);
    }

}
