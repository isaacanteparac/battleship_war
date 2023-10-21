package com.iac.shipwar.UI.layout;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.FontType;
import com.iac.shipwar.models.enums.Modifier;
import com.iac.shipwar.models.enums.SubcomponentOrientation;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.Dashboard;

public class UiDashboard {
    protected Panel_ container;
    protected Map<Dashboard, Panel_> box = new HashMap<Dashboard, Panel_>();
    protected Map<Modifier, Map<Dashboard, Text_>> texts = new HashMap<Modifier, Map<Dashboard, Text_>>();
    protected final int factor = 10;
    protected final int fontSizeSubtittle = 18;
    protected final int fontSizeTitle = 20;
    protected final int boxLabelSize = 60;
    protected final String bgBox = "#000000";
    protected final String fontColor = "#FFFFFF";
    protected final String titleFontColor = "#FFFFFF";
    protected final int transparencyBox = 255;

    public UiDashboard(Panel_ p) {
        this.container = p;
        this.container.activateGrip(SubcomponentOrientation.HORIZONTAL_H);
        this.texts.put(Modifier.INMUTABLE, null);
        this.texts.put(Modifier.MUTABLE, null);

        data();
        addBox(Modifier.INMUTABLE, Dashboard.SETTING, 200);
        boxLabel(Dashboard.SCORE, this.boxLabelSize);
        addBox(Modifier.INMUTABLE, Dashboard.SHIP, 210);
        addBox(Modifier.INMUTABLE, Dashboard.ATTACK, 155);
        addBox(Modifier.INMUTABLE, Dashboard.COORDINATES, 250);
        addBox(Modifier.INMUTABLE, Dashboard.SHOOTINGLOG, 250);

        this.box.get(Dashboard.ATTACK).visible(false);
        this.box.get(Dashboard.SCORE).visible(false);
        this.box.get(Dashboard.SHOOTINGLOG).visible(false);
        this.box.get(Dashboard.COORDINATES).visible(false);

        shipColor();
    }

    public Map<Dashboard, Panel_> getComponentsBoxes() {
        return this.box;
    }

    private void boxLabel(Dashboard BoardStatus, int h) {
        addBox(Modifier.INMUTABLE, BoardStatus, h);// 75
        this.texts.get(Modifier.MUTABLE).get(BoardStatus)
                .setWidth(this.box.get(BoardStatus).getSizeWidthComponent());
        this.texts.get(Modifier.MUTABLE).get(BoardStatus).setColor(this.fontColor);

        this.box.get(BoardStatus)
                .addComponent(this.texts.get(Modifier.MUTABLE).get(BoardStatus).getLabel());
    }

    private Text_ immutableText(String text, AlingText p, int size) {
        Text_ t = new Text_(text, this.container.getSizeWidthComponent());
        t.setColor(this.titleFontColor);
        t.setSize(size);
        t.setType(FontType.BOLD);
        t.setAling(p);
        return t;
    }

    private void addBox(Modifier m, Dashboard sb, int height) {
        Panel_ panel = new Panel_(
                new PanelCharacteristic(this.container.getSizeWidthComponent(), (height + this.factor), 20,
                        this.transparencyBox, 40, 20,
                        0, 0,
                        this.bgBox, this.container.getPanel()));

        this.texts.get(Modifier.INMUTABLE).get(sb).setWidth(panel.getSizeWidthComponent());
        panel.addComponent(this.texts.get(Modifier.INMUTABLE).get(sb).getLabel());
        this.box.put(sb, panel);
        this.container.addComponent(this.box.get(sb).getPanel());
    }

    private void data() {
        this.texts.put(Modifier.INMUTABLE, new HashMap<Dashboard, Text_>() {
            {
                put(Dashboard.SETTING,
                        immutableText(Dashboard.SETTING.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
                put(Dashboard.SHIP, immutableText(Dashboard.SHIP.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
                put(Dashboard.SHOOTINGLOG,
                        immutableText(Dashboard.SHOOTINGLOG.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
                put(Dashboard.SCORE,
                        immutableText(Dashboard.SCORE.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
                put(Dashboard.ATTACK,
                        immutableText(Dashboard.ATTACK.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
                put(Dashboard.COORDINATES,
                        immutableText(Dashboard.COORDINATES.getDisplayName(), AlingText.LEFT, fontSizeSubtittle));
            }
        });

        this.texts.put(Modifier.MUTABLE, new HashMap<Dashboard, Text_>() {
            {
                put(Dashboard.SCORE, new Text_("200 de 200", container.getSizeWidthComponent()));
            }
        });
    }

    private void shipColor() {
        Ship[] ships = Ship.values();
        for (Ship ship : ships) {
            Panel_ panel = new Panel_(
                    new PanelCharacteristic(this.box.get(Dashboard.SHIP).getSizeWidthComponent(), 40, 0,
                            0, 40, 0,
                            0, 0,
                            "#FFFFFF", this.box.get(Dashboard.SHIP).getPanel()));
            Text_ t = new Text_(
                    (ship.getDisplayName() + ":" + String.valueOf(ship.getNumber())), 100);// 1000
            t.setColor(ship.getColorHex());
            panel.addComponent(t.getLabel());
            for (int i = 1; i <= ship.getSize(); i++) {
                panel.addComponent(new Panel_(new PanelCharacteristic(30, 30, 0,
                        255, 15, 0,
                        0, 0,
                        ship.getColorHex(), panel.getPanel())).getPanel());
            }
            panel.activateGrip(SubcomponentOrientation.VERTICAL_LEFT);
            this.box.get(Dashboard.SHIP).addComponent(panel.getPanel());
        }
    }

    public void addComponentBox(Component c, Dashboard sb) {
        this.box.get(sb).addComponent(c);
    }

    public Panel_ getBox(Dashboard sb) {
        return this.box.get(sb);
    }

    public void updateTextsModifier(Dashboard state, String text) {
        this.texts.get(Modifier.MUTABLE).get(state).setText(text);
    }

}
