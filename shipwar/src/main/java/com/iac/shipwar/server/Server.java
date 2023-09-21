package com.iac.shipwar.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static protected final int port = 6060;

    static DatagramSocket socket;
    static DatagramPacket request;
    static DatagramPacket response;
    static int clientPort;
    static InetAddress address;
    static int bufferSize = 10 * 1024 * 1024;// 10mb
    static byte[] requestbuffer = new byte[bufferSize];
    static byte[] responseBuffer = new byte[bufferSize];

    /*
     * public Server() throws Exception {
     * 
     * ServerSocket server_socket = new ServerSocket(2020);
     * System.out.println("Port 2020 is now open.");
     * 
     * // infinite while loop: wait for new connections
     * while (true) {
     * Socket socket = server_socket.accept();
     * Thread_ server_thread = new Thread_(socket);
     * Thread_ thread = new Thread_(server_thread);
     * thread.start();
     * }
     * }
     * 
     * public static void main(String[] args) {
     * try {
     * new Server();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     */
    /*
     * public static void main(String[] args) {
     * 
     * try {
     * System.out.println("server: tamo activao!");
     * socket = new DatagramSocket(port);
     * 
     * while (true) {
     * receiveDataClient();
     * response_();
     * }
     * 
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * 
     * private static void receiveDataClient() {
     * request = new DatagramPacket(requestbuffer, requestbuffer.length);
     * try {
     * socket.receive(request);
     * String dataReceived = new String(request.getData());
     * clientPort = request.getPort();
     * address = request.getAddress();
     * System.out.println("client port: " + clientPort);
     * System.out.println("> " + dataReceived);
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * 
     * private static void response_() {
     * String msgServer = "SERVER msg: te envie la data que me pediste";
     * responseBuffer = msgServer.getBytes();
     * response = new DatagramPacket(responseBuffer, responseBuffer.length, address,
     * clientPort);
     * 
     * try {
     * socket.send(response);
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * 
     */
}
