package com.iac.shipwar.controllers;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.iac.shipwar.components.layout.UiBoard;
import com.iac.shipwar.components.layout.UiDashboard;
import com.iac.shipwar.components.widgets.Button_;
import com.iac.shipwar.components.widgets.Options_;
import com.iac.shipwar.components.widgets.Panel_;
import com.iac.shipwar.components.widgets.Text_;
import com.iac.shipwar.models.dataclass.AttackCoordinates;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Attack {

    protected Panel_ container;
    protected String route;
    protected UiBoard enemyBoard;
    protected ClientServer client;
    protected UiDashboard dashboard;

    public Attack(UiBoard eb, UiDashboard ud) {
        this.enemyBoard = eb;
        this.dashboard = ud;
        this.client = new ClientServer();
        assembleComponent();
    }

    public void assembleComponent() {
        Panel_ panelRow = createDefaultPanel(this.dashboard.getBox(Dashboard.ATTACK), 35);
        Panel_ panelColumn = createDefaultPanel(this.dashboard.getBox(Dashboard.ATTACK), 35);

        final Options_<Row> rowOpt = new Options_<>();
        final Options_<Column> columnOpt = new Options_<>();
        rowOpt.pureEnum(Row.class);
        columnOpt.pureEnum(Column.class);

        panelRow.addComponent(new Text_("Fila", 100).getLabel());
        panelColumn.addComponent(new Text_("Columna", 100).getLabel());
        rowOpt.addPanel(panelRow);
        columnOpt.addPanel(panelColumn);

        Button_ b = new Button_();
        Text_ t = new Text_("\u1F52 Fuego \u1F52", this.dashboard.getBox(Dashboard.ATTACK).getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg("#Fc045b");
        t.setColor("#FFFFFF");

        fireButtonAction(b.geButton(), rowOpt.getComboBox(), columnOpt.getComboBox());

        this.dashboard.getBox(Dashboard.ATTACK).addComponent(panelRow.getPanel());
        this.dashboard.getBox(Dashboard.ATTACK).addComponent(panelColumn.getPanel());
        this.dashboard.getBox(Dashboard.ATTACK).addComponent(b.geButton());

    }

    private void fireButtonAction(JButton button, JComboBox row, JComboBox column) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección de los JComboBox
                Row selectedRow = (Row) row.getSelectedItem();
                Column selectedColumn = (Column) column.getSelectedItem();
                AttackCoordinates data = new AttackCoordinates(selectedRow, selectedColumn);
                enemyBoard.getContainer().visible(true);

                client.sendData("Jh");
                enemyBoard.changeColor(selectedRow, selectedColumn, "#Fc045b");
                // Hacer algo con los elementos seleccionados
                System.out.println("Fila seleccionada: " + selectedRow);
                System.out.println("Columna seleccionada: " + selectedColumn);
            }
        });
    }

    private Panel_ createDefaultPanel(Panel_ box, int h) {
        return new Panel_(
                new PanelCharacteristic(box.getSizeWidthComponent(), h, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", box.getPanel()));
    }

}
