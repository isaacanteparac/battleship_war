package com.iac.shipwar.UI.layout;

import java.awt.event.WindowAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Window;
import com.iac.shipwar.controllers.AttackCtrl;
import com.iac.shipwar.controllers.CoordinatesCtrl;
import com.iac.shipwar.controllers.SettingCtrl;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.MainPanels;


import java.awt.event.WindowEvent;



public class UiMain {
    protected Window windows;
    protected final int sizeBoardW = 620;
    protected final int sizeBoardH = 655;
    protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
    protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
    protected final int transparency = 70;
    protected final String bgPanels = "#FFFFFF";

    public UiMain() {
        generatePanelsMain();
        generateComponents();

    }

    private void generatePanelsMain() {
        this.windows = new Window("Sea Battle", 1300, 800, true);
        this.windows.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.windows.getFrame().setVisible(false);
        this.windows.getFrame().setLocationRelativeTo(null);
        Window windowsinfo = new Window("Dashboard", 340, 850, false);
        windowsinfo.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowsinfo.getFrame().setVisible(true);
        windowsinfo.getFrame().setLocation(windows.getFrame().getX() - windowsinfo.getFrame().getWidth(),
                windows.getFrame().getY());
        windowsinfo.getFrame().setLocationRelativeTo(null);

        createdPanel(MainPanels.INFORMATION,
                new PanelCharacteristic(300, 750, 0,
                        0, 60, 10,
                        0, 0,
                        "#FFFFFF", windowsinfo.getPanel()));

        bulidBoard(MainPanels.MYBOARD, 0);
        bulidBoard(MainPanels.ENEMYBOARD, 1);

    }

    private void generateComponents() {
        final UiDashboard dashboard = new UiDashboard(this.panels.get(MainPanels.INFORMATION));
        final UiBoard uiBoard = new UiBoard(this.panels.get(MainPanels.MYBOARD), MainPanels.MYBOARD.getDisplayName(),
                "#114db1", "#FFFFFF");
        final UiBoard uiBoardEnemy = new UiBoard(this.panels.get(MainPanels.ENEMYBOARD),
                MainPanels.ENEMYBOARD.getDisplayName(), "#fef6cd", "#000000");
        final AttackCtrl attack = new AttackCtrl(uiBoardEnemy, dashboard);
        final CoordinatesCtrl coordinates = new CoordinatesCtrl(dashboard, uiBoard,
                this.panels.get(MainPanels.ENEMYBOARD));
        final SettingCtrl settingGame = new SettingCtrl(dashboard, uiBoard, this.windows);

        this.panels.get(MainPanels.ENEMYBOARD).visible(false);
    }

    public Map<MainPanels, Panel_> getMapPanels() {
        return this.panels;
    }

    private void bulidBoard(MainPanels board, int xposition) {
        createdPanel(board,
                new PanelCharacteristic(this.sizeBoardW, this.sizeBoardH, 10,
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
