package com.iac.shipwar.controllers;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.iac.shipwar.UI.components.Attack;
import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AttackCtrl extends Attack {

    public AttackCtrl(UiBoard eb, UiDashboard ud) {
        super(eb, ud);
        fireButtonAction(fireButton.geButton(), rowOption.getComboBox(), columnOption.getComboBox());
    }

    private void fireButtonAction(JButton button, JComboBox row, JComboBox column) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Row selectedRow = (Row) row.getSelectedItem();
                Column selectedColumn = (Column) column.getSelectedItem();
                //Shipdeployed data = new Shipdeployed(selectedRow, selectedColumn);
                enemyBoard.getContainer().visible(true);

                client.sendData("Jh");
                enemyBoard.changeColor(selectedRow, selectedColumn, "#Fc045b");
                System.out.println("Fila seleccionada: " + selectedRow);
                System.out.println("Columna seleccionada: " + selectedColumn);
            }
        });
    }

}
