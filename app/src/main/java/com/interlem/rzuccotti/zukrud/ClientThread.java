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

    private static final int SERVERPORT = 5002;
    private static final String SERVER_IP = "172.18.50.162";

    @Override
    public void run() {

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            socket = new Socket(serverAddr, SERVERPORT);
            socket.getOutputStream().write("papacella".getBytes());

        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}