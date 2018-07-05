package com.distributed.system.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/*
 * @author:Hussein Awala
 * this class is a mobile agent represent the request of the user(SINK)
 * the instance of this class transfer between the component of network
 * every component add something data to this instance, while it's not arrived to goal
 * and when arrived to goal, this sensor add the data and retuen the object to SINK
 */
public class SinkRequest {

    //the state where the request is handled by the selected sensor and the result is added
    public static final int OK=0;
    //the state where the selected sensor is unreachable
    public static final int ERROR=1;
    //the initial state when the request is created even find 1 of the 2 previous state
    public static final int WORKING=2;

    //filling by SINK
    /********************************************************************/
    //the type of the wanted service
    protected int typeOfService;
    //the position of the wanted service
    protected Point position;
    //the encrypted master Key
    protected byte[] masterKey;
    /********************************************************************/

    //filling by Supervisor
    /********************************************************************/
    //ID of the actual selected sensor
    protected String selectedSensor;
    //list of IDs of the previous selected sensors
    protected HashSet<String> selectedSensors;
    //the id of this request
    protected int requestID;
    /********************************************************************/

    //filling by Sensors
    /********************************************************************/
    //the state of this request
    protected int state;
    //the result of the wanted service
    protected String result;
    //the sequence of sensors that transmit this request
    protected Stack<String> path;
    //the host of last RoutingSensor
    protected String hostOfLastRouter;
    /********************************************************************/

    public SinkRequest(int typeOfService, Point position) {
        this.typeOfService = typeOfService;
        this.position = position;
        this.state=WORKING;
        this.selectedSensors=new HashSet<>();
        this.path=new Stack<>();
    }

    public int getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(int typeOfService) {
        this.typeOfService = typeOfService;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getSelectedSensor() {
        return selectedSensor;
    }

    public void setSelectedSensor(String selectedSensor) {
        this.selectedSensor = selectedSensor;
    }

    public HashSet<String> getSelectedSensors() {
        return selectedSensors;
    }

    public void setSelectedSensors(HashSet<String> selectedSensors) {
        this.selectedSensors = selectedSensors;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Stack<String> getPath() {
        return path;
    }

    public void setPath(Stack<String> path) {
        this.path = path;
    }

    public String getHostOfLastRouter() {
        return hostOfLastRouter;
    }

    public void setHostOfLastRouter(String hostOfLastRouter) {
        this.hostOfLastRouter = hostOfLastRouter;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setMasterKey(byte[] masterKey) {
        this.masterKey = masterKey;
    }

    //add the selected sensor to the set of selected sensors and empty the first #By Mohamad Mohyeddine
    public void addCurrentToSensors()
    {
        selectedSensors.add(selectedSensor);
        selectedSensor="";
    }

    //Test if the sensor is unreachable (exists in the set of selected sensors) #By Mohamad Mohyeddine
    public boolean isUnReached(String id)
    {
        return selectedSensors.contains(id);
    }

    public byte[] getMasterKey() {
        return masterKey;
    }
}
