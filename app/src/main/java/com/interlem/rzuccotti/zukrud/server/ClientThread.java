package com.interlem.rzuccotti.zukrud.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by rzuccotti on 12/02/2018.
 */

public class ClientThread implements Runnable {

    public static Socket socket;
    private String message;
    BufferedReader bufferedReader;
    StringBuilder responseString = new StringBuilder();

    private static final int SERVERPORT = 5002;
    private static final String SERVER_IP = "172.18.50.107";

    public ClientThread (String message_)
    {
        message = message_;
    }

    @Override
    public void run() {
        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            socket = new Socket(serverAddress, SERVERPORT);
            socket.getOutputStream().write(message.getBytes());

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                responseString.append(str);
            }

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}