package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import com.iac.shipwar.components.Button_;
import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Text_;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.Column;
import com.iac.shipwar.data.enums.ElementPosition;
import com.iac.shipwar.data.enums.Orientation;
import com.iac.shipwar.data.enums.Row;

public class UiBoard {
    protected Panel_ container;
    protected String colorHex;
    protected Map<Row, Panel_> boxButton = new HashMap<Row, Panel_>();
    protected Map<Row, Map<Column, Button_>> rButton = new HashMap<Row, Map<Column, Button_>>();

    public UiBoard(Panel_ panel, String title, String hex) {
        this.container = panel;
        this.colorHex = hex;
        

        title(title);
        createButton(Row.R1);
        createButton(Row.R2);
        createButton(Row.R3);
        createButton(Row.R4);
        createButton(Row.R5);
        createButton(Row.R6);
        createButton(Row.R7);
        createButton(Row.R8);
    }

    private void title(String text){
        Text_ t = new Text_(text, this.container.getSizeWidthComponent());
        //t.setColor("#FFFFFF");
        t.setSize(30);
        t.setAling(ElementPosition.CENTER);
                this.container.addComponent(t.getLabel());

    }

    private void createButton(Row row) {
        Panel_ box = new Panel_(new PanelCharacteristic(this.container.getSizeWidthComponent(), 80, 0,
                0, 0, 10,
                0, 0,
                "#000000", this.container.getPanel()));
        Map<Column, Button_> buttons = new HashMap<Column, Button_>();
        for (Column c : Column.values()) {
            Button_ b = new Button_();
            Text_ t = new Text_(row+":" + c, 40);
            t.setColor("#003366");
            b.add(t.getLabel());
            b.setColorBg(this.colorHex);
            buttons.put(c, b);
            box.addComponent(b.geButton());
        }
        box.activateGrip(Orientation.HORIZONTAL_W);
        this.rButton.put(row, buttons);
        this.container.addComponent(box.getPanel());
    }

}
