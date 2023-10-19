package com.iac.shipwar.UI.layout;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.controllers.ShipDeployed;
import com.iac.shipwar.controllers.Singleton;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.SubcomponentOrientation;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.models.enums.VitalConditions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.iac.shipwar.models.enums.Row;

public class UiBoard {
    protected Panel_ container;
    protected String bgColor;
    protected String fontColor;
    protected Map<Row, Panel_> boxButton = new HashMap<Row, Panel_>();
    protected boolean attackbuttonAction;
    protected Map<Row, Map<Column, Button_>> rButton = new HashMap<Row, Map<Column, Button_>>();
    protected final Singleton singleton = Singleton.getInstance();

    public UiBoard(Panel_ panel, String title, String bg, String fcolor, boolean attackbuttonA) {
        this.container = panel;
        this.bgColor = bg;
        this.fontColor = fcolor;
        this.attackbuttonAction = attackbuttonA;
        title(title);

        Panel_ subContainer = new Panel_(new PanelCharacteristic(this.container.getSizeWidthComponent(), 455, 5,
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
        Panel_ box = new Panel_(new PanelCharacteristic(620, 40, 0,
                0, 0, 5,
                0, 0,
                "#000000", this.container.getPanel()));
        Map<Column, Button_> buttons = new HashMap<Column, Button_>();
        for (Column c : Column.values()) {
            Button_ b = new Button_();
            Text_ t = new Text_(row + " " + c, 35);
            t.setSize(13);
            t.setColor(this.fontColor);
            b.add(t.getLabel());
            b.setRounded(30);
            b.setColorBg(this.bgColor);
            buttons.put(c, b);
            box.addComponent(b.geButton());
            if (this.attackbuttonAction) {
                addActionButton(row, c, b.geButton());
            }
        }
        box.activateGrip(SubcomponentOrientation.HORIZONTAL_W);
        this.rButton.put(row, buttons);
        subContainer.addComponent(box.getPanel());
        return box;
    }

    private void addActionButton(Row r, Column c, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (singleton.getGameInstance().getAttackComponet()) {
                    // getContainer().visible(true);
                    ShipDeployed shipDeath = new ShipDeployed(TypeMarineElement.BOMB, r, c);
                    shipDeath.setVital(VitalConditions.DEAD);
                    singleton.getGameInstance().sendData(shipDeath);
                    if (singleton.getSuccessfulAttack()) {
                        changeColor(r, c, "#Fc045b");

                    } else {
                        changeColor(r, c, "#A29973");

                    }
                }
            }
        });
    }

    public void changeColor(Row r, Column c, String h) {
        Button_ button = this.rButton.get(r).get(c);
        button.setColorBg(h);
    }

}
