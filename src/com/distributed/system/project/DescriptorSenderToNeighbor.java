package com.distributed.system.project;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hussein Awala
 */
public class DescriptorSenderToNeighbor extends Thread{
    BasicSensor sensor;
    public DescriptorSenderToNeighbor(BasicSensor sensor) {
        this.sensor=sensor;
    }

    @Override
    public void run() {
        for (Map.Entry<String,Descriptor> entry:sensor.getRoutingTable().entrySet()){
            new DescriptorSender(entry.getValue(),sensor).start();
        }
    }
}
