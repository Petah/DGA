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

/**
 *
 * @author Petah
 */
public class Node {

    private Socket socket;
    private PrintWriter printWriter;

    public Node(Socket socket) {
        this.socket = socket;
        new InputListener().start();
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
            sendMessage("Node");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        printWriter.write(0);
        printWriter.println(message);
        printWriter.flush();
    }

    private class InputListener extends Thread {

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
            System.out.println("Node waiting for input.");
            while (true) {
                try {
                    switch (bufferedReader.read()) {
                        case 0:
                            System.out.println("Client message: " + bufferedReader.readLine());
                            break;
                        default:
                            System.out.println("Client sent uknown message.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}