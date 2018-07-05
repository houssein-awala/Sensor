package com.distributed.system.project;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;

public class RoutingSensor extends BasicSensor{
    protected HashMap<String,String> routingTable;
    private final Object lockRoutingTable=new Object();

    public RoutingSensor() throws RemoteException {
    }

    public RoutingSensor(Descriptor descriptor) throws RemoteException {
        super(descriptor);
    }

    public HashMap<String, String> getRoutingTable() {
        synchronized (lockRoutingTable) {
            return routingTable;
        }
    }

    public void setRoutingTable(HashMap<String, String> routingTable) {
        synchronized (lockRoutingTable) {
            this.routingTable = routingTable;
        }
    }

}
