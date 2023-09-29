package com.iac.shipwar.controllers;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.iac.shipwar.UI.components.Attack;
import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.models.enums.VitalConditions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AttackCtrl extends Attack {

    protected final Singleton singleton = Singleton.getInstance();

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
                enemyBoard.getContainer().visible(true);
                enemyBoard.changeColor(selectedRow, selectedColumn, "#e5ddb9");
                burningShot(selectedRow, selectedColumn);
            }
        });
    }

    /*
     * ShipDeployed receiveData();
     * DatagramPacket sendData(ShipDeployed content);
     */
    private void burningShot(Row row, Column column) {
        ShipDeployed shipDeath = new ShipDeployed(TypeMarineElement.BOMB, row, column);
        shipDeath.setVital(VitalConditions.DEAD);
        singleton.getGameInstance().sendData(shipDeath);
    }

}
