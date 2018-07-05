package com.distributed.system.project;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Hussein Awala
 */
public class RoutingSensor extends BasicSensor{
    protected HashMap<Integer,HashSet<String>> messagesManager;

    public RoutingSensor() throws RemoteException {
        messagesManager=new HashMap<>();
    }

    public RoutingSensor(Descriptor descriptor) throws RemoteException {
        super(descriptor);
    }

    public synchronized HashMap<Integer, HashSet<String>> getMessagesManager() {
        return messagesManager;
    }

    public synchronized void setMessagesManager(HashMap<Integer, HashSet<String>> messagesManager) {
        this.messagesManager = messagesManager;
    }
}
