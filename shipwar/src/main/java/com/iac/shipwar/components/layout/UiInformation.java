package com.iac.shipwar.components.layout;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Text_;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.ShipCharacteristic;
import com.iac.shipwar.data.enums.ElementPosition;
import com.iac.shipwar.data.enums.FontType;
import com.iac.shipwar.data.enums.Modifier;
import com.iac.shipwar.data.enums.Orientation;
import com.iac.shipwar.data.enums.ShipSize;
import com.iac.shipwar.data.enums.StatusBoard;

public class UiInformation {
    protected Panel_ container;
    protected Map<StatusBoard, Panel_> box = new HashMap<StatusBoard, Panel_>();
    protected Map<Modifier, Map<StatusBoard, Text_>> texts = new HashMap<Modifier, Map<StatusBoard, Text_>>();
    protected Map<ShipSize, ShipCharacteristic> shipsCharacteristic;
    protected final int factor = 10;
    protected final int fontSizeSubtittle = 18;
    protected final int fontSizeTitle = 20;
    protected final int boxLabelSize = 60;
    protected final String bgBox = "#FFFFFF";
    protected final String titleFontColor = "#2d2e2f";
    protected final int transparencyBox = 255;

    public UiInformation(Panel_ p, Map<ShipSize, ShipCharacteristic> sc) {
        this.container = p;
        this.container.activateGrip(Orientation.HORIZONTAL_H);
        this.shipsCharacteristic = sc;
        this.texts.put(Modifier.INMUTABLE, null);
        this.texts.put(Modifier.MUTABLE, null);

        data();
        //this.container.addComponent(immutableText("Estado", ElementPosition.CENTER,
        //this.fontSizeTitle).getLabel());

        //boxLabel(StatusBoard.SCORE, this.boxLabelSize);
        addBox(Modifier.INMUTABLE, StatusBoard.SHIP, 200);

        addBox(Modifier.INMUTABLE, StatusBoard.ATTACK, 155);
        boxLabel(StatusBoard.DESTROYED, this.boxLabelSize);
        boxLabel(StatusBoard.FAILED, this.boxLabelSize);
        addBox(Modifier.INMUTABLE, StatusBoard.ROUTE, 115);

        shipColor();
    }

    private void boxLabel(StatusBoard statusBoard, int h) {
        addBox(Modifier.INMUTABLE, statusBoard, h);// 75
        this.texts.get(Modifier.MUTABLE).get(statusBoard)
                .setWidth(this.box.get(statusBoard).getSizeWidthComponent());
        this.texts.get(Modifier.MUTABLE).get(statusBoard).setColor(this.titleFontColor);

        this.box.get(statusBoard)
                .addComponent(this.texts.get(Modifier.MUTABLE).get(statusBoard).getLabel());
    }

    private Text_ immutableText(String text, ElementPosition p, int size) {
        Text_ t = new Text_(text, this.container.getSizeWidthComponent());
        t.setColor("#A85727");
        t.setSize(size);
        t.setType(FontType.BOLD);
        t.setAling(p);
        return t;
    }

    private void addBox(Modifier m, StatusBoard sb, int height) {
        Panel_ moment = new Panel_(
                new PanelCharacteristic(this.container.getSizeWidthComponent(), (height + this.factor), 20,
                        this.transparencyBox, 40, 20,
                        0, 0,
                        this.bgBox, this.container.getPanel()));

        this.texts.get(Modifier.INMUTABLE).get(sb).setWidth(moment.getSizeWidthComponent());
        moment.addComponent(this.texts.get(Modifier.INMUTABLE).get(sb).getLabel());
        this.box.put(sb, moment);
        this.container.addComponent(this.box.get(sb).getPanel());
    }

    private void data() {
        this.texts.put(Modifier.INMUTABLE, new HashMap<StatusBoard, Text_>() {
            {
                put(StatusBoard.SHIP, immutableText("Barcos:", ElementPosition.LEFT, fontSizeSubtittle));
                put(StatusBoard.DESTROYED, immutableText("Destruidos:", ElementPosition.LEFT, fontSizeSubtittle));
                put(StatusBoard.FAILED, immutableText("Fallados:", ElementPosition.LEFT, fontSizeSubtittle));
                put(StatusBoard.ATTACK, immutableText("Atacar:", ElementPosition.LEFT, fontSizeSubtittle));
                put(StatusBoard.ROUTE, immutableText("Url:", ElementPosition.LEFT, fontSizeSubtittle));

            }
        });

        this.texts.put(Modifier.MUTABLE, new HashMap<StatusBoard, Text_>() {
            {
                put(StatusBoard.DESTROYED, new Text_("0000", container.getSizeWidthComponent()));
                put(StatusBoard.FAILED, new Text_("0000", container.getSizeWidthComponent()));
            }
        });
    }

    private void shipColor() {
        for (Map.Entry<ShipSize, ShipCharacteristic> element : this.shipsCharacteristic.entrySet()) {
            ShipCharacteristic characteristic = element.getValue();
            Panel_ moment = new Panel_(
                    new PanelCharacteristic(this.box.get(StatusBoard.SHIP).getSizeWidthComponent(), 50, 0,
                            0, 40, 0,
                            0, 0,
                            "#FFFFFF", this.box.get(StatusBoard.SHIP).getPanel()));
            Text_ t = new Text_((characteristic.screenName()+"-"+String.valueOf(characteristic.number_())), 100);//1000
            t.setColor(this.titleFontColor);
            moment.addComponent(t.getLabel());
            for (int i = 1; i <= characteristic.size(); i++) {
                moment.addComponent(new Panel_(new PanelCharacteristic(40, 40, 0,
                        255, 20, 0,
                        0, 0,
                        characteristic.color(), moment.getPanel())).getPanel());
            }
            moment.activateGrip(Orientation.VERTICAL_LEFT);
            this.box.get(StatusBoard.SHIP).addComponent(moment.getPanel());
        }
    }

    public void addComponentBox(Component c, StatusBoard sb) {
        this.box.get(sb).addComponent(c);
    }

    public Panel_ getBox(StatusBoard sb) {
        return this.box.get(sb);
    }

    public void updateTextsModifier(StatusBoard state, String text) {
        this.texts.get(Modifier.MUTABLE).get(state).setText(text);
    }

}
