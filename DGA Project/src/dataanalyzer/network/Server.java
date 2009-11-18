/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package dataanalyzer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petah
 */
public class Server {

    private static int port = 4444;
    private static ServerSocket serverSocket;
    private static ArrayList<Node> nodes = new ArrayList<Node>();

    public static void listen() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port: " + port + ".");
        } catch (IOException ex) {
            System.err.println("Server failed to listen on port: " + port + ".");
            ex.printStackTrace();
        }
        new ConnectionListener().start();
    }

    private static class ConnectionListener extends Thread {

        public void run() {
            while (true) {
                try {
                    nodes.add(new Node(serverSocket.accept()));
                    System.out.println("Server accepted incoming connection.");
                } catch (IOException ex) {
                    System.err.println("Server failed to accept incoming connection.");
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server.listen();
        Client.connect();
//
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(4444);
//        } catch (IOException e) {
//            System.err.println("Could not listen on port: 4444.");
//            System.exit(1);
//        }
//
//        Socket clientSocket = null;
//        try {
//            clientSocket = serverSocket.accept();
//        } catch (IOException e) {
//            System.err.println("Accept failed.");
//            System.exit(1);
//        }
//
//        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(
//                clientSocket.getInputStream()));
//        String inputLine, outputLine;
////        KnockKnockProtocol kkp = new KnockKnockProtocol();
////
//        outputLine = "Hello sockets!";
//        out.println(outputLine);
////
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
////             outputLine = kkp.processInput(inputLine);
////             out.println(outputLine);
////             if (outputLine.equals("Bye."))
////                break;
//        }
//        out.close();
//        in.close();
//        clientSocket.close();
//        serverSocket.close();
    }
}