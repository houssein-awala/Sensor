package com.distributed.system.project;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BasicSensor extends UnicastRemoteObject implements ISensor{
    protected Descriptor descriptor;
    protected Data data;
    protected ConnectionWithDataServer connectionWithDataServer;
    protected ConnectionWithSupervisor connectionWithSupervisor;
    protected final Object lockDescriptor=new Object();
    protected final Object lockData=new Object();
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
}
