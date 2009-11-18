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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petah
 */
public class Client {

    private static int port = 4444;
    private static String hostName = "localhost";
    private static Socket socket;
    private static PrintWriter printWriter;

    public static void connect() {
        try {
            socket = new Socket(hostName, port);
            new InputListener().start();
            printWriter = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to " + hostName + ".");
            sendMessage("Client");
            new Thread() {

                public void run() {
                    while (true) {
                        try {
                            sendMessage("Time: " + System.currentTimeMillis());
                            sleep(500);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (UnknownHostException ex) {
            System.err.println("Could not connect to " + hostName + ", unknown host.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Couldn't get I/O for the connection to: " + hostName + ".");
            ex.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        printWriter.write(0);
        printWriter.println(message);
        printWriter.flush();
    }

    private static class InputListener extends Thread {

        private BufferedReader bufferedReader;

        public InputListener() {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Client waiting for input.");
            while (true) {
                try {
                    switch (bufferedReader.read()) {
                        case 0:
                            System.out.println("Server message: " + bufferedReader.readLine());
                            break;
                        default:
                            System.out.println("Server sent uknown message.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
//
//    public static void main(String[] args) throws IOException {
//
//        Socket echoSocket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//
//        try {
//            echoSocket = new Socket("localhost", 4444);
//            out = new PrintWriter(echoSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(
//                    echoSocket.getInputStream()));
//        } catch (UnknownHostException e) {
//            System.err.println("Don't know about host: localhost.");
//            System.exit(1);
//        } catch (IOException e) {
//            System.err.println("Couldn't get I/O for " + "the connection to: localhost.");
//            System.exit(1);
//        }
//
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//        String userInput;
//
//        while ((userInput = stdIn.readLine()) != null) {
//            out.println(userInput);
//            System.out.println("echo: " + in.readLine());
//        }
//
//        out.close();
//        in.close();
//        stdIn.close();
//        echoSocket.close();
//    }
}
