package com.distributed.system.project;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * @author Hussein Awala
 * this class represent the Basic Sensor functionality
 * it consist from  1-connection with supervisor thread
                    2-connection with data server thread
                    3-and request receiver thread
 * where every thread do it work asynchronally with other thread
 */
public class BasicSensor extends UnicastRemoteObject implements ISensor,Runnable{
    protected HashMap<String,Descriptor> routingTable;
    protected Descriptor descriptor;
    protected Data data;
    protected ConnectionWithDataServer connectionWithDataServer;
    protected ConnectionWithSupervisor connectionWithSupervisor;
    protected RequestReceiver requestReceiver;
    protected DescriptorSenderToNeighbor descriptorSender;
    protected DescriptorReceiverFromNeighbor descriptorReceiver;
    protected final Object lockDescriptor=new Object();
    protected final Object lockData=new Object();
    protected final Object waitForReady=new Object();
    private final Object lockRoutingTable=new Object();
    public Descriptor accessToDescriptor(Descriptor descriptor){
        synchronized(lockDescriptor) {
            if (descriptor != null)
                this.descriptor = descriptor;
            return descriptor;
        }
    }

    public BasicSensor() throws RemoteException {
        super();
        connectionWithDataServer=new ConnectionWithDataServer(this);
        connectionWithSupervisor=new ConnectionWithSupervisor(this);
        descriptorSender=new DescriptorSenderToNeighbor(this);
        descriptorReceiver=new DescriptorReceiverFromNeighbor(this);
        requestReceiver=new RequestReceiver(this);
    }

    public BasicSensor(Descriptor descriptor) throws RemoteException {
        super();
        this.descriptor = descriptor;
    }

    public Descriptor getDescriptor() {
        return accessToDescriptor(null);
    }

    public void setDescriptor(Descriptor descriptor){
        accessToDescriptor(descriptor);
    }
    @Override
    public void configure(Descriptor descriptor) {
        setDescriptor(descriptor);
        connectionWithDataServer.start();
    }

    public Data getData() {
        return data;
    }

    public Data accessToData(String newData){
        synchronized(lockData) {
            if (newData == null) {
                return this.data;
            }
            this.data.readData(newData);
            return this.data;
        }
    }
    public void changeTheStateToReady() throws IOException {
        connectionWithSupervisor.changeStateToReady();
    }

    public Object getWaitForReady() {
        return waitForReady;
    }

    public void runTheRequestReceiver(){
        requestReceiver.start();
        descriptorReceiver.start();
        descriptorSender.start();
    }

    public HashMap<String, Descriptor> getRoutingTable() {
        synchronized (lockRoutingTable) {
            return routingTable;
        }
    }

    public void setRoutingTable(HashMap<String, Descriptor> routingTable) {
        synchronized (lockRoutingTable) {
            this.routingTable = routingTable;
        }
    }
    @Override
    public void run() {
        connectionWithSupervisor.start();
    }
}
