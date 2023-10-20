package com.iac.shipwar.interfaces;

import java.net.DatagramPacket;

import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.controllers.ShipDeployed;
import com.iac.shipwar.models.enums.AlingText;

public interface  IGame {
    void start();
    void end();
    String getPort();
    ShipDeployed receiveData();
    DatagramPacket sendData(ShipDeployed content);
    boolean getServerListening();
    boolean getAttackComponet();
    default void addText(String t, Panel_ panelRegister, String color){
        Text_ textAttack = new Text_(t, panelRegister.getSizeWidthComponent());
        textAttack.setColor(color);
        textAttack.setAling(AlingText.LEFT);
        panelRegister.addComponent(textAttack.getLabel());
    };

}
