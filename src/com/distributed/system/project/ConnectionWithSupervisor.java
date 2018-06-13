package com.distributed.system.project;

public class ConnectionWithSupervisor {
    protected BasicSensor sensor;

    public ConnectionWithSupervisor(BasicSensor sensor) {
        this.sensor=sensor;
    }
    public void changeStateToReady(){
        //send ready to
    }
}
