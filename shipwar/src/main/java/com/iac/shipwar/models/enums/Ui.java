package com.iac.shipwar.models.enums;

public enum Ui {
    BOARD_TITLE(null, "Sea Battle"),
    DASHBOARD_TITLE(null, "Dashboard"),
    WINDOW_DASHBOARD_WIDTH(340,""),
    WINDOW_DASHBOARD_HEIGHT(850,null),
    INTERNAL_DASHBOARD_WIDTH(300,null),
    INTERNAL_DASHBOARD_HEIGHT(750,null),
    WINDOW_BOARD_WIDTH(1300,null),
    WINDOW_BOARD_HEIGHT(800,null),
    INTERNAL_BOARD_WIDTH(620,null),
    INTERNAL_BOARD_HEIGHT(680,null),
    INTERNAL_BOARD_TRANSPARENCY(255,null),
    BACKGROUND_COLOR(null,"#000000"),
    DEFAULT_BUTTON_COLOR(null,"#FFFFFF"),
    BUTTON_WIDTH_DEFAULT(55, null),
    BUTTON_HEIGHT_DEFAULT(55,null);
    


    private Integer intValue;
    private String stringValue;

    Ui(Integer intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void printValues() {
        System.out.println("Name: " + this.name());
        System.out.println("Integer Value: " + intValue);
        System.out.println("String Value: " + stringValue);
    }

    public static void main(String[] args) {
        for (Ui ui : Ui.values()) {
            ui.printValues();
            System.out.println(); // Para separar las salidas de cada constante
        }
    }
}
