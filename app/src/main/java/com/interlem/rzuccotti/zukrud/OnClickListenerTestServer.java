package com.interlem.rzuccotti.zukrud;

import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;

/**
 * Created by rzuccotti on 12/02/2018.
 */

public class OnClickListenerTestServer implements View.OnClickListener {

    //final String serverAddress = "172.18.50.162";
    //final int port = 5002;
    //public final Socket socket = ClientThread.socket;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    ClientThread ct = new ClientThread();
    OutputStreamWriter osw;

    @Override
    public void onClick(View view) {
        try {
            new Thread(new ClientThread()).start();
            /*String str = "Papacella";
            ct.socket.getOutputStream().write(str.getBytes("US-ASCII"));
            ct.socket.close();*/
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(ct.socket.getOutputStream())), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(ct.socket.getInputStream()));
            out.println("papacella");
            String resposeFromServer = in.readLine();
            out.close();
            in.close();
            ct.socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}