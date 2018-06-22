package com.distributed.system.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
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
    /********************************************************************/

    //filling by Supervisor
    /********************************************************************/
    //ID of the actual selected sensor
    protected String selectedSensor;
    //list of IDs of the previous selected sensors
    protected HashSet<String> selectedSensors;
    /********************************************************************/

    //filling by Sensors
    /********************************************************************/
    //the state of this request
    protected int state;
    //the result of the wanted service
    protected String result;
    //the sequence of sensors that transmit this request
    protected ArrayList<String> path;
    /********************************************************************/

    public SinkRequest(int typeOfService, Point position) {
        this.typeOfService = typeOfService;
        this.position = position;
        this.state=WORKING;
        this.selectedSensors=new HashSet<>();
        this.path=new ArrayList<>();
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

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }
}
