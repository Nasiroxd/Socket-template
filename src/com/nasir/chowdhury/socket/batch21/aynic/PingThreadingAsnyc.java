package com.nasir.chowdhury.socket.batch21.aynic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class PingThreadingAsnyc implements Runnable {
    private Socket clientSocket;

    public PingThreadingAsnyc(Socket clientSocket){
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try{


            BufferedReader    request = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter respond = new PrintWriter(clientSocket.getOutputStream(),true);

                String inputLine;
                while ((inputLine = request.readLine())!=null){
                    System.out.println("Client Respond : " +  inputLine.toUpperCase());
                    respond.println(Thread.currentThread().getName() + " Respond  : " + inputLine.toUpperCase());

                }


        }catch (IOException e){
            System.out.println("Client Disconnection");
            System.out.println("..........................");


        }
    }
}
