package com.distributed.system.project;

import java.io.Serializable;

/*
*AUTHOR: HUSSEIN AWALA
*this class is a descriptor of a real sensor data type and ID
* it has the ID of sensor, the port, the identifier and the variables
 */

public class Request implements Serializable{
    private int port;
    private int tau;
    private String variales;
    private String ID;

    public Request(int port, int tau, String variales) {
        this.port = port;
        this.tau = tau;
        this.variales = variales;
    }

    public int getPort() {
        return port;
    }

    public int getTau() {
        return tau;
    }

    public String getVariales() {
        return variales;
    }

    public String getID() {
        return ID;
    }
}
