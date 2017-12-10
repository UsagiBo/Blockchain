/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockchain;

/**
 *
 * @author Rumyana
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PeriodicPulseRunnable implements Runnable {

    private HashMap<ServerInfo, Date> serverStatus;
    private int sequenceNumber;
    private int localPort;

    public PeriodicPulseRunnable(HashMap<ServerInfo, Date> serverStatus, int localPort) {
        this.serverStatus = serverStatus;
        this.sequenceNumber = 0;
        this.localPort = localPort;
        
    }

    @Override
    public void run() {
    	String message;
        while(true) {
            // broadcast pulse message to all peers
            message = "pulse|" + String.valueOf(localPort) + "|" + String.valueOf(sequenceNumber);

            for (ServerInfo info : serverStatus.keySet()) {
                Thread thread = new Thread(new PulseSenderRunnable(info, message));
                thread.start();
            }

            // increment the sequenceNumber
            sequenceNumber += 1;
            
            // sleep for two seconds
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }
}

