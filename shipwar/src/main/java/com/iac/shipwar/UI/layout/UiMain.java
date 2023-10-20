package com.iac.shipwar.UI.layout;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Window;
import com.iac.shipwar.controllers.AttackCtrl;
import com.iac.shipwar.controllers.CoordinatesCtrl;
import com.iac.shipwar.controllers.SettingCtrl;
import com.iac.shipwar.controllers.Singleton;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.MainPanels;
import com.iac.shipwar.models.enums.Ui;

public class UiMain {
        protected Window windows;
        protected final int sizeBoardW = Ui.INTERNAL_BOARD_WIDTH.getIntValue();
        protected final int sizeBoardH = Ui.INTERNAL_BOARD_HEIGHT.getIntValue();
        protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
        protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
        protected final int transparency = Ui.INTERNAL_BOARD_TRANSPARENCY.getIntValue();
        protected final String bgPanels = Ui.BACKGROUND_COLOR.getStringValue();
        protected final Singleton singleton = Singleton.getInstance();

        /*
         * tengo que hacer una funcion que contenga el while para que este constanatemente preguntando si la variable de
         * getcomponentattack se encuentra true o false, para saber si se muestra o no
         */
        public UiMain() {
                generatePanelsMain();
                generateComponents();

        }

        private void generatePanelsMain() {
                this.windows = new Window(Ui.BOARD_TITLE.getStringValue(), Ui.WINDOW_BOARD_WIDTH.getIntValue(),
                                Ui.WINDOW_BOARD_HEIGHT.getIntValue(), false);
                this.windows.getFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                this.windows.getFrame().setVisible(false);
                this.windows.getFrame().setLocationRelativeTo(null);
                Window windowsinfo = new Window(Ui.DASHBOARD_TITLE.getStringValue(),
                                Ui.WINDOW_DASHBOARD_WIDTH.getIntValue(),
                                Ui.WINDOW_DASHBOARD_HEIGHT.getIntValue(), false);
                windowsinfo.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windowsinfo.getFrame().setVisible(true);
                windowsinfo.getFrame().setLocation(windows.getFrame().getX() - windowsinfo.getFrame().getWidth(),
                                windows.getFrame().getY());
                windowsinfo.getFrame().setLocationRelativeTo(null);

                createdPanel(MainPanels.INFORMATION,
                                new PanelCharacteristic(Ui.INTERNAL_DASHBOARD_WIDTH.getIntValue(),
                                                Ui.INTERNAL_DASHBOARD_HEIGHT.getIntValue(), 0,
                                                0, 60, 10,
                                                0, 0,
                                                "#FFFFFF", windowsinfo.getPanel()));

                bulidBoard(MainPanels.MYBOARD, 0);
                bulidBoard(MainPanels.ENEMYBOARD, 1);

        }

        private void generateComponents() {
                final UiDashboard dashboard = new UiDashboard(this.panels.get(MainPanels.INFORMATION));
                this.singleton.setDashboard(dashboard);
                final UiBoard uiBoard = new UiBoard(this.panels.get(MainPanels.MYBOARD),
                                MainPanels.MYBOARD.getDisplayName(),
                                "#1b1c1c", "#FFFFFF", false);
                final UiBoard uiBoardEnemy = new UiBoard(this.panels.get(MainPanels.ENEMYBOARD),
                                MainPanels.ENEMYBOARD.getDisplayName(), "#585858", "#ffffff", false);
                final AttackCtrl attack = new AttackCtrl(uiBoardEnemy, dashboard);
                final CoordinatesCtrl coordinates = new CoordinatesCtrl(dashboard, uiBoard,
                                this.panels.get(MainPanels.ENEMYBOARD));
                final SettingCtrl settingGame = new SettingCtrl(dashboard, uiBoard, this.windows);
                this.singleton.setMyBoard(uiBoard);
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
