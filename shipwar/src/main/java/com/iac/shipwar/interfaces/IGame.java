package com.iac.shipwar.interfaces;

import java.net.DatagramPacket;

import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.controllers.ShipDeployed;

public interface  IGame {
    void start();
    void end();
    String getPort();
    ShipDeployed receiveData();
    DatagramPacket sendData(ShipDeployed content);
    boolean getServerListening();
    boolean getAttackComponet();
    default void addText(String t, Panel_ panelRegister){
        Text_ textAttack = new Text_(t, 250);
        panelRegister.addComponent(textAttack.getLabel());
    };

}
