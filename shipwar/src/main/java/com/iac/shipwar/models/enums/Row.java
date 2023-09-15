package com.iac.shipwar.models.enums;

public enum Row {
    R1(0),
    R2(1),
    R3(2),
    R4(3),
    R5(4),
    R6(5),
    R7(6),
    R8(7);

    private final int index;

    Row(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}

