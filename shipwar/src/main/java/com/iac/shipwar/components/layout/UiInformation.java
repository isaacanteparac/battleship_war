package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Text_;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.ElementPosition;
import com.iac.shipwar.data.enums.FontType;
import com.iac.shipwar.data.enums.Modifier;
import com.iac.shipwar.data.enums.Orientation;
import com.iac.shipwar.data.enums.StatusBoard;

public class UiInformation {
    protected Panel_ container;
    protected Map<StatusBoard, Panel_> box = new HashMap<StatusBoard, Panel_>();
    protected Map<Modifier, Map<StatusBoard, Text_>> texts = new HashMap<Modifier, Map<StatusBoard, Text_>>();

    public UiInformation(Panel_ p) {
        this.container = p;
        this.container.activateGrip(Orientation.HORIZONTAL_H);
        this.texts.put(Modifier.INMUTABLE, null);
        this.texts.put(Modifier.MUTABLE, null);

        addMap();
        this.container.addComponent(immutableText("Estado", ElementPosition.CENTER, 25).getLabel());

        addLabelContainer(Modifier.INMUTABLE, StatusBoard.SHIP, 210);


        addLabelContainer(Modifier.INMUTABLE, StatusBoard.SCORE, 75);
        this.texts.get(Modifier.MUTABLE).get(StatusBoard.SCORE)
                .setWidth(this.box.get(StatusBoard.SCORE).getSizeWidthComponent());
        this.box.get(StatusBoard.SCORE)
                .addComponent(this.texts.get(Modifier.MUTABLE).get(StatusBoard.SCORE).getLabel());


        shipColor("Pequeño", 1, "#fbffa2");
        shipColor("Mediano", 2, "#64ffc8");
        shipColor("Grande", 3, "#bba6ff");

        addLabelContainer(Modifier.INMUTABLE, StatusBoard.DESTROYED, 75);
        this.texts.get(Modifier.MUTABLE).get(StatusBoard.DESTROYED)
                .setWidth(this.box.get(StatusBoard.DESTROYED).getSizeWidthComponent());
        this.box.get(StatusBoard.DESTROYED)
                .addComponent(this.texts.get(Modifier.MUTABLE).get(StatusBoard.DESTROYED).getLabel());
        addLabelContainer(Modifier.INMUTABLE, StatusBoard.FAILED, 75);
        this.texts.get(Modifier.MUTABLE).get(StatusBoard.FAILED)
                .setWidth(this.box.get(StatusBoard.FAILED).getSizeWidthComponent());
        this.box.get(StatusBoard.FAILED)
                .addComponent(this.texts.get(Modifier.MUTABLE).get(StatusBoard.FAILED).getLabel());

    }

    private Text_ immutableText(String text, ElementPosition p, int size) {
        Text_ t = new Text_(text, this.container.getSizeWidthComponent());
        t.setColor("#0078bd");
        t.setFamily("DejaVu Sans");
        t.setSize(size);
        t.setType(FontType.BOLD);
        t.setAling(p);
        return t;
    }

    private void addLabelContainer(Modifier m, StatusBoard sb, int height) {
        Panel_ moment = new Panel_(new PanelCharacteristic(this.container.getSizeWidthComponent(), height, 20,
                255, 40, 20,
                0, 0,
                "#e2f3f8", this.container.getPanel()));
        this.texts.get(Modifier.INMUTABLE).get(sb).setWidth(moment.getSizeWidthComponent());
        moment.addComponent(this.texts.get(Modifier.INMUTABLE).get(sb).getLabel());
        this.box.put(sb, moment);
        this.container.addComponent(this.box.get(sb).getPanel());
    }

    private void addMap() {
        this.texts.put(Modifier.INMUTABLE, new HashMap<StatusBoard, Text_>() {
            {
                put(StatusBoard.SCORE, immutableText("Puntos:", ElementPosition.LEFT, 20));
                put(StatusBoard.SHIP, immutableText("Barcos:", ElementPosition.LEFT, 20));
                put(StatusBoard.DESTROYED, immutableText("Destruidos:", ElementPosition.LEFT, 20));
                put(StatusBoard.FAILED, immutableText("Fallados:", ElementPosition.LEFT, 20));
            }
        });

        this.texts.put(Modifier.MUTABLE, new HashMap<StatusBoard, Text_>() {
            {
                put(StatusBoard.SCORE, new Text_("0000", container.getSizeWidthComponent()));
                put(StatusBoard.DESTROYED, new Text_("0000", container.getSizeWidthComponent()));
                put(StatusBoard.FAILED, new Text_("0000", container.getSizeWidthComponent()));
            }
        });
    }

    private void shipColor(String text, int nBox, String color) {
        Panel_ moment = new Panel_(
                new PanelCharacteristic(this.box.get(StatusBoard.SHIP).getSizeWidthComponent(), 50, 0,
                        0, 40, 0,
                        0, 0,
                        "#FFFFFF", this.box.get(StatusBoard.SHIP).getPanel()));
        moment.addComponent(new Text_(text, 100).getLabel());
        for (int i = 1; i <= nBox; i++) {
            moment.addComponent(new Panel_(new PanelCharacteristic(40, 40, 0,
                    255, 20, 0,
                    0, 0,
                    color, moment.getPanel())).getPanel());
        }
        moment.activateGrip(Orientation.VERTICAL_LEFT);
        this.box.get(StatusBoard.SHIP).addComponent(moment.getPanel());
    }

    public void updateTextsModifier(StatusBoard state, String text) {
        this.texts.get(Modifier.MUTABLE).get(state).setText(text);
    }

}
