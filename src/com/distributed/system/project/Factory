package com.distributed.system.project;


import static factory.Descriptor.BASIC_SENSOR;
import java.awt.Point;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mostafa slim
 */
public class Factory extends UnicastRemoteObject implements IFactory{

    
    
    
    @Override
    public void createSensor(int type, Point position, int service, int capacity, double range) {
    
        
        
        new Thread(){
        
        @Override
        public void run(){
        
        
        
        }
        };
    }

    public class threadhandle extends Thread
    {
    public int type;
    public Point position;
    public int service;
    public int capacity;
    public double range;

        public threadhandle(int type, Point position, int service, int capacity, double range) {
            this.type = type;
            this.position = position;
            this.service = service;
            this.capacity = capacity;
            this.range = range;
        }
   
    @Override
    public void run()
    {
      Descriptor descriptor =  new Descriptor(type, service, capacity, range);
     if(type==BASIC_SENSOR) {
          try {
              BasicSensor basicSensor =  new BasicSensor(descriptor);
              
          } catch (RemoteException ex) {
              Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
          }
     
     }
     else {
         
     
     }
    
    }
    

}


}
