package com.interlem.rzuccotti.zukrud;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by rzuccotti on 12/02/2018.
 */

class ClientThread implements Runnable {

    public static Socket socket;
    private String message;

    private static final int SERVERPORT = 5002;
    private static final String SERVER_IP = "172.18.50.107";

    ClientThread (String message_)
    {
        message = message_;
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            socket = new Socket(serverAddress, SERVERPORT);
            socket.getOutputStream().write(message.getBytes());
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}