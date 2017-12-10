/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package com.blockchain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Rumyana
 */
public class MessageSenderRunnable implements Runnable{

    private ServerInfo destServer;
    private String message;

    public MessageSenderRunnable(ServerInfo destServer, String message) {
        this.destServer = destServer;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            // create socket with a timeout of 2 seconds
            Socket s = new Socket();
            s.connect(new InetSocketAddress(this.destServer.getHost(), this.destServer.getPort()), 10000);
            PrintWriter pw =  new PrintWriter(s.getOutputStream(), true);
            
            System.out.println("sending " + message);
            // send the message forward
        	pw.println(message);
        	pw.flush();

            // close printWriter and socket
            pw.close();
            s.close();
            
        } catch (IOException e) {
        }
    }
}
