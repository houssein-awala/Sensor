package com.distributed.system.project;

import sun.management.Sensor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class RequestHandler extends Thread{
    BasicSensor sensor;
    Socket socket;

    public RequestHandler(BasicSensor sensor,Socket socket) {
        this.sensor = sensor;
        this.socket=socket;
    }

    public SinkRequest readRequest(Socket socket){
        SinkRequest request=null;
        try {
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            request=(SinkRequest)ois.readObject();
            oos.writeBoolean(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            return request;
        }
    }
    public boolean handleRequest(SinkRequest request){
        if (request==null)
            return false;
        if (request.getState()==SinkRequest.WORKING){
            if (request.getSelectedSensor().equals(sensor.getDescriptor().getId())){
                String retour=request.getPath().pop();
                request.setResult(sensor.getData().getData());
                request.setState(SinkRequest.OK);
                sendRequest(retour,request);
                return true;
            }
        }
        return false;
    }
    public void sendRequest(String destination,SinkRequest request){
        try {
            Socket socket=new Socket(destination,RequestReceiver.portToReceive);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            ois.readBoolean();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        SinkRequest request=readRequest(socket);
        handleRequest(request);
    }
}
