package com.iac.shipwar.UI.components;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.Dashboard;

public class Setting {
    protected UiDashboard dashboard;
    protected UiBoard myBoard;
    protected Button_ btnCreateGame;
    protected Button_ btnJoinGame;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected Text_ textScreen;
    protected JTextField inputPort;
    protected final String fontColor = "#A0A0A0A";


    public Setting(UiDashboard ud, UiBoard myBoard) {
        this.myBoard = myBoard;
        this.dashboard = ud;
        assembleComponent();
    }

    private void assembleComponent() {
        this.textScreen = createText("6565", "panelText", fontColor);
        this.inputPort = new JTextField();
        this.inputPort
                .setPreferredSize(new Dimension(this.dashboard.getBox(Dashboard.SETTING).getSizeWidthComponent(), 30));
        this.dashboard.getBox(Dashboard.SETTING).addComponent(this.inputPort);

        this.btnJoinGame = createButton("Unirse", "panelJoinGame", "#facd87");
        this.btnCreateGame = createButton("Crear", "panelCreateGame", "#87b4fa");
        this.panels.get("panelText").visible(false);
    }

    private Text_ createText(String content, String id, String colorHex) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.SETTING), 30);
        Text_ text = new Text_(content,
                this.dashboard.getBox(Dashboard.SETTING).getSizeWidthComponent());
        text.setAling(AlingText.CENTER);
        text.setColor(colorHex);
        panel.addComponent(text.getLabel());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.SETTING).addComponent(panel.getPanel());
        return text;
    }

    private Button_ createButton(String Text, String id, String colorHex) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.SETTING), 55);
        Button_ b = new Button_();
        Text_ t = new Text_(Text, this.dashboard.getBox(Dashboard.SETTING).getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg(colorHex);
        t.setColor("#000000");
        panel.addComponent(b.geButton());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.SETTING).addComponent(panel.getPanel());
        return b;
    }

    private Panel_ createDefaultPanel(Panel_ box, int h) {
        return new Panel_(
                new PanelCharacteristic(box.getSizeWidthComponent(), h, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", box.getPanel()));
    }
}
