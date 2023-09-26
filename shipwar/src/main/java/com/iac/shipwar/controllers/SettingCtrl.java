package com.iac.shipwar.controllers;

import javax.swing.JButton;

import com.iac.shipwar.UI.components.Setting;
import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Window;
import com.iac.shipwar.models.enums.Dashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingCtrl extends Setting {

    protected Singleton singleton = Singleton.getInstance();
    protected Window w;

    public SettingCtrl(UiDashboard ud, UiBoard myBoard, Window window) {
        super(ud, myBoard);
        this.w = window;
        joinGameButton(this.btnJoinGame);
        createGameButton(this.btnCreateGame);
    }

    private void joinGameButton(Button_ btn) {
        JButton button = btn.geButton();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panels.get("panelCreateGame").visible(false);
                dashboard.getBox(Dashboard.SETTING).setHeight(130);
                String text = inputPort.getText();
                if (!text.isEmpty()) {
                    singleton.serverActive(false, inputPort.getText());
                    if (singleton.getGameInstance().getServerListening()) {
                        panels.get("panelText").visible(true);
                        textScreen.setText(singleton.getGameInstance().getPort());
                        panels.get("panelText").visible(true);
                        dashboard.getBox(Dashboard.SETTING).setHeight(80);
                        inputPort.setVisible(false);
                        panels.get("panelJoinGame").visible(false);
                        dashboard.getBox(Dashboard.COORDINATES).visible(true);
                        w.getFrame().setVisible(true);
                    }

                }
            }
        });
    }

    private void createGameButton(Button_ btn) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPort.setVisible(false);
                panels.get("panelCreateGame").visible(false);
                panels.get("panelJoinGame").visible(false);
                singleton.serverActive(true, "");
                textScreen.setText("Port: " + singleton.getGameInstance().getPort());
                panels.get("panelText").visible(true);
                dashboard.getBox(Dashboard.SETTING).setHeight(80);
                dashboard.getBox(Dashboard.COORDINATES).visible(true);
                w.getFrame().setVisible(true);

            }
        });
    }
}
