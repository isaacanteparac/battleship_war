package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.widgets.Panel_;
import com.iac.shipwar.components.widgets.Window;
import com.iac.shipwar.controllers.Attack;
import com.iac.shipwar.controllers.Coordinates;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.MainPanels;
import com.iac.shipwar.models.enums.Dashboard;

public class UiMain {
    protected Window windows;
    protected final int sizeBoardW = 650;
    protected final int sizeBoardH = 680;
    protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
    protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
    protected final int transparency = 70;
    protected final String bgPanels = "#FFFFFF";


    public UiMain() {
        generatePanelsMain();
        generateComponents();

    }

    private void generatePanelsMain() {
        this.windows = new Window("Barquitos Peleadores", 1905, 1000);
        createdPanel(MainPanels.INFORMATION,
                new PanelCharacteristic(300, 900, 20,
                        0, 60, 10,
                        0, 0,
                        this.bgPanels, this.windows.getPanel()));

        bulidBoard(MainPanels.MYBOARD);
        bulidBoard(MainPanels.ENEMYBOARD);

    }

    private void generateComponents() {
        final UiDashboard dashboard = new UiDashboard(this.panels.get(MainPanels.INFORMATION));
        final UiBoard uiBoard = new UiBoard(this.panels.get(MainPanels.MYBOARD), MainPanels.MYBOARD.getDisplayName(),
                "#114db1", "#FFFFFF");// d19bfe
        final UiBoard uiBoardEnemy = new UiBoard(this.panels.get(MainPanels.ENEMYBOARD),
                MainPanels.ENEMYBOARD.getDisplayName(), "#b1114d", "#FFFFFF");

        final Attack attack = new Attack(uiBoardEnemy, dashboard);
        final Coordinates coordinates = new Coordinates(dashboard, uiBoard, this.panels.get(MainPanels.ENEMYBOARD));

        this.panels.get(MainPanels.ENEMYBOARD).visible(false);
        dashboard.updateTextsModifier(Dashboard.FAILED, "000");
    }

    public Map<MainPanels, Panel_> getMapPanels() {
        return this.panels;
    }

    private void bulidBoard(MainPanels board) {
        createdPanel(board,
                new PanelCharacteristic(this.sizeBoardW, this.sizeBoardH, 20,
                        this.transparency, 60, 15,
                        2, 0,
                        this.bgPanels, this.windows.getPanel()));
    }

    private void createdPanel(MainPanels name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}
