package com.distributed.system.project;

import java.net.Socket;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Hussein Awala
 */
public class RequestHandlerAndRouter extends RequestHandler{

    public RequestHandlerAndRouter(RoutingSensor sensor, Socket socket) {
        super(sensor,socket);
    }

    @Override
    public boolean handleRequest(SinkRequest request) throws Exception {
        if (super.handleRequest(request))
            return true;
        Descriptor next=null;
        HashSet<String> alreadyTestedIt=((RoutingSensor)sensor).getMessagesManager().get(request.getRequestID());
        if (alreadyTestedIt==null){
            alreadyTestedIt=new HashSet<>();
            ((RoutingSensor)sensor).getMessagesManager().put(request.getRequestID(),alreadyTestedIt);
        }
        for (Map.Entry<String,Descriptor> entry:sensor.getRoutingTable().entrySet()){
            if (!alreadyTestedIt.contains(entry.getKey()))
            {
                (((RoutingSensor)sensor).getMessagesManager().get(request.getRequestID())).add(entry.getKey());
                next=sensor.getRoutingTable().get(entry.getKey());
                break;
            }
        }
        if (request.getState()==SinkRequest.WORKING){
            sensor.getRoutingTable().get(request.selectedSensor);
            if (next!=null){
                request.getPath().push(sensor.getDescriptor().getId());
                sendRequest(next.getHost(),request);
                return true;
            }
            else{
                String previous=request.getPath().pop();
                request.setState(SinkRequest.ERROR);
                ((RoutingSensor)sensor).getMessagesManager().remove(request.getRequestID());
                sendRequest(previous,request);
                return false;
            }
        }
        else {
            if (request.getState()==SinkRequest.OK) {
                String previous = request.getPath().pop();
                ((RoutingSensor)sensor).getMessagesManager().remove(request.getRequestID());
                sendRequest(previous, request);
                return true;
            }
            else {
                request.getPath().push(sensor.getDescriptor().getId());
                sendRequest(next.getHost(),request);
                request.setState(SinkRequest.WORKING);
                return true;
            }
        }
    }

    @Override
    public void run() {
        SinkRequest request=readRequest(socket);
        try {
            handleRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
