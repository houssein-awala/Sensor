package com.distributed.system.project;

public class BasicSensor {
    protected Descriptor descriptor;
    protected Data data;
    protected ConnectionWithDataServer connectionWithDataServer;
    protected ConnectionWithSupervisor connectionWithSupervisor;

    public BasicSensor() {
        connectionWithDataServer=new ConnectionWithDataServer(this);
    }

    public BasicSensor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public Data getData() {
        return data;
    }

    public synchronized Data accessToData(String newData){
        if (newData==null)
        {
            return this.data;
        }
        this.data.readData(newData);
        return this.data;
    }
    public void changeTheStateToReady(){
        connectionWithSupervisor.changeStateToReady();
    }
}
