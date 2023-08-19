package com.nasir.chowdhury.socket.batch21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PingServer {
    private final int port;
    private int count;
    private ServerSocket server;

    public PingServer(int port){
        this.port = port;

    }
    public void start() throws IOException {
        server = new ServerSocket(port);
        System.out.println("Server stated............."+ port);
        listenRespondAnd(server);



    }
    public void listenRespondAnd(ServerSocket serverSocket) throws IOException {
        Socket client = null;
        BufferedReader request = null;
        PrintWriter respond = null;

       try{
           while (!server.isClosed()){
               client = server.accept();
               System.out.println(++count+"Client connection");
               request = new BufferedReader(new InputStreamReader(client.getInputStream()));
               respond = new PrintWriter(client.getOutputStream(),true);

               String inputLine;
               while ((inputLine = request.readLine())!=null){
                   System.out.println("From client : "+ inputLine);
                   respond.println("From server : " + inputLine.toUpperCase());

               }
           }

       }catch (SocketException e){
           relsenRespondAndSomoy(client,request,respond);
           System.out.println("..........................");
           listenRespondAnd(serverSocket);

       }





    }

    private  void relsenRespondAndSomoy(Socket client, BufferedReader request, PrintWriter respond) throws IOException {
        count--;
        System.out.println("Client Disconnection");
        if(request != null)request.close();
        if (respond !=null) respond.close();
        if (client != null) client.close();

    }

    public static void main(String[] args) throws IOException {
        PingServer pingServer = new PingServer(6002);
        pingServer.start();
    }

}
