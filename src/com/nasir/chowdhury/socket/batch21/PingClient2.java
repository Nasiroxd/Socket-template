package com.nasir.chowdhury.socket.batch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PingClient2 {
    private Socket cliientSocket;

    public void connection(String ip , int port) throws IOException {
        this.cliientSocket = new Socket(ip,port);

    }

    public void  start(String msg) throws IOException {
        PrintWriter request = new PrintWriter(cliientSocket.getOutputStream(),true);
        request.println(msg);
        BufferedReader respond = new BufferedReader(
                new InputStreamReader(cliientSocket.getInputStream())

        );
        String line = respond.readLine();
        System.out.println(line);
    }

    public static void main(String[] args) throws IOException {
        PingClient2 pingClient2 = new PingClient2();
        pingClient2.connection("127.0.0.1",6002);

        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if (line.equals("bye"))
                break;
            pingClient2.start(line);
        }

    }
}
