package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Window;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.ShipCharacteristic;
import com.iac.shipwar.data.enums.MainPanels;
import com.iac.shipwar.data.enums.ShipSize;
import com.iac.shipwar.data.enums.StatusBoard;

public class UiMain {
    protected Window windows;
    protected final int sizeBoardWH = 750;
    protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
    protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
    protected final int transparency = 80;
    protected final String bgPanels = "#FFFFFF";// #eff3f7
    protected final Map<ShipSize, ShipCharacteristic> shipsC = new HashMap<ShipSize, ShipCharacteristic>() {
        {
            put(ShipSize.SMALL, new ShipCharacteristic(ShipSize.SMALL, "Pequeño", "#045cfb", 1, 5));
            put(ShipSize.MEDIUM, new ShipCharacteristic(ShipSize.MEDIUM, "Mediano", "#a304fb", 2, 3));
            put(ShipSize.BIG, new ShipCharacteristic(ShipSize.BIG, "Grande", "#5cfb04", 3, 2));

        }
    };

    public UiMain() {
        generatePanelsMain();
        generateComponents();

    }

    private void generatePanelsMain() {
        this.windows = new Window("Barquitos Peleadores", 1905, 1000);
        createdPanel(MainPanels.INFORMATION,
                new PanelCharacteristic(300, 900, 20,
                        this.transparency, 60, 10,
                        0, 0,
                        this.bgPanels, this.windows.getPanel()));

        createdPanel(MainPanels.MYBOARD,
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20,
                        this.transparency, 60, 15,
                        1, 0,
                        this.bgPanels, this.windows.getPanel()));

        createdPanel(MainPanels.ENEMYBOARD,
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20,
                        this.transparency, 60, 10,
                        2, 0,
                        this.bgPanels, this.windows.getPanel()));
    }

    private void generateComponents() {
        final UiInformation uiInformation = new UiInformation(this.panels.get(MainPanels.INFORMATION), shipsC);
        final UiBoard uiBoard = new UiBoard(this.panels.get(MainPanels.MYBOARD), "Yo", "#181a1b", "#FFFFFF");// d19bfe
        final UiBoard uiBoardEnemy = new UiBoard(this.panels.get(MainPanels.ENEMYBOARD), "Enemigo", "#fbd404", "#000000");
        final UiInput uiInput = new UiInput(uiBoardEnemy);
        uiInput.mountComponentRoute(uiInformation.getBox(StatusBoard.ROUTE));
        uiInput.mountComponentAttack(uiInformation.getBox(StatusBoard.ATTACK));
        uiInformation.updateTextsModifier(StatusBoard.FAILED, "000");
    }

    public Map<MainPanels, Panel_> getMapPanels() {
        return this.panels;
    }

    private void createdPanel(MainPanels name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}
