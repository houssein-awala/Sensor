package com.distributed.system.project;

import java.awt.*;
import java.rmi.Remote;
import java.util.HashMap;

/*
 * this interface is the remote interface shared with supervisor to setup the sensor remotly
 */
public interface ISensor extends Remote {
    public Descriptor getDescriptor();
    public void configure(Descriptor descriptor);
    public void setRoutingTable(HashMap<String,Descriptor> routingTable);
    public void editSensor(String id, int type, Point position, int state);
    public void deleteSensor(String id);
}
