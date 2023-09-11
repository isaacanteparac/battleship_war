package com.iac.shipwar;

import java.util.HashMap;
import java.util.Map;


import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.layout.UiBoard;
import com.iac.shipwar.components.layout.UiInformation;
import com.iac.shipwar.components.layout.UiInput;
import com.iac.shipwar.components.layout.UiMain;
import com.iac.shipwar.data.ShipCharacteristic;
import com.iac.shipwar.data.enums.Column;
import com.iac.shipwar.data.enums.MainPanels;
import com.iac.shipwar.data.enums.Row;
import com.iac.shipwar.data.enums.ShipSize;
import com.iac.shipwar.data.enums.StatusBoard;


/**
 *
 * @author isaac
 */
public class App {

    static final Map<ShipSize, ShipCharacteristic> shipsC = new HashMap<ShipSize, ShipCharacteristic>(){{
        put(ShipSize.SMALL, new ShipCharacteristic(ShipSize.SMALL, "Pequeño", "#fbffa2", 1, 5));
        put(ShipSize.MEDIUM, new ShipCharacteristic(ShipSize.MEDIUM, "Mediano", "#64ffc8", 2, 3));
        put(ShipSize.BIG, new ShipCharacteristic(ShipSize.BIG, "Grande", "#bba6ff", 3, 2));
        
    }};

    public static void main(String[] args) {
        final UiMain ui = new UiMain();
        Map<MainPanels, Panel_> uiPanels = ui.getMapPanels();

        final UiInformation uiInformation = new UiInformation(uiPanels.get(MainPanels.INFORMATION), shipsC);
        final UiInput uiInput =  new UiInput();
        uiInput.mountComponentRoute(uiInformation.getBox(StatusBoard.ROUTE));
        uiInput.mountComponentAttack(uiInformation.getBox(StatusBoard.ATTACK));
        final UiBoard uiBoard = new UiBoard(uiPanels.get(MainPanels.MYBOARD), "Yo","#d19bfe");
        final UiBoard uiBoardEnemy = new UiBoard(uiPanels.get(MainPanels.ENEMYBOARD), "Enemigo", "#fed19b");


        


        uiBoard.changeColor(Row.R8, Column.C1, "#fbffa2");
        uiBoard.changeColor(Row.R1, Column.C8, "#64ffc8");
        uiBoard.changeColor(Row.R1, Column.C7, "#64ffc8");

        

        uiInformation.updateTextsModifier(StatusBoard.SCORE, "modificado");

    }
}
