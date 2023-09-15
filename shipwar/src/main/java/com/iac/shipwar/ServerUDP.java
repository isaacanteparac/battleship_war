package com.iac.shipwar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {
    static protected final int port = 6060;

    static DatagramSocket socket;
    static DatagramPacket request;
    static DatagramPacket response;
    static int clientPort;
    static InetAddress address;
    static int bufferSize = 10 * 1024 * 1024;// 10mb
    static byte[] requestbuffer = new byte[bufferSize];
    static byte[] responseBuffer = new byte[bufferSize];

    public static void main(String[] args) {

        try {
            System.out.println("server: tamo activao!");
            socket = new DatagramSocket(port);

            while (true) {
                receiveDataClient();
                response_();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void receiveDataClient() {
        request = new DatagramPacket(requestbuffer, requestbuffer.length);
        try {
            socket.receive(request);
            String dataReceived = new String(request.getData());
            clientPort = request.getPort();
            address = request.getAddress();
            System.out.println("client port: " + clientPort);
            System.out.println("> " + dataReceived);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void response_() {
        String msgServer = "SERVER msg: te envie la data que me pediste";
        responseBuffer = msgServer.getBytes();
        response = new DatagramPacket(responseBuffer, responseBuffer.length, address, clientPort);

        try {
            socket.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
