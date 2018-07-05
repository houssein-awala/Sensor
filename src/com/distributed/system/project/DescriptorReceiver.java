package com.distributed.system.project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Hussein Awala
 */
public class DescriptorReceiver extends Thread{
    BasicSensor sensor;
    Socket socket;
    public DescriptorReceiver(BasicSensor sensor, Socket socket) {
        this.sensor = sensor;
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Descriptor descriptor=(Descriptor) objectInputStream.readObject();
            objectOutputStream.writeBoolean(true);
            synchronized (sensor.getRoutingTable()) {
                sensor.getRoutingTable().put(descriptor.getId(), descriptor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
