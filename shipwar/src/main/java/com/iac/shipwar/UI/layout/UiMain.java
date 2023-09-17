package com.iac.shipwar.UI.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.UI.components.Attack;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Window;
import com.iac.shipwar.controllers.CoordenadasCtrl;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.MainPanels;

public class UiMain {
    protected Window windows;
    protected final int sizeBoardW = 520;
    protected final int sizeBoardH = 550;
    protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
    protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
    protected final int transparency = 70;
    protected final String bgPanels = "#FFFFFF";


    public UiMain() {
        generatePanelsMain();
        generateComponents();

    }

    private void generatePanelsMain() {
        this.windows = new Window("Barquitos Peleadores", 1600, 850);
        createdPanel(MainPanels.INFORMATION,
                new PanelCharacteristic(250, 700, 20,
                        255, 60, 10,
                        0, 0,
                        "#FFFFFF", this.windows.getPanel()));

        bulidBoard(MainPanels.MYBOARD, 1);
        bulidBoard(MainPanels.ENEMYBOARD,2);

    }

    private void generateComponents() {
        final UiDashboard dashboard = new UiDashboard(this.panels.get(MainPanels.INFORMATION));
        final UiBoard uiBoard = new UiBoard(this.panels.get(MainPanels.MYBOARD), MainPanels.MYBOARD.getDisplayName(),
                "#114db1", "#FFFFFF");// d19bfe
         final UiBoard uiBoardEnemy = new UiBoard(this.panels.get(MainPanels.ENEMYBOARD),
                MainPanels.ENEMYBOARD.getDisplayName(), "#b1114d", "#FFFFFF");
                

        final Attack attack = new Attack(uiBoardEnemy, dashboard);
        final CoordenadasCtrl coordinates = new CoordenadasCtrl(dashboard, uiBoard, this.panels.get(MainPanels.ENEMYBOARD));

        this.panels.get(MainPanels.ENEMYBOARD).visible(false);
    }

    public Map<MainPanels, Panel_> getMapPanels() {
        return this.panels;
    }

    private void bulidBoard(MainPanels board, int xposition) {
        createdPanel(board,
                new PanelCharacteristic(this.sizeBoardW, this.sizeBoardH, 20,
                        this.transparency, 60, 10,
                        xposition, 0,
                        this.bgPanels, this.windows.getPanel()));
    }

    private void createdPanel(MainPanels name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}
