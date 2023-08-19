package com.nasir.chowdhury.socket.batch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSever {
    private  final int port;

    public TCPSever(int port){
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server stated.........");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connection");

        BufferedReader request = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter respond = new PrintWriter(clientSocket.getOutputStream(),true);

        String line  = request.readLine();
        while (!line.isEmpty()){
            System.out.println("From client : " + line);
            respond.println("From server : "+ line);
            line = request.readLine();
        }



    }

    public static void main(String[] args) throws IOException {
        TCPSever tcpSever = new TCPSever(6001);
        tcpSever.start();
    }
}
