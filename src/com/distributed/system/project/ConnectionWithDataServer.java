package com.distributed.system.project;

import sun.management.Sensor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hussein Awala
 * this thread is responsible for setup the connection with the data server
 * then receiving the data 1 time at least with a TCP connection and save it
 * then change the state of this sensor in the supervisor
 * then receiving the data after each sent from the data server and refresh the old data by the new
 */
public class ConnectionWithDataServer extends Thread {
    static protected final String hostOfTheDataServer = "DATA_SERVER";
    static protected final int portWithDataServer = 1234;
    static protected final int portToListenNewData = 6789;
    protected BasicSensor sensor;

    public ConnectionWithDataServer(BasicSensor sensor) {
        this.sensor = sensor;
    }

    //this is a template explain how this thread work
    private final void connect() throws IOException {
        //1st step open the connection and receive data for 1 time
        openConnection();
        //tell to supervisor that the sensor is ready to serve the sink
        sensor.changeTheStateToReady();
        //receive new data from the data server and change the old data
        receiveData();
    }

    //in this method the sensor open a TCP connection with data server
    //then read the data for the first time and save it in Data object of the sensor
    public void openConnection() throws IOException {
        Socket socket = new Socket(hostOfTheDataServer, portWithDataServer);
        Scanner in = new Scanner(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(sensor.getDescriptor().getRequest());
        sensor.accessToData(in.nextLine());
        out.writeBoolean(true);
    }

    //this thread must be ready to receive data every T of time
    //after each receive, this thread go to the sensor object and change the old data to the new data received
    public void receiveData() throws SocketException {
        DatagramSocket listenToNewData = new DatagramSocket(portToListenNewData);
        while (true) {
            byte[] newData = new byte[1024 * 1024 * 10];
            DatagramPacket packet = new DatagramPacket(newData, newData.length);
            sensor.accessToData(new String(newData));
        }
    }

    //runnig the thread and call the template method
    @Override
    public void run() {
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}