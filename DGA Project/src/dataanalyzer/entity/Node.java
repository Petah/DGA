package dataanalyzer.entity;

import dataanalyzer.DGA;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;
import net.sf.lipermi.net.Client;

public class Node {

    private DGA system;
    private String hostName;
    private int port;
    private LipeServerInterface server;
    private Client client;
    private long id;

    public Node(DGA system, String hostName, int port, LipeServerInterface server, Client client, long id) {
        this.system = system;
        this.hostName = hostName;
        this.port = port;
        this.server = server;
        this.client = client;
        this.id = id;
    }

    @Override
    public String toString() {
        return hostName + ":" + port;
    }

    // FIXME: Program freezes when runing this method if the server and client are run seperate
    public void loadProcess(AlgorithmProcess process) {
        if (process instanceof Serializable) {
            server.loadProcess(process);
        } else {
            system.userInterface.handleError("Process (" + process + ") is not serializable.");
        }
    }

    public void loadOutput(Output o) {
        server.loadOutput(o);
    }

    public void removeProcess(long id) {
        switch (server.removeProcess(id)) {
            case processNotFound:
                system.userInterface.out("Client -> Process not found.");
        }
    }

    public void runProcess(long id) {
        switch (server.runProcess(id)) {
            case processNotFound:
                system.userInterface.out("Client -> Process not found.");
        }
    }
    // FIXME: fix the disconnecting 
    public void disconnect() {
        try {
            client.close();
        } catch (IOException ex) {
            system.userInterface.handleError(ex.getMessage());
        }
        client = null;
        server = null;
//        server.disconnect();
//        server = null;
    }

    public long getId() {
        return id;
    }
}