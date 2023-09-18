package com.iac.shipwar.UI.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.SubcomponentOrientation;
import com.iac.shipwar.models.enums.Row;

public class UiBoard {
    protected Panel_ container;
    protected String bgColor;
    protected String fontColor;
    protected Map<Row, Panel_> boxButton = new HashMap<Row, Panel_>();
    protected Map<Row, Map<Column, Button_>> rButton = new HashMap<Row, Map<Column, Button_>>();

    public UiBoard(Panel_ panel, String title, String bg, String fcolor) {
        this.container = panel;
        this.bgColor = bg;
        this.fontColor = fcolor;

        title(title);

         Panel_ subContainer = new Panel_(new PanelCharacteristic(this.container.getSizeWidthComponent(), 490,5,
                0, 50, 5,
                0, 0,
                "#FFFFFF", this.container.getPanel()));
        subContainer.activateGrip(SubcomponentOrientation.VERTICAL);
        for (Row row : Row.values()) {
            createButton(row, subContainer);
        }

        this.container.addComponent(subContainer.getPanel());
    }

    public Panel_ getContainer() {
        return this.container;
    }

    private void title(String text) {
        Text_ t = new Text_(text, this.container.getSizeWidthComponent());
        t.setSize(25);
        t.setAling(AlingText.CENTER);
        t.setColor("#FFFFFF");
        this.container.addComponent(t.getLabel());

    }

    private Panel_ createButton(Row row, Panel_ subContainer) {
        Panel_ box = new Panel_(new PanelCharacteristic(subContainer.getSizeWidthComponent(), 55, 0,
                0, 0, 5,
                0, 0,
                "#000000", this.container.getPanel()));
        Map<Column, Button_> buttons = new HashMap<Column, Button_>();
        for (Column c : Column.values()) {
            Button_ b = new Button_();
            Text_ t = new Text_(row + ":" + c, 35);
            t.setSize(16);
            t.setColor(this.fontColor);
            b.add(t.getLabel());
            b.setRounded(30);
            b.setColorBg(this.bgColor);
            buttons.put(c, b);
            box.addComponent(b.geButton());
        }
        box.activateGrip(SubcomponentOrientation.HORIZONTAL_W);
        this.rButton.put(row, buttons);
        subContainer.addComponent(box.getPanel());
        return box;
    }

    public void changeColor(Row r, Column c, String h) {
        Button_ button = this.rButton.get(r).get(c);
        button.setColorBg(h);
    }

}
