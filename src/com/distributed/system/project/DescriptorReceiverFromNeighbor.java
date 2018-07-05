package com.distributed.system.project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Hussein Awala
 */
public class DescriptorReceiverFromNeighbor extends Thread{
    BasicSensor sensor;

    public DescriptorReceiverFromNeighbor(BasicSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void run() {
        try {
            ServerSocket socket=new ServerSocket(DescriptorSender.portSendDescriptorToNeigbor);
            while (true){
                Socket s=socket.accept();
                new DescriptorReceiver(sensor,s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
