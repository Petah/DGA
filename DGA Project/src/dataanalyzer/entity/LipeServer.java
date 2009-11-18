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
import dataanalyzer.UserInterface;
import dataanalyzer.enumerator.ServerStatus;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.util.Unique;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.swing.JOptionPane;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;
import net.sf.lipermi.net.Server;

/**
 *
 * @author Petah
 */
public class LipeServer implements LipeServerInterface {

    private DGA system;
    private CallHandler callHandler;
    private Server server;
    protected transient LinkedHashSet<CompletionListener> completionListeners = new LinkedHashSet<CompletionListener>();

    public LipeServer(DGA system) {
        super();
        this.system = system;
    }

    public boolean start() {
        Setting<Integer> port = system.settingsManager.find("port");
        if (port == null) {
            port = new Setting("port", 4055, "Integer");
            system.settingsManager.add(port);
        }
        return start(port.value);
    }

    /**
     * Starts the RMI registry and binds the server
     * @param port Port to start the RMI registry
     * @return True if the server was started correctly, otherwise false
     */
    public boolean start(int port) {
        callHandler = new CallHandler();
        LipeServerInterface serverInterface = this;
        try {
            callHandler.registerGlobal(LipeServerInterface.class, serverInterface);
        } catch (LipeRMIException ex) {
            system.userInterface.handleException(ex);
            return false;
        }
        if (server == null) {
//            server.close();
//            server = null;
            server = new Server();
            try {
                server.bind(port, callHandler);
            } catch (IOException ex) {
                system.userInterface.handleException(ex);
                return false;
            }
        }
        system.userInterface.out("Lipe server started.");
        return true;
    }

    //Server side (client tells server to ...)
    public void out(String str) {
        system.userInterface.out("Server: " + str);
    }

    // FIXME: fix the disconnecting 
    public void stop() {
//        system.userInterface.out("discon");
//        if (server != null) {
////        system.userInterface.out("discon2");
            server.close();
            server = null;
//            system.userInterface.out("Lipe server stopped.");
//        }
    }
//
    // FIXME: fix the disconnecting 
    public void disconnect(long id) {
        Iterator<Node> i = system.nodeManager.get().iterator();
        while (i.hasNext()) {
            Node n = i.next();
            if (n.getId() == id) {
                n.disconnect();
                i.remove();
                system.userInterface.out("Client disconnected from server (id:" + id + ").");
                break;
            }
        }
    }

    public long registerNode(String hostName, int port) {
        try {
            Client client = new Client(hostName, port, new CallHandler());
            LipeServerInterface serverInterface = (LipeServerInterface) client.getGlobal(LipeServerInterface.class);
//            system.userInterface.out("Returned connection to Lipe client.");
            long id = Unique.getLong();
            Node node = new Node(system, hostName, port, serverInterface, client, id);
            system.nodeManager.add(node);

            system.userInterface.out("New client connected to server (id:" + id + ").");
            return id;
        } catch (IOException ex) {
            system.userInterface.handleError(ex.getMessage());
        }
        return -1;
    }

    public ServerStatus loadOutput(Output o) {
        system.outputManager.add(o);
        system.userInterface.out("Server -> Output loaded from " + o.getOwner().toString(), UserInterface.MIN);
        return ServerStatus.failure;
    }

    public ServerStatus loadProcess(AlgorithmProcess process) {
        system.processManager.add(process);
        system.userInterface.out("Server -> Process loaded: " + process, UserInterface.MIN);
        return ServerStatus.success;
    }

    public ServerStatus removeProcess(long id) {
        final AlgorithmProcess process = findProcess(id);
        if (process != null) {
            system.processManager.remove(process);
            system.userInterface.out("Server -> Process removed: " + process, UserInterface.MIN);
            return ServerStatus.success;
        }
        return ServerStatus.processNotFound;
    }

    public void processComplete(long id) {
        out("Completed running process (" + id + ")");
        for (CompletionListener c : completionListeners) {
            c.complete(id);
        }
    }

    public void addCompletionListener(CompletionListener c) {
        completionListeners.add(c);
    }

    public ServerStatus runProcess(long id) {
        final AlgorithmProcess process = findProcess(id);
        if (process != null) {
            process.addCompletionListener(new CompletionListener() {

                public void complete(Object o) {
                    system.client.getServerInterface().loadOutput(process.getOutput());
                    system.client.getServerInterface().processComplete(process.getId());
                }
            });
            system.processManager.run(process);
            system.userInterface.out("Server -> Running process: " + process, UserInterface.MIN);
            return ServerStatus.success;
        }
        return ServerStatus.processNotFound;
    }

    private AlgorithmProcess findProcess(long id) {
        for (AlgorithmProcess p : system.processManager.get()) {
            if (p.getId() == id) {
                return p;
            }
        }
        system.userInterface.handleError("Server -> Cannot find process " + id);
        return null;
    }
}
