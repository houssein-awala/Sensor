package com.distributed.system.project;

import java.net.Socket;

public class RequestHandlerAndRouter extends RequestHandler{

    public RequestHandlerAndRouter(RoutingSensor sensor, Socket socket) {
        super(sensor,socket);
    }

    @Override
    public boolean handleRequest(SinkRequest request) {
        if (super.handleRequest(request))
            return true;
        if (request.getState()==SinkRequest.WORKING){
            String next=((RoutingSensor)sensor).getRoutingTable().get(request.selectedSensor);
            if (next!=null){
                request.getPath().push(sensor.getDescriptor().getId());
                sendRequest(next,request);
                return true;
            }
            else{
                String previous=request.getPath().pop();
                request.setState(SinkRequest.ERROR);
                sendRequest(previous,request);
                return false;
            }
        }
        else {
            String previous=request.getPath().pop();
            sendRequest(previous,request);
            return false;
        }
    }

    @Override
    public void run() {
        SinkRequest request=readRequest(socket);
        handleRequest(request);
    }
}
