package com.iac.shipwar.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.controllers.ShipDeployed;
import com.iac.shipwar.controllers.Singleton;
import com.iac.shipwar.interfaces.IGame;
import com.iac.shipwar.models.enums.Dashboard;

//Receiver
public class CreateGame implements IGame {
    private int port;
    private int senderPort;
    private final int bufferSize = 10 * 1024 * 1024;// 10mb
    private DatagramSocket dtSocket;
    private byte[] buffer;
    private DatagramPacket dtPacket;
    private InetAddress address;
    private Boolean serverListening;
    private Boolean attackComponet = false;
    protected final Singleton singleton = Singleton.getInstance();
    private UiDashboard dashboard;

    public CreateGame(UiDashboard dashboard) throws Exception {
        this.dashboard = dashboard;
        start();
        randomPort();
        this.dtSocket = new DatagramSocket(this.port);
        System.out.println("- - - create game - - -");
        System.out.println("- - - connected - - - \nPORT: " + this.port);
        startListening();
    }

    public void startListening() {
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (serverListening) {
                    receiveData();
                }
            }
        });

        listenerThread.start();
    }

    @Override
    public ShipDeployed receiveData() {
        try {
            this.buffer = new byte[this.bufferSize];
            this.dtPacket = new DatagramPacket(this.buffer, this.bufferSize);
            this.dtSocket.receive(this.dtPacket);
            this.address = this.dtPacket.getAddress();
            this.senderPort = this.dtPacket.getPort();
            ByteArrayInputStream bis = new ByteArrayInputStream(this.buffer);
            ObjectInputStream in = new ObjectInputStream(bis);
            ShipDeployed receivedData = (ShipDeployed) in.readObject();
            this.singleton.receiveAttack(receivedData);
            this.attackComponet = true;
            String text = "Enemy: " + receivedData.getRow().name() + " | " + receivedData.getColumn().name();
            addText(text, dashboard.getBox(Dashboard.SHOOTINGLOG), "#Fc045b");
            this.dashboard.getBox(Dashboard.ATTACK).visible(this.attackComponet);
            return receivedData;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DatagramPacket sendData(ShipDeployed content) {
        try {
            if (this.address == null || this.senderPort == 0) {
                System.out.println("Error: Dirección o puerto no configurados.");
                return null;
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(content);
            this.buffer = bos.toByteArray();
            this.dtPacket = new DatagramPacket(this.buffer, this.buffer.length, this.address, this.senderPort);
            content.printDetails("MIO");
            this.dtSocket.send(dtPacket);
            this.attackComponet = false;
            String text = "     Yo: " + content.getRow().name() + " | " + content.getColumn().name();
            addText(text, dashboard.getBox(Dashboard.SHOOTINGLOG), "#045bfc");
            this.dashboard.getBox(Dashboard.ATTACK).visible(this.attackComponet);
            return dtPacket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void randomPort() {
        Random random = new Random();
        this.port = random.nextInt(6000) + 3001;
    }

    @Override
    public boolean getAttackComponet() {
        return attackComponet;
    }

    @Override
    public String getPort() {
        return "" + this.port;
    }

    @Override
    public void start() {
        this.serverListening = true;
    }

    @Override
    public void end() {
        this.serverListening = false;

    }

    @Override
    public boolean getServerListening() {
        return this.serverListening;
    }

}
