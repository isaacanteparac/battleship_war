package com.iac.shipwar.components.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.iac.shipwar.components.Button_;
import com.iac.shipwar.components.Options_;
import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Text_;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.Column;
import com.iac.shipwar.data.enums.Orientation;
import com.iac.shipwar.data.enums.Row;

import java.awt.event.ActionListener;

public class UiInput {

    protected Panel_ container;
    protected String route;
    protected UiBoard enemyBoard;

    public UiInput(UiBoard eb) {
        this.enemyBoard = eb;
    }

    public void mountComponentRoute(Panel_ pRoute) {
        JTextField inputRoute = new JTextField();
        inputRoute.setPreferredSize(new Dimension(pRoute.getSizeWidthComponent(), 30));
        Button_ b = new Button_();
        Text_ t = new Text_("Conectar", 90);
        b.setWidth(100);
        b.setHeight(40);
        b.setColorBg("#000000");
        t.setColor("#FFFFFF");
        b.add(t.getLabel());
        actionButtonRoute(b.geButton(), inputRoute);
        pRoute.addComponent(inputRoute);
        pRoute.addComponent(b.geButton());
    }

    private void actionButtonRoute(JButton b, JTextField input) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route = input.getText();
                System.out.println("Texto ingresado: " + route);
                // hacer una alerta que fue conectado correctamente al server
            }
        });
    }

    public void mountComponentAttack(Panel_ boxAttack) {
        Panel_ panelRow = createMomentaryPanels(boxAttack, 35);
        Panel_ panelColumn = createMomentaryPanels(boxAttack, 35);
        Options_ rowOpt = new Options_<>(Row.class);
        panelRow.addComponent(new Text_("Fila", 100).getLabel());
        panelRow.addComponent(rowOpt.getComboBox());
        panelRow.activateGrip(Orientation.CENTER);

        Options_ columnOpt = new Options_<>(Column.class);
        panelColumn.addComponent(new Text_("Columna", 100).getLabel());
        panelColumn.addComponent(columnOpt.getComboBox());
        panelColumn.activateGrip(Orientation.CENTER);

        Button_ b = new Button_();
        Text_ t = new Text_("\u1F52 Fuego \u1F52", boxAttack.getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg("#Fc045b");
        t.setColor("#FFFFFF");

        actionButtonAttack(b.geButton(), rowOpt.getComboBox(), columnOpt.getComboBox());

        boxAttack.addComponent(panelRow.getPanel());
        boxAttack.addComponent(panelColumn.getPanel());
        boxAttack.addComponent(b.geButton());

    }

    private Panel_ createMomentaryPanels(Panel_ box, int h) {
        return new Panel_(
                new PanelCharacteristic(box.getSizeWidthComponent(), h, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", box.getPanel()));
    }

    private void actionButtonAttack(JButton button, JComboBox row, JComboBox column) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección de los JComboBox
                Row selectedRow = (Row) row.getSelectedItem();
                Column selectedColumn = (Column) column.getSelectedItem();
                enemyBoard.changeColor(selectedRow, selectedColumn, "#Fc045b");
                // Hacer algo con los elementos seleccionados
                System.out.println("Fila seleccionada: " + selectedRow);
                System.out.println("Columna seleccionada: " + selectedColumn);
            }
        });
    }

}
