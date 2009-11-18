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

import dataanalyzer.enumerator.ServerStatus;
import java.rmi.*;

/**
 *
 * @author Petah
 */
public interface LipeServerInterface extends Remote {

    public void out(String str);

    public ServerStatus loadProcess(AlgorithmProcess process);

    public ServerStatus runProcess(long id);

    public void processComplete(long id);

    public ServerStatus removeProcess(long id);

    public ServerStatus loadOutput(Output o);

    public long registerNode(String hostName, int port);

    public void disconnect(long id);

//    public void disconnect();
}
