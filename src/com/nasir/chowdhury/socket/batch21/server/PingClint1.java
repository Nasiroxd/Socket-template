package com.nasir.chowdhury.socket.batch21.server;

import com.nasir.chowdhury.socket.batch21.PingClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PingClint1 {
    private Socket clientSocket;
    private PrintWriter request;
    private BufferedReader respond;

    public void connection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip,port);
        respond = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        request = new PrintWriter(clientSocket.getOutputStream(),true);
    }

    public String sendMessage(String msg) throws IOException {
        request.println(msg);
        String ReturnSendmsg = respond.readLine();
        return ReturnSendmsg;
    }

    public static void main(String[] args) throws IOException {
        PingClint1 pingClint1 = new PingClint1();
        pingClint1.connection("127.0.0.1",6002);
        Scanner input = new Scanner(System.in);
        while (true){
            String line = input.nextLine();
            if (line.equals("bye"))
                break;
            System.out.println( pingClint1.sendMessage(line));
        }
    }


}


