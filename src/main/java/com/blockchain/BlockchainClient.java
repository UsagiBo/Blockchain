/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blockchain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rumyana
 */
public class BlockchainClient {

    public static void main(String[] args) {
        if (args.length != 3) {
            return;
        }

        int localPort = 0;
        int remotePort = 0;
        String remoteHost = null;

        try {
            localPort = Integer.parseInt(args[0]);
            remoteHost = args[1];
            remotePort = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return;
        }
        
        try {
            ServerInfo serv = new ServerInfo(remoteHost, remotePort);
            Scanner sc = new Scanner(System.in);
            while (true) {
            	String message = sc.nextLine();
            	new Thread(new MessageSenderRunnable(serv, message)).start();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}

