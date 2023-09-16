package com.iac.shipwar.UI.util;

import java.util.ArrayList;

public class EnumList<T extends Enum<T>> {
    private ArrayList<T> enumList = new ArrayList<>();

    public void addEnum(T value) {
        enumList.add(value);
    }

    public ArrayList<T> getArrayList() {
        return enumList;
    }
}
