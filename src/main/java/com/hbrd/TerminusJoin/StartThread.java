package com.hbrd.TerminusJoin;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class StartThread extends Thread {
    public void run() {
        try {
            ServerSocket  ss =new  ServerSocket(8081);
            while(true) {
               Socket  s=ss.accept();
               new TerminusMessage(s);
            }
        }catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
}


