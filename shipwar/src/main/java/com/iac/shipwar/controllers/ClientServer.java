package com.iac.shipwar.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ClientServer {

    private InetAddress address;
    private int serverPort = 6060;
    private DatagramSocket socket;
    private byte[] requestData = new byte[10 * 1024 * 1024];
    private DatagramPacket response;
    private DatagramPacket request;

    public ClientServer() {
        initialized();
        startListening();

    }

    private void initialized() {
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName("localhost");
            System.out.println("mi puerto: " + this.socket.getPort());
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void sendData(Serializable record) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(record);

            byte[] data = byteArrayOutputStream.toByteArray();
            this.response = new DatagramPacket(data, data.length, this.address, this.serverPort);
            this.socket.send(this.response);

            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveData() {
        this.request = new DatagramPacket(this.requestData, this.requestData.length, this.address, this.serverPort);
        try {
            this.socket.receive(request);
            System.out.println(new String(this.request.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    receiveData();
                }
            }
        });

        listenerThread.start();
    }
}
