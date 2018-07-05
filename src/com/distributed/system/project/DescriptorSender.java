package com.distributed.system.project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Hussein Awala
 */
public class DescriptorSender extends Thread{
    protected Descriptor descriptor;
    protected BasicSensor sensor;
    public static final int portSendDescriptorToNeigbor=1213;

    public DescriptorSender(Descriptor descriptor, BasicSensor sensor) {
        this.descriptor = descriptor;
        this.sensor = sensor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = new Socket(descriptor.getHost(), portSendDescriptorToNeigbor);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream.writeObject(sensor.getDescriptor());
                objectInputStream.readBoolean();
                socket.close();
                sleep(10000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
