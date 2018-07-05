package com.distributed.system.project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestReceiver extends Thread {
    protected BasicSensor sensor;
    public static final int portToReceive=1122;

    public RequestReceiver(BasicSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void run() {
        synchronized (sensor.getWaitForReady()){
            if (sensor.getDescriptor().getState()!= Descriptor.READY){
                try {
                    sensor.getWaitForReady().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(portToReceive);
            while (true){
                Socket request=socket.accept();
                createHandler(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createHandler(Socket request){
        if (sensor instanceof RoutingSensor){
            new RequestHandlerAndRouter((RoutingSensor) sensor,request).start();
        }
        else {
            new RequestHandler(sensor,request).start();
        }
    }
}
