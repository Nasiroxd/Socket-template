package com.nasir.chowdhury.socket.batch21.aynic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PingServerAynic {
    private final int port;
    private ServerSocket server;

    public PingServerAynic(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server stated.............,, 127.0.0.1 " + port);
        listenRespondAnd(server);
    }

    public void listenRespondAnd(ServerSocket serverSocket) throws IOException {

        while (!server.isClosed()) {
           Socket socket = server.accept();
           PingThreadingAsnyc pingThreadingAsnyc = new PingThreadingAsnyc(socket);
           Thread t1 = new Thread(pingThreadingAsnyc);
           t1.start();


        }
    }

    public static void main(String[] args) throws IOException {
        PingServerAynic pingServerAynic = new PingServerAynic(6002);
        pingServerAynic.start();
    }
}