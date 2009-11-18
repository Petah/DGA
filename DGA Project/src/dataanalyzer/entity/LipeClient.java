/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package dataanalyzer.entity;

import dataanalyzer.DGA;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

/**
 *
 * @author Petah
 */
public class LipeClient {

    private DGA system;
    private Client client;
    private CallHandler callHandler;
    private LipeServerInterface serverInterface;
    private long id;
    private LipeServer returnServer;

    public LipeClient(DGA system) {
        this.system = system;
    }

    public boolean connect() {
        Setting<String> host = system.settingsManager.find("hostName");
        if (host == null) {
            host = new Setting("hostName", "localhost", "String");
            system.settingsManager.add(host);
        }
        Setting<Integer> port = system.settingsManager.find("port");
        if (port == null) {
            port = new Setting("port", 4055, "Integer");
            system.settingsManager.add(port);
        }
        Setting<Integer> returnPort = system.settingsManager.find("returnPort");
        if (returnPort == null) {
            returnPort = new Setting("returnPort", 4056, "Integer");
            system.settingsManager.add(returnPort);
        }
        return connect(host.value, port.value, returnPort.value);
    }

    public boolean connect(String hostName, int port, int returnPort) {
        callHandler = new CallHandler();
        try {
            client = new Client(hostName, port, callHandler);
        } catch (IOException ex) {
            system.userInterface.handleError(ex.getMessage());
            return false;
        }
        serverInterface = (LipeServerInterface) client.getGlobal(LipeServerInterface.class);


        returnServer = new LipeServer(system);
        returnServer.start(returnPort);
        try {
            id = serverInterface.registerNode(InetAddress.getLocalHost().getHostAddress(), returnPort);
        } catch (UnknownHostException ex) {
            system.userInterface.handleException(ex);
        }
        system.userInterface.out("Connected to Lipe server (id:" + id + ").");
        return true;
    }

    public LipeServerInterface getServerInterface() {
        return serverInterface;
    }

    // FIXME: fix the disconnecting 
    public void disconnect() {
        serverInterface.disconnect(id);
//////        system.server.disconnect();
        returnServer.stop();
        try {
            client.close();
        } catch (IOException ex) {
            system.userInterface.handleError(ex.getMessage());
        }
        returnServer = null;
        serverInterface = null;
        client = null;
//        callHandler = null;
//        system.userInterface.out("Disconnected from Lipe server.");
    }
}
