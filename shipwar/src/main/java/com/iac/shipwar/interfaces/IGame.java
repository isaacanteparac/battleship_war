package com.iac.shipwar.interfaces;

import java.net.DatagramPacket;
import com.iac.shipwar.controllers.ShipDeployed;

public interface  IGame {
    void start();
    void end();
    String getPort();
    ShipDeployed receiveData();
    DatagramPacket sendData(ShipDeployed content);
    boolean getServerListening();
    boolean getAttackComponet();

}
